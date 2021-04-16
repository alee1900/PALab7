import Exceptions.InvalidNumber;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class Game, defines the TSP game
 */
@Data
public class Game {
    private int nrPlayers;
    private int nrTokens;
    private List<Player> players;
    private List<Token> tokens;
    ExecutorService executor;

    public Game(int nrPlayers, int nrTokens) throws InvalidNumber {
        if (nrPlayers <= 0) {
            throw new InvalidNumber(nrPlayers + " is not a valid number of players!");
        }
        if (nrTokens <= 0) {
            throw new InvalidNumber(nrTokens + " is not a valid number of tokens!");
        }
        this.nrPlayers = nrPlayers;
        this.nrTokens = nrTokens;
        players = new ArrayList<>(nrPlayers);
        tokens = new CopyOnWriteArrayList<>();
        executor = Executors.newFixedThreadPool(nrPlayers);
    }

    /**
     * Method to add players to a game
     */
    public void addPlayers() {
        for (int i = 0; i < nrPlayers; i++) {
            players.add(new Player("Player" + i));
        }
    }

    /**
     * Method to add tokens to a game
     */
    public void addTokens() {
        for (int i = 1; i <= nrTokens; i++) {
            Random rand = new Random();
            int j = rand.nextInt(nrTokens) + 1;
            tokens.add(new Token(i, j));
        }
    }

    /**
     * Method to play the game in turns
     */
    public synchronized void playGame() {
        while (tokens.size() > 0) {
            for (int i = 0; i < nrPlayers; i++) {
                if (tokens.size() > 0) {
                    executor.execute(new Turn(players.get(i), tokens));
                }
            }
        }
    }

    /**
     * Method to print the tokens of each player at the end of the game
     */
    public void printResult() {
        System.out.println("The game is over. These are the players and their tokens:");
        for (Player player : players) {
            player.printAllTokens();
        }
    }

    /**
     * Method to calculate the scores of all players
     */
    public void calculateScores() {
        for (Player player : players) {
            player.calculateScore();
        }
    }

    /**
     * Method to determine the ranking at the end of the game
     */
    public void determineRanking() {
        Collections.sort(players, (p1, p2) -> p1.getScore() > p2.getScore() ? -1 : 1);
        System.out.println("This is the ranking:");
        for (Player player : players) {
            System.out.println(player.getName() + " has obtained " + player.getScore() + " points");
        }
    }

    /**
     * Method to start the game
     */
    public void startGame() {
        addPlayers();
        addTokens();
        playGame();
        printResult();
        calculateScores();
        determineRanking();
    }

    /**
     * Method to end game when time ends
     */
    public void endGame() {
        executor.shutdownNow();
        printResult();
        calculateScores();
        determineRanking();
        System.exit(1);
    }
}
