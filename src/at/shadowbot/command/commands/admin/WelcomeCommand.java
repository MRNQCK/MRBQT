package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.sql.Manager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;

public class WelcomeCommand extends Command {
    public WelcomeCommand() {
        super("Welcome", CommandCategory.ADMIN);
        setDescription("Sents a Message when a user jons");
        setDisplayEmoji(":door:");
        setPermission(Permission.MANAGE_CHANNEL);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        try {
            Manager manager = new Manager();
            manager.createUserString(e.getGuild().getId(), "SERVERSETTINGS", "WELCOMECHANNEL");
            manager.createUserString(e.getGuild().getId(), "SERVERSETTINGS", "WELCOMEMESSAGE");
            TextChannel channel = e.getMessage().getMentionedChannels().get(0);
            if (args.length > 0) {
                String msg = e.getMessage().getContentRaw().substring(getName().length() + 2 + args[0].length() + 1);
                manager.setString(e.getGuild().getId(), "SERVERSETTINGS", "WELCOMECHANNEL", channel.getId());
                manager.setString(e.getGuild().getId(), "SERVERSETTINGS", "WELCOMEMESSAGE", msg);
                e.getChannel().sendMessage("Die Nachricht wurde gesetzt UwU").queue();
            } else {
                manager.setString(e.getGuild().getId(), "SERVERSETTINGS", "WELCOMEMESSAGE", "");
                e.getChannel().sendMessage("Es gibt jetzt keine Nachricht mehr TvT").queue();
            }
        } catch (Exception ex) {
            e.getChannel().sendMessage("Benutze Welcome #Channel <Nachricht>").queue();
        }
    }
}
