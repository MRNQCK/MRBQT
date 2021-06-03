package at.shadowbot.command.commands.misc;

import at.shadowbot.Bot;
import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", CommandCategory.MISC);
        setDescription("Zeigt den ping an");
        setDisplayEmoji(":mailbox_with_no_mail:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage("Pong! " + Bot.jda.getGatewayPing() + "ms :french_bread:").queue();
    }
}
