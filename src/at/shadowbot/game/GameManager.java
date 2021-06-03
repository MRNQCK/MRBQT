package at.shadowbot.game;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GameManager extends ListenerAdapter {
    private static GameManager instance;

    private int maxGamesRunning = 1000;

    private List<Game> runningGames = new ArrayList<>();

    public static GameManager getInstance() {
        return instance;
    }

    public GameManager() {
        instance = this;
    }

    public boolean registerGame(Game game) {
        if(runningGames.size() < maxGamesRunning) {
            runningGames.add(game);
            return true;
        }
        return false;
    }

    public void unregisterGame(Game game) {
        runningGames.remove(game);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !isMemberInGame(event.getMember()))
            return;
        for (Game game : runningGames)
            if (game.isMemberInGame(event.getMember())) {
                game.processInput(event.getMessage());
                return;
            }
    }

    public boolean isMemberInGame(Member member) {
        for (Game game : runningGames)
            if (game.isMemberInGame(member))
                return true;
        return false;
    }
}
