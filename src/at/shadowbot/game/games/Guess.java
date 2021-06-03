package at.shadowbot.game.games;

import at.shadowbot.game.Game;
import at.shadowbot.game.GameManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;
import java.util.Random;

public class Guess extends Game {

    private Member member;

    private TextChannel channel;

    private int number = new Random().nextInt(5) + 1;

    public Guess(Member member, TextChannel channel) {
        super("Guess");
        this.member = member;
        this.channel = channel;
        setInvolvedPlayers(Arrays.asList(new Member[]{member}));
        channel.sendMessage("Guess a random Number between 1 and 5. Type `cancel` to cancel the Game or type your guess.").queue();
    }

    @Override
    public void processInput(Message message) {
        if (!message.getChannel().getId().equalsIgnoreCase(channel.getId()))
            return;
        if (message.getContentRaw().equalsIgnoreCase("cancel")) {
            channel.sendMessage("The Game got cancelled!").queue();
            GameManager.getInstance().unregisterGame(this);
            return;
        }
        if (isValidNr(message.getContentRaw())) {
            channel.sendMessage("Congratulations! Your guess was correct").queue();
        } else {
            channel.sendMessage("Wrong! The correct number would have been " + number + " your guess was: " + message.getContentRaw()).queue();
        }
        GameManager.getInstance().unregisterGame(this);
    }

    private boolean isValidNr(String s) {
        try {
            int i = Integer.parseInt(s);
            return i > 0 && i < 6 && i == number;
        } catch (Exception e) {
            return false;
        }
    }
}
