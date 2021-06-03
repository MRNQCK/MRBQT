package at.shadowbot.command.commands.utility;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.utils.FormationUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GuildInfoCommand extends Command {
    public GuildInfoCommand() {
        super("GuildInfo", CommandCategory.UTILITY);
        setDescription("Shows Information about this Guild");
        setDisplayEmoji(":information_source:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        Guild guild = e.getGuild();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(":information_source: GuildInfo ~ " + guild.getName());
        embedBuilder.addField(":calendar_spiral: Created", FormationUtils.translateDate(guild.getTimeCreated()), true);
        embedBuilder.addField(":crown: Owner", guild.getOwner().getAsMention(), true);
        embedBuilder.addField(":map: Location", guild.getRegion().getEmoji() + " " + guild.getRegion().getName(), true);
        embedBuilder.addField(":sparkles: Times Boosted", guild.getBoostCount() + "", true);
        if(guild.getAfkChannel() != null)
            embedBuilder.addField(":hourglass: AFK-Channel", guild.getAfkChannel().getName(), true);
        if(guild.getDefaultChannel() != null)
            embedBuilder.addField(":memo: Default Channel", guild.getDefaultChannel().getName(), true);
        embedBuilder.addField(":bar_chart: Members", guild.getMemberCount() + "", true);
        embedBuilder.setThumbnail(guild.getIconUrl());
        if(guild.getBannerUrl() != null)
            embedBuilder.addField(":link: Banner URL", guild.getBannerUrl(), true);
        embedBuilder.addField(":file_folder: Emojis", guild.getEmotes().size() + " / " + guild.getMaxEmotes(), true);
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
