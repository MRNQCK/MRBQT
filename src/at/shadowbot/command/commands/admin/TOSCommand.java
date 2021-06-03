package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class TOSCommand extends Command {
    public TOSCommand() {
        super("TOS", CommandCategory.ADMIN);
        setDescription("TOS");
        setDisplayEmoji(":shield:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage(" https://discord.com/terms ").queue();
    }
}
