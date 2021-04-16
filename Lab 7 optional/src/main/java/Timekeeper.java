import Exceptions.InvalidNumber;
import lombok.Data;

/**
 * Class Timekeeper, used to keep the time during the game
 * Ends game when time is up
 */
@Data
public class Timekeeper implements Runnable {
    private Game game;
    private long duration;
    private long startTime;

    public Timekeeper(Game game, long duration) throws InvalidNumber {
        if (duration <= 0) {
            throw new InvalidNumber(duration + " is not a valid duration time!");
        }
        this.game = game;
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            long passedTime = currentTime - startTime;
            long remainingTime = duration - passedTime;
            if (remainingTime < 0) {
                System.out.println("No time left!");
                game.endGame();
                return;
            }
        }
    }
}
