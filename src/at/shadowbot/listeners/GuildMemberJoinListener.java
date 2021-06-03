package at.shadowbot.listeners;

import at.shadowbot.Bot;
import at.shadowbot.sql.Manager;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMemberJoinListener extends ListenerAdapter {

    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        try {
            Manager manager = new Manager();
            String channelID = manager.getString(event.getGuild().getId(), "SERVERSETTINGS", "WELCOMECHANNEL");
            String msg = manager.getString(event.getGuild().getId(), "SERVERSETTINGS", "WELCOMEMESSAGE");
            if (msg.equalsIgnoreCase("")) {
                return;
            }
            Bot.jda.getTextChannelById(channelID).sendMessage(msg.replaceAll("%user%", event.getMember().getAsMention())).queue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kannst du ignorieren hoffe ich lol");
        }
    }
}
