package at.shadowbot.game.games;

import at.shadowbot.game.Game;
import at.shadowbot.game.GameManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Arrays;

public class TicTacToe extends Game {

    private TextChannel channel;

    private Member currentPlayer;

    private boolean running = true;

    private final Member memberO;
    private final Member memberX;

    private String[] field = new String[]{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "};

    public TicTacToe(Member memberO, Member memberX, TextChannel channel) {
        super("TicTacToe");

        setInvolvedPlayers(Arrays.asList(new Member[]{memberO, memberX}));

        this.channel = channel;
        this.memberO = memberO;
        this.memberX = memberX;


        this.currentPlayer = memberO;
        drawField(true);
    }

    @Override
    public void processInput(Message message) {
        if (!message.getChannel().getId().equalsIgnoreCase(channel.getId()))
            return;
        if (message.getContentRaw().equalsIgnoreCase("cancel")) {
            GameManager.getInstance().unregisterGame(this);
            message.getChannel().sendMessage("The Game of TicTacToe got cancelled").queue();
            return;
        }
        if (isInt(message.getContentRaw()) && Integer.parseInt(message.getContentRaw()) < 10 && Integer.parseInt(message.getContentRaw()) > 0) {
            if (message.getMember().getId().equalsIgnoreCase(currentPlayer.getId())) {
                if (!setFieldIfPossible(Integer.parseInt(message.getContentRaw()))) {
                    message.getChannel().sendMessage("This spot is already used! Please try another one").queue();
                } else {
                    drawField(running);
                }
            }
        } else {
            if (message.getMember().getId().equalsIgnoreCase(currentPlayer.getId())) {
                message.getChannel().sendMessage("The Message you entered was not a valid Number!").queue();
            }
        }
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean setFieldIfPossible(int index) {
        if (this.field[index - 1].trim().equalsIgnoreCase("")) {
            String c = getCharForPlayer(currentPlayer);
            this.field[index - 1] = c;
            int winner = getWinner();
            if (winner == 0) {
                currentPlayer = currentPlayer.getId().equals(memberO.getId()) ? memberX : memberO;
                return true;
            } else if (winner == 3) {
                channel.sendMessage("The Game ended in a Draw! GG").queue();
                running = false;
                GameManager.getInstance().unregisterGame(this);
            } else if (winner == 1 || winner == 2) {
                channel.sendMessage("The Game ended! The winner is " + (winner == 1 ? memberO.getAsMention() + " ~ 0" : memberX.getAsMention() + " ~ X")).queue();
                running = false;
                GameManager.getInstance().unregisterGame(this);
            }
            return true;
        } else {
            return false;
        }
    }

    private String getCharForPlayer(Member member) {
        return member.getId().equals(memberO.getId()) ? "0" : "X";
    }

    private void drawField(boolean next) {
        String field = "__**TicTacToe ~ " + memberO.getEffectiveName() + " ~ 0 | " + memberX.getEffectiveName() + " ~ X**__" + "\n\n";
        for (int i = 0; i < 3; i++) {
            if (i < 2)
                field = field + "__";
            for (int j = 0; j < 3; j++) {
                String s = this.field[(i * 3) + j];
                field = field + "   " + s + "   ";
                if (j < 2) {
                    field = field + "|";
                }
            }
            if (i < 2)
                field = field + "__";
            field = field + "\n";
        }
        if (next) {
            field = field + "\nThe current Player is: " + currentPlayer.getAsMention() + " ~ " + getCharForPlayer(currentPlayer);
            field = field + "\nPlease use `cancel` to stop the game, or use the Numbers 1 - 9 to select where you want to place your character";
        }
        channel.sendMessage(field).queue();
    }

    private int getWinner() {
        //check for draw
        boolean draw = true;
        for (int i = 0; i < field.length; i++) {
            if (field[i].trim().equalsIgnoreCase(""))
                draw = false;
        }
        if (draw)
            return 3;
        //check for memberO win horizontal
        if ((cf(0, "0") && cf(1, "0") && cf(2, "0")) || (cf(3, "0") && cf(4, "0") && cf(5, "0")) || (cf(6, "0") && cf(7, "0") && cf(8, "0")))
            return 1;
        //check for member0 win vertical
        if ((cf(0, "0") && cf(3, "0") && cf(6, "0")) || (cf(1, "0") && cf(4, "0") && cf(7, "0")) || (cf(2, "0") && cf(5, "0") && cf(8, "0")))
            return 1;

        //check for member0 win diagonal
        if (field[0].equalsIgnoreCase("0") && field[4].equalsIgnoreCase("0") && field[8].equalsIgnoreCase("0"))
            return 1;
        if (field[2].equalsIgnoreCase("0") && field[4].equalsIgnoreCase("0") && field[6].equalsIgnoreCase("0"))
            return 1;

        //check for memberX win horizontal
        if ((cf(0, "X") && cf(1, "X") && cf(2, "X")) || (cf(3, "X") && cf(4, "X") && cf(5, "X")) || (cf(6, "X") && cf(7, "X") && cf(8, "X")))
            return 2;
        //check for memberX win vertical
        if ((cf(0, "X") && cf(3, "X") && cf(6, "X")) || (cf(1, "X") && cf(4, "X") && cf(7, "X")) || (cf(2, "X") && cf(5, "X") && cf(8, "X")))
            return 2;

        //check for memberX win diagonal
        if (field[0].equalsIgnoreCase("X") && field[4].equalsIgnoreCase("X") && field[8].equalsIgnoreCase("X"))
            return 2;
        if (field[2].equalsIgnoreCase("X") && field[4].equalsIgnoreCase("X") && field[6].equalsIgnoreCase("X"))
            return 2;
        return 0;
    }

    private boolean cf(int i, String s) {
        return field[i].equalsIgnoreCase(s);
    }
}
