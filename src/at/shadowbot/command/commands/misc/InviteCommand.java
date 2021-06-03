package at.shadowbot.command.commands.misc;

import at.shadowbot.Bot;
import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class InviteCommand extends Command {
    public InviteCommand() {
        super("Invite", CommandCategory.MISC);
        setDisplayEmoji(":french_bread:");
        setDescription("Invite this Bot to **your** Guilds");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Baguette");
        embedBuilder.addField("", "To invite the Bot, click [here](" + Bot.jda.getInviteUrl(Permission.ADMINISTRATOR) + ")", false);
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
