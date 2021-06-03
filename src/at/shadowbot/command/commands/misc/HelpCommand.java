package at.shadowbot.command.commands.misc;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;

import at.shadowbot.command.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("Help", CommandCategory.MISC);
        setDescription("List Commands or Arguments");
        setDisplayEmoji(":information_source:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        HelpListing listing = null;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        if (args.length == 0)
            listing = HelpListing.CATEGORIES;
        else if (CommandCategory.parseCategory(args[0]) != null)
            listing = HelpListing.CATEGORY;
        else if (CommandManager.getInstance().parseCommand(args[0]) != null)
            listing = HelpListing.COMMAND;
        else
            listing = HelpListing.CATEGORIES;

        switch (listing) {
            case COMMAND:
                Command command = CommandManager.getInstance().parseCommand(args[0]);
                embedBuilder.addField(":label: Name", command.getName(), false);
                embedBuilder.setTitle(":information_source: Command Info ~ " + command.getName() + (command.getDisplayEmoji() != null ? " " + command.getDisplayEmoji() : ""));
                if (command.getArguments().length > 0)
                    embedBuilder.addField(":pencil: Valid Arguments", String.join(", ", command.getArguments()), false);
                if (command.getAliases().length > 0)
                    embedBuilder.addField(":scroll: Aliases", String.join(", ", command.getAliases()), false);
                embedBuilder.addField(":page_facing_up: Category", command.getCategory().getName(), false);
                if (command.getPermission() != null)
                    embedBuilder.addField(":military_medal: Needed Permission", command.getPermission().getName() + " / ADMINISTRATOR", false);
                embedBuilder.addField(":lock: NSFW", command.isNsfw() + "", false);
                break;
            case CATEGORY:
                CommandCategory category = CommandCategory.parseCategory(args[0]);
                embedBuilder.setTitle(":page_facing_up: Category Info ~ " + category.getName());
                for (Command command1 : CommandManager.getInstance().getCommandsInCategory(category)) {
                    embedBuilder.addField((command1.getDisplayEmoji() != null ? command1.getDisplayEmoji() + " " : "") + command1.getName() + (command1.getAliases().length > 0 ? ", " + String.join(", ", command1.getAliases()) : ""), command1.getDescription() != null ? command1.getDescription() : "-", true);
                }
                break;
            case CATEGORIES:
                embedBuilder.setTitle(":page_facing_up: All Categories");
                for (CommandCategory category1 : CommandCategory.values()) {
                    embedBuilder.addField(category1.getDisplayEmoji() + " " + category1.getName(), "", true);
                }
                embedBuilder.setFooter("For further information use `" + getName() + " <Category, Command>`");
                break;
        }
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}

enum HelpListing {
    CATEGORIES,
    CATEGORY,
    COMMAND;
}
