import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Player for each player in the game
 */
@Data
public class Player {
    private String name;
    private List<Token> playersTokens;

    public Player(String name) {
        this.name = name;
        this.playersTokens = new ArrayList<>();
    }

    /**
     * Method to add chosen token to the player's token list
     * @param token represents the token to be added
     */
    public void addToken(Token token) {
        playersTokens.add(token);
    }

    /**
     * Method to print all of the player's tokens
     */
    public void printAllTokens() {
        System.out.println(name + "has the following tokens:");
        for (Token token : playersTokens) {
            System.out.println("[" + token.getIndex() + "," + token.getValue() + "]");
        }
    }
}
