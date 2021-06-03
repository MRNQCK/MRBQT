package at.shadowbot.command.commands.fun;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class DiceCommand extends Command {
    public DiceCommand() {
        super("Dice", CommandCategory.FUN);
        setDescription("Throws a Dice for you.");
        setDisplayEmoji(":game_die:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage("You rolled the number " + (new Random().nextInt(5) + 1)).queue();
    }
}
