package at.shadowbot.command.commands.nsfw;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LesbianCommand extends Command {
    public LesbianCommand() {
        super("Lesbian", CommandCategory.NSFW);
        setDescription("Random Lesbian Gif");
        setDisplayEmoji(":underage:");
        setNsfw(true);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage(getHentai()).queue();
    }

    public MessageEmbed getHentai() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Lesbian");
        try {
            String apiUrl = "https://nekos.life/api/v2/img/les";
            JSONObject jsonObject = new JSONObject((String) Unirest.get(apiUrl).asString().getBody());
            embedBuilder.setImage(jsonObject.getString("url"));
        } catch (Throwable t) {
            embedBuilder.setDescription("Something went wrong.");
        }
        return embedBuilder.build();
    }
}
