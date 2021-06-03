package at.shadowbot.command.commands.fun;

import at.shadowbot.command.Command;
import at.shadowbot.command.CommandCategory;
import at.shadowbot.game.GameManager;
import at.shadowbot.game.games.Guess;
import at.shadowbot.game.games.TicTacToe;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class PlayCommand extends Command {
    public PlayCommand() {
        super("Play", CommandCategory.FUN);
        setDisplayEmoji(":game_die:");
        setDescription("Play Games alone or with other Players");
        setArguments("tictactoe", "guess");
    }

    @Override
    public void onCommand(GuildMessageReceivedEvent e, String[] args) {
        String arg = args[0];
        if (GameManager.getInstance().isMemberInGame(e.getMember())) {
            e.getChannel().sendMessage("You already are in a game").queue();
            return;
        }
        switch (arg.toLowerCase()) {
            case "tictactoe":
                if (e.getMessage().getMentionedMembers().size() < 1) {
                    e.getChannel().sendMessage("Please mention the User you want to Play with in the Command").queue();
                    return;
                }
                Member mate = e.getMessage().getMentionedMembers().get(0);
                if (mate.getId().equalsIgnoreCase(e.getMember().getId())) {
                    e.getChannel().sendMessage("You cannot play against yourself, dummy").queue();
                    return;
                }
                if (GameManager.getInstance().isMemberInGame(mate)) {
                    e.getChannel().sendMessage("The member you mentioned is already in a game").queue();
                    return;
                }
                TicTacToe ticTacToe = new TicTacToe(e.getMember(), mate, e.getChannel());
                if (!GameManager.getInstance().registerGame(ticTacToe))
                    e.getChannel().sendMessage("There are too many games running currently, please try again later").queue();
                break;
            case "guess":
                Guess guess = new Guess(e.getMember(), e.getChannel());
                if (!GameManager.getInstance().registerGame(guess))
                    e.getChannel().sendMessage("There are too many games running currently, please try again later").queue();
                break;
        }
    }
}
