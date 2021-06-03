package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;

import java.util.Arrays;

public class SKickCommand extends Command {
    public SKickCommand() {
        super("SKick", CommandCategory.ADMIN);
        setDescription("Silent Kick Users from your Server");
        setDisplayEmoji(":punch:");
        setPermission(Permission.KICK_MEMBERS);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        if (args.length > 0) {
            Guild guild = e.getGuild();
            Member target;
            if (e.getMessage().getMentionedMembers().size() > 0) {
                target = e.getMessage().getMentionedMembers().get(0);
            } else {
                target = e.getGuild().retrieveMemberById(args[0]).complete();
            }
            String reason = "By: " + e.getMember().getUser().getAsTag();
            if (args.length > 1) {
                reason = reason + " " + String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            }
            try {
                guild.kick(target, reason).queue();
            } catch (PermissionException ignored) {
            }
            e.getChannel().sendMessage("The Member " + target.getUser().getAsTag() + " got Kicked").queue();
        } else {
            e.getChannel().sendMessage("Please Mention the User you want to SKick in the command").queue();
        }
    }
}
