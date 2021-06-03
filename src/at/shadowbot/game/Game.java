package at.shadowbot.game;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    private String name;

    private List<Member> involvedPlayers;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void processInput(Message message) {
    }

    public void setInvolvedPlayers(List<Member> involvedPlayers) {
        this.involvedPlayers = involvedPlayers;
    }

    public List<Member> getInvolvedPlayers() {
        return involvedPlayers;
    }

    public boolean isMemberInGame(Member member) {
        List<String> ids = new ArrayList<>();
        involvedPlayers.forEach(member1 -> {
            if (member.getGuild().getId().equalsIgnoreCase(member1.getGuild().getId()))
                ids.add(member1.getId());
        });
        return ids.stream().anyMatch(member.getId()::equalsIgnoreCase);
    }
}
