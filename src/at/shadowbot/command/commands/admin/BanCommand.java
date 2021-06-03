package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;

import java.util.Arrays;

public class BanCommand extends Command {
    public BanCommand() {
        super("Ban", CommandCategory.ADMIN);
        setDescription("Ban Users from your Server");
        setDisplayEmoji(":gun:");
        setPermission(Permission.BAN_MEMBERS);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        if (args.length > 0) {
            Guild guild = e.getGuild();
            Member target;
            if(e.getMessage().getMentionedMembers().size() > 0) {
                target = e.getMessage().getMentionedMembers().get(0);
            } else {
                target = e.getGuild().retrieveMemberById(args[0]).complete();
            }
            String reason = "By: " + e.getMember().getUser().getAsTag();
            if (args.length > 1) {
                reason = reason + " " + String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            }
            try {
                target.getUser().openPrivateChannel().complete().sendMessage("You were banned from " + e.getGuild().getName() + "! Reason: " + reason).queue();
            } catch (Exception ignored) {
            }
            try {
                guild.ban(target, 0, reason).queue();
            } catch (PermissionException ignored) {
            }
            e.getChannel().sendMessage("The Member " + target.getUser().getAsTag() + " got Banned").queue();
        } else {
            e.getChannel().sendMessage("Please Mention the User you want to Ban or use a valid ID in the command").queue();
        }
    }
}
