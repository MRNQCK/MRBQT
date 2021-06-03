package at.shadowbot.command;

import at.shadowbot.Bot;
import at.shadowbot.utils.Multithreading;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager extends ListenerAdapter {

    private static List<Command> commands;

    private static CommandManager instance;

    public static CommandManager getInstance() {
        return instance;
    }

    public CommandManager() {
        commands = new ArrayList<>();
        instance = this;
    }

    public void registerCommand(Command command) {
        commands.add(command);
    }

    public void unregisterCommand(Command command) {
        for (Command cmd : commands) {
            if (cmd.getClass() == command.getClass()) {
                commands.remove(cmd);
                break;
            }
        }
    }

    public int getRegisteredCommandAmount() {
        return commands.size();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String arg, cmd;
        String[] args;
        String prefix = Bot.getPrefix(event.getGuild());
        if (event.getAuthor().isBot() || event.getMessage().getContentRaw().split("\\s+").length < 1)
            return;
        arg = event.getMessage().getContentRaw().split("\\s+")[0];
        if (arg.length() < prefix.length() || !arg.substring(0, prefix.length()).equalsIgnoreCase(prefix))
            return;
        cmd = arg.substring(prefix.length());
        args = event.getMessage().getContentRaw().length() > 0 ? Arrays.copyOfRange(event.getMessage().getContentRaw().split("\\s+"), 1, event.getMessage().getContentRaw().split("\\s+").length) : new String[0];
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(cmd) || contains(command.getAliases(), cmd)) {
                System.out.println(event.getAuthor().getAsTag() + " issued Command " + command.getName() + " on Guild " + event.getGuild().getName() + " > " + event.getMessage().getContentRaw());
                if (command.isValidSender(event.getMember())) {
                    if (!command.isNsfw() || event.getChannel().isNSFW()) {
                        if (command.getArguments().length == 0 || (args.length > 0 && contains(command.getArguments(), args[0]))) {
                            Multithreading.runAsync(() -> {
                                command.onCommand(event, args);
                            });
                        } else if (command.getArguments().length > 0) {
                            event.getChannel().sendMessage("Invalid Arguments! Please use one of the Following: `" + command.getName() + " <" + String.join(", ", command.getArguments()) + ">`").queue();
                        }
                    } else {
                        event.getChannel().sendMessage("This command only works in NSFW-enabled Channels").queue();
                    }
                } else {
                    event.getChannel().sendMessage("You are missing the permissions " + command.getPermission() + " or ADMINISTRATOR to execute this command").queue();
                }
                return;
            }
        }
    }

    private boolean contains(String[] a, String s) {
        return Arrays.stream(a).anyMatch(s::equalsIgnoreCase);
    }

    public List<Command> getCommandsInCategory(CommandCategory category) {
        List<Command> result = new ArrayList<>();
        for (Command command : commands)
            if (command.getCategory() == category)
                result.add(command);
        return result;
    }

    public Command parseCommand(String s) {
        for (Command command : commands)
            if (command.getName().equalsIgnoreCase(s) || contains(command.getAliases(), s))
                return command;
        return null;
    }
}
