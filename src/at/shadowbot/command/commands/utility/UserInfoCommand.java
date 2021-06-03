package at.shadowbot.command.commands.utility;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.command.commands.CommandTargetType;
import at.shadowbot.utils.FormationUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class UserInfoCommand extends Command {
    public UserInfoCommand() {
        super("UserInfo", CommandCategory.UTILITY);
        setDescription("Shows Information about a specific User");
        setDisplayEmoji(":information_source:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        CommandTargetType targetType;
        boolean id;
        try {
            e.getGuild().retrieveMemberById(args[0]).complete();
            id = true;
        } catch (Exception ex) {
            id = false;
        }
        if (args.length < 1 && e.getMessage().getMentionedMembers().size() < 1 && !id) {
            targetType = CommandTargetType.SELF;
        } else if (args.length > 0 && e.getMessage().getMentionedMembers().size() > 0) {
            targetType = CommandTargetType.MENTION;
        } else if (id) {
            targetType = CommandTargetType.ID;
        } else {
            targetType = CommandTargetType.SELF;
        }
        Member target = targetType == CommandTargetType.SELF ? e.getMember() : (targetType == CommandTargetType.ID ? e.getGuild().retrieveMemberById(args[0]).complete() : e.getMessage().getMentionedMembers().get(0));
        embedBuilder.setTitle(":label: UserInfo ~ " + target.getUser().getAsTag());
        embedBuilder.setDescription(target.getAsMention());
        if (target.getNickname() != null)
            embedBuilder.addField(":calendar_spiral: Nickname", target.getNickname(), true);
        embedBuilder.addField(":calendar_spiral: Joined Discord", FormationUtils.translateDate(target.getTimeCreated()), true);
        embedBuilder.addField(":calendar_spiral: Joined here", FormationUtils.translateDate(target.getTimeJoined()), true);
        if (target.getTimeBoosted() != null)
            embedBuilder.addField(":sparkles: Boosting since", FormationUtils.translateDate(target.getTimeBoosted()), true);
        try {
            embedBuilder.addField(":rainbow: Color", String.format("#%02x%02x%02x", target.getColor().getRed(), target.getColor().getGreen(), target.getColor().getBlue()), true);
        } catch (NullPointerException ignored) {
        }
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        target.getRoles().forEach(role -> {
            roles.add(role.getName());
        });
        target.getPermissions().forEach(permission -> {
            permissions.add(permission.getName());
        });
        embedBuilder.addField(":crossed_swords: Roles", String.join(", ", roles), false);
        embedBuilder.addField(":military_medal: Permissions", String.join(", ", permissions), false);
        embedBuilder.setColor(target.getColor());
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
