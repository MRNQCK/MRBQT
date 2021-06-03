package at.shadowbot.command.commands.fun;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MemeCommand extends Command {
    public MemeCommand() {
        super("Meme", CommandCategory.FUN);
        setDescription("Random Meme");
        setDisplayEmoji(":frog:");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage(getMeme()).queue();
    }

    public static MessageEmbed getMeme() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Meme");
        try {
            String apiUrl = "https://meme-api.herokuapp.com/gimme";
            JSONObject jsonObject = new JSONObject((String) Unirest.get(apiUrl).asString().getBody());
            embedBuilder.setImage(jsonObject.getString("url"));
        } catch (Throwable t) {
            embedBuilder.setDescription("Something went wrong.");
        }
        return embedBuilder.build();
    }
}