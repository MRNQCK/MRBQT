package at.shadowbot.command.commands.utility;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.utils.BdoParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class BDOBossCommand extends Command {
    public BDOBossCommand() {
        super("BDOBoss", CommandCategory.UTILITY);
        setArguments("eu", "na", "jp", "kr", "mena", "ru", "sa", "sea", "th", "tw");
        setDisplayEmoji(":japanese_ogre: ");
        setDescription("Get Information about the next Boss(es) in Black Desert Online");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        String nextBossInfo = BdoParser.getNextBosses(args[0]);
        String[] infos = nextBossInfo.split("/");
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("[Upcoming] Black Desert Online Boss(es) ~ [" + args[0].toUpperCase() + "]");
        embedBuilder.setColor(Color.RED);
        embedBuilder.addField("Next Boss: " + infos[0], infos[1], true);
        embedBuilder.addField("Following Boss: " + infos[2], infos[3], true);
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
