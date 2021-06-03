package at.shadowbot;

import at.shadowbot.command.CommandManager;
import at.shadowbot.command.commands.admin.*;
import at.shadowbot.command.commands.fun.*;
import at.shadowbot.command.commands.misc.*;
import at.shadowbot.command.commands.nsfw.*;
import at.shadowbot.command.commands.utility.*;
import at.shadowbot.game.GameManager;
import at.shadowbot.listeners.GuildMemberJoinListener;
import at.shadowbot.sql.Manager;
import at.shadowbot.sql.SQLAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;


public class Bot {
    public static JDA jda;

    public static String getPrefix(Guild guild) {

        String id = guild.getId();
        String prefix = new Manager().getString(id, "SERVERSETTINGS", "PREFIX");
        if (prefix.equalsIgnoreCase("") || prefix.equalsIgnoreCase("0"))
            prefix = "\"";
        return prefix;
    }

    public static void main(String[] args) {

        new SQLAPI();

        System.out.println("Logging into Discord Account");
        try {
            login();
        } catch (LoginException ex) {
            System.out.println("Cannot log into the given Account: " + ex.getMessage());
        }
        System.out.println("Successfully logged into Account " + jda.getSelfUser().getAsTag() + " / " + jda.getSelfUser().getId());

        System.out.println("Registering Listeners");
        registerListeners();
        System.out.println("Successfully registered " + jda.getRegisteredListeners().size() + " Listeners");

        System.out.println("Registering Commands");
        registerCommands();
        System.out.println("Successfully registered " + CommandManager.getInstance().getRegisteredCommandAmount() + " Commands");

        System.out.println("Bot invite: " + jda.getInviteUrl(Permission.ADMINISTRATOR));



    }

    private static void login() throws LoginException {
        String token = "Nzg2OTEyMzA0OTQ4MDUxOTg5.X9NTMQ._Nw3I1OwNHYGIO716rm1PVormyU";
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.setActivity(Activity.listening("\"Help "));
        jda = builder.build();
    }

    private static void registerListeners() {
        jda.addEventListener(new GuildMemberJoinListener());
        new CommandManager();
        jda.addEventListener(CommandManager.getInstance());
        jda.addEventListener(new GameManager());
    }

    private static void registerCommands() {
        CommandManager.getInstance().registerCommand(new CryptoCommand());
        CommandManager.getInstance().registerCommand(new HelpCommand());
        CommandManager.getInstance().registerCommand(new PollCommand());
        CommandManager.getInstance().registerCommand(new UserInfoCommand());
        CommandManager.getInstance().registerCommand(new GuildInfoCommand());
        CommandManager.getInstance().registerCommand(new KickCommand());
        CommandManager.getInstance().registerCommand(new BanCommand());
        CommandManager.getInstance().registerCommand(new PlayCommand());
        CommandManager.getInstance().registerCommand(new BDOBossCommand());
        CommandManager.getInstance().registerCommand(new CoinflipCommand());
        CommandManager.getInstance().registerCommand(new ClearCommand());
        CommandManager.getInstance().registerCommand(new DiceCommand());
        CommandManager.getInstance().registerCommand(new InviteCommand());
        CommandManager.getInstance().registerCommand(new AvatarCommand());
        CommandManager.getInstance().registerCommand(new PrefixCommand());
        CommandManager.getInstance().registerCommand(new HentaiCommand());
        CommandManager.getInstance().registerCommand(new EroFeetCommand());
        CommandManager.getInstance().registerCommand(new FeetCommand());
        CommandManager.getInstance().registerCommand(new LesbianCommand());
        CommandManager.getInstance().registerCommand(new LewdNekoCommand());
        CommandManager.getInstance().registerCommand(new AnalCommand());
        CommandManager.getInstance().registerCommand(new TitsCommand());
        CommandManager.getInstance().registerCommand(new MemeCommand());
        CommandManager.getInstance().registerCommand(new DankMemeCommand());
        CommandManager.getInstance().registerCommand(new PingCommand());
        CommandManager.getInstance().registerCommand(new WelcomeCommand());
        CommandManager.getInstance().registerCommand(new BoobsCommand());
        CommandManager.getInstance().registerCommand(new BotInfo());
        CommandManager.getInstance().registerCommand(new SBanCommand());
        CommandManager.getInstance().registerCommand(new TOSCommand());
        CommandManager.getInstance().registerCommand(new TOSAgeCommand());

    }
}
