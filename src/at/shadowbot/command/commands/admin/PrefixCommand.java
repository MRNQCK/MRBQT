package at.shadowbot.command.commands.admin;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.sql.Manager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PrefixCommand extends Command {
    public PrefixCommand() {
        super("Prefix", CommandCategory.ADMIN);
        setDescription("Sets the Prefix of the Bot");
        setDisplayEmoji(":otter:");
        setPermission(Permission.MANAGE_SERVER);
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        try {
            String arg = e.getMessage().getContentRaw().split("\\s+")[1];
            Manager man = new Manager();
            man.createUserString(e.getGuild().getId(), "SERVERSETTINGS", "PREFIX");
            man.setString(e.getGuild().getId(), "SERVERSETTINGS", "PREFIX", arg.toLowerCase());
            e.getChannel().sendMessage("Der Prefix hier ist jetzt: " + arg).queue();
        } catch (Exception ex) {
            e.getChannel().sendMessage("Du musst schon einen Prefix angeben du dummerchen :C").queue();
        }
    }
}
