package at.shadowbot.command.commands.utility;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.command.commands.CommandTargetType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class AvatarCommand extends Command {
    public AvatarCommand() {
        super("Avatar", CommandCategory.UTILITY);
        setDescription("Let's you hava a closer Look on someones Discord Avatar");
        setDisplayEmoji(":baby:");
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
        embedBuilder.setTitle(target.getUser().getAsTag());
        embedBuilder.setColor(target.getColor());
        embedBuilder.setImage(target.getUser().getEffectiveAvatarUrl());
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
