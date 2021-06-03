package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("Clear", CommandCategory.ADMIN);
        setDescription("Clears a certain amount of Messages in the Channel (1-99).");
        setPermission(Permission.MESSAGE_MANAGE);
        setDisplayEmoji(":cloud_tornado:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        if (args.length > 0 && isInt(args[0])) {
            int amount = Integer.parseInt(args[0]);
            if(amount > 0 && amount < 100) {
                e.getChannel().deleteMessages(e.getChannel().getHistory().retrievePast((amount + 1)).complete()).queue();
                e.getChannel().sendMessage("Cleared " + args[0] + " Messages").queue();
            } else {
                e.getChannel().sendMessage("I can only clear between 1 to 99 messages at once.").queue();
            }
        } else {
            e.getChannel().sendMessage("Please mention how many messages (1-99) you want to clear in this channel.").queue();
        }
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
