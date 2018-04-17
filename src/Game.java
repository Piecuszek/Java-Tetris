import objects.Block;
import objects.blocks.*;
import objects.enums.Movements;
import objects.enums.Rotation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Game {
    protected int x, y;
    protected Color[][] board;
    protected long score;
    protected ArrayList<Block> list;

    protected Block current;
    protected int nextBlock;
    private JPanel panel1;

    public Game() {
    }

    public void init() {
        x = 12;
        y = 20;
        int startPosition = x / 2 - 2;

        // dodanie wszystkich rodzajów klocków do listy
        list.add(new IBlock(startPosition, 0));
        list.add(new JBlock(startPosition, 0));
        list.add(new LBlock(startPosition, 0));
        list.add(new TBlock(startPosition, 0));
        list.add(new SBlock(startPosition, 0));
        list.add(new ZBlock(startPosition, 0));
        list.add(new OBlock(startPosition, 0));

        // stworzenie planszy
        board = new Color[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (i == 0 || i == (y - 1) || j == (x - 1)) {
                    board[i][j] = Color.BLACK;
                } else {
                    board[i][j] = Color.GRAY;
                }
            }
        }

        Random random = new Random();
        nextBlock = random.nextInt(7);
        current = list.get(random.nextInt(7));
    }

    protected boolean isFullRow(int index) {
        // sprawdzenie czy wiersz o danym indeksie jest pusty
        for (int j = 1; j < (x - 1); j++) {
            if (board[index][j] == Color.GRAY) {
                return false;
            }
        }
        return true;
    }

    protected void clearRows(int index) {
        // czyszczenie wierszy od 1 do indeksu
        for (int i = index; i > 1; i--) {
            for (int j = 1; j < (x - 1); j++) {
                board[i][j] = board[i - 1][j];
            }
        }
    }

    protected void checkRows() {
        // sprawdzenie wierszy
        int clears = 0;
        for (int i = 1; i < (y - 1); i++) {
            if (isFullRow(i)) {
                clearRows(i);
                clears++;
            }
        }

        // zwiększenie wyniku
        switch (clears) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 600;
                break;
            case 4:
                score += 1000;
                break;
        }
    }

    protected boolean canBeMoved(Block temp) {
        // sprawdzenie czy możliwe jest umieszczenie klocka w obecnej pozycji na planszy
        for (Point[] p : temp.getStructure()) {
            if ( board[temp.getY() + p[temp.getRotation()].y]
                    [temp.getX() + p[temp.getRotation()].x] != Color.GRAY){
                return false;
            }
        }
        return true;
    }

    protected void move(Movements movements) {
        // ruch klocka
        Block temp = current;

        switch (movements){
            case MOVE_DOWN: // w dół
                temp.setY(temp.getY() + 1);
                break;
            case MOVE_LEFT: // w lewo
                temp.setX(temp.getX() - 1);
                break;
            case MOVE_RIGHT: // w prawo
                temp.setX(temp.getX() + 1);
                break;
            case TURN_LEFT: // obrót w lewo
                temp.rotate(-1);
                break;
            case TURN_RIGHT: //obrót w prawo
                temp.rotate(1);
                break;
        }

        if (canBeMoved(temp)) {
            current = temp;
        }
    }

    protected boolean isDropped(int index) {
        for (Point[] p : current.getStructure()) {
            if ( board[current.getY() + p[current.getRotation()].y + 1]
                    [current.getX() + p[current.getRotation()].x] != Color.GRAY){
                return true;
            }
        }
        return false;
    }

    protected void addCurrentBlockToTheBoard() {
        // dodanie opadniętego klocka do planszy
        for (Point[] p : current.getStructure()) {
            board[current.getY() + p[current.getRotation()].y]
                    [current.getX() + p[current.getRotation()].x] = current.getColor();
        }

        // zwiększenie wyniku
        score += 100;
    }

    protected void generateNextBlock() {
        // wygenerowanie nowego klocka
        current = list.get(nextBlock);

        Random random = new Random();
        nextBlock = random.nextInt(7);
    }

}
