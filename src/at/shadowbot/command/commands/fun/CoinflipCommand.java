package at.shadowbot.command.commands.fun;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Random;

public class CoinflipCommand extends Command {
    public CoinflipCommand() {
        super("Coinflip", CommandCategory.FUN);
        setDescription("Flips a coin for you");
        setDisplayEmoji(":coin:");
        setAliases(new String[]{"cf"});
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        int rn = new Random().nextInt(100001);
        String result;
        if (rn < 50000) {
            result = "Your Coin landed on Heads";
        } else if (rn < 100000) {
            result = "Your Coin landed on Tails";
        } else {
            result = "OH MY GOD YOUR COIN LANDED ON ITS SIDE! IS THIS EVEN POSSIBLE???";
        }
        e.getChannel().sendMessage(result).queue();
    }
}
