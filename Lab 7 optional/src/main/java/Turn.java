import lombok.Data;

import java.util.List;
import java.util.Random;

/**
 * Class Turn for every turn in the game
 * Implements Runnable, creates a thread for every turn
 */
@Data
public class Turn implements Runnable {
    private Player player;
    private List<Token> tokens;

    public Turn(Player player, List<Token> tokens) {
        this.player = player;
        this.tokens = tokens;
    }

    @Override
    public void run() {
        Random rand = new Random();
        Token token = tokens.get(rand.nextInt(tokens.size()));
        player.addToken(token);
    }
}
