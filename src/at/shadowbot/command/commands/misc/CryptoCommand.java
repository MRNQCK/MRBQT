package at.shadowbot.command.commands.misc;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Map;

public class CryptoCommand extends Command {
    public CryptoCommand() {
        super("Crypto", CommandCategory.MISC);
        setArguments("btc", "bnb", "eth", "ltc", "eos");
        setDescription("Show real time Crypto Currency worth");
        setDisplayEmoji(":coin:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        String arg = args[0];
        EmbedBuilder embedBuilder = null;
        switch (arg) {
            case "btc":
                embedBuilder = getEmbed("Bitcoin");
                break;
            case "bnb":
                embedBuilder = getEmbed("Binancecoin");
                break;
            case "eth":
                embedBuilder = getEmbed("Ethereum");
                break;
            case "ltc":
                embedBuilder = getEmbed("Litecoin");
                break;
            case "eos":
                embedBuilder = getEmbed("Eos");
                break;
        }
        if (embedBuilder != null)
            e.getChannel().sendMessage(embedBuilder.build()).queue();
        else
            e.getChannel().sendMessage("Something went wrong.").queue();
    }

    private EmbedBuilder getEmbed(String cur) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(cur);
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();
        Map<String, Map<String, Double>> EUR = client.getPrice(cur.toLowerCase(), Currency.EUR);
        Map<String, Map<String, Double>> USD = client.getPrice(cur.toLowerCase(), Currency.USD);
        double priceEUR = EUR.get(cur.toLowerCase()).get(Currency.EUR);
        double priceUSD = USD.get(cur.toLowerCase()).get(Currency.USD);
        embedBuilder.addField("EUR", priceEUR + "", false);
        embedBuilder.addField("USD", priceUSD + "", false);
        return embedBuilder;
    }
}
