import lombok.Data;

/**
 * Class Token for every token in the game
 */
@Data
public class Token {
    private int index;
    private int value;

    public Token(int index, int value) {
        this.index = index;
        this.value = value;
    }
}
