package at.shadowbot.command.commands.misc;

import at.shadowbot.Bot;
import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.lang.management.ManagementFactory;

public class BotInfo extends Command {
    public BotInfo() {
        super("BotInfo", CommandCategory.MISC);
        setDisplayEmoji(":battery: ");
        setDescription("Displays general information about the bot");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        EmbedBuilder meb = new EmbedBuilder();

        meb.setTitle("Bot Statistiken");
        meb.setColor(Color.PINK);

        long maxMemory = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        long totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
        long freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        meb.addField("User: ", Bot.jda.getGuilds().stream().mapToInt(guild -> guild.getMemberCount()).sum() + "\n", false);
        meb.addField("Server: ", String.valueOf(Bot.jda.getGuilds().size()), false);

        meb.addBlankField(false);

        com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        meb.addField("CPU Auslastung:", (int) (os.getSystemCpuLoad() * 100) + "%", false);
        meb.addField("CPU Kerne:", "" + os.getAvailableProcessors(), false);
        meb.addField("RAM Auslastung", usedMemory + "/" + maxMemory + " (" + (usedMemory * 100) / totalMemory + "%)", false);
        e.getChannel().sendMessage(meb.build()).queue();
    }
}
