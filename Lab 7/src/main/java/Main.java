import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Insert the number of players:");

            int nrPlayers = input.nextInt();
            System.out.println("Insert the number of tokens (n):");

            int nrTokens = input.nextInt();

            Game game = new Game(nrPlayers, nrTokens);
            game.startGame();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
