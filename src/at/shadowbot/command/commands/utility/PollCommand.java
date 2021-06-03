package at.shadowbot.command.commands.utility;

import at.shadowbot.Bot;
import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;

public class PollCommand extends Command {
    public PollCommand() {
        super("Poll", CommandCategory.UTILITY);
        setDescription("Let's you do quick polls");
        setPermission(Permission.MESSAGE_MANAGE);
        setDisplayEmoji(":bar_chart:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        if(args.length == 0) {
            e.getChannel().sendMessage("Please specify a message for the poll").queue();
        } else {
            try {
                e.getMessage().delete().queue();
            } catch (PermissionException ignored) {
            }
            Guild supportGuild = Bot.jda.getGuildById("835524774813761566");
            Message message = e.getChannel().sendMessage(String.join(" ", args)).complete();
            message.addReaction(supportGuild.getEmotesByName("vl_yes", false).get(0)).queue();
            message.addReaction(supportGuild.getEmotesByName("vl_no", false).get(0)).queue();
        }
    }
}
