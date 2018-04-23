import blocks.Block;
import blocks.*;
import enums.Movements;
import enums.Rotation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel {
    protected int x, y;
    protected Color[][] board;
    protected long score;
    protected Block current;
    protected int nextBlock;
    private JPanel panel1;
    boolean stop;

    public Game() {
    }

    public void init() {
        stop = false;
        x = 12;
        y = 20;
        int startPosition = x / 2 - 2;

        // stworzenie planszy
        board = new Color[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (j == 0 || i == (y - 1) || j == (x - 1)) {
                    board[i][j] = Color.BLACK;
                } else {
                    board[i][j] = Color.GRAY;
                }
            }
        }

        Random random = new Random();
        nextBlock = random.nextInt(7);
        generateNextBlock();
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
                score += 50;
                break;
            case 2:
                score += 150;
                break;
            case 3:
                score += 300;
                break;
            case 4:
                score += 500;
                break;
        }
    }

    protected boolean canBeMoved() {
        // sprawdzenie czy możliwe jest umieszczenie klocka w obecnej pozycji na planszy
        for (Point p : current.getStructure()[current.getRotation()]) {
            if ((current.getY() + p.y) >= (y - 1) || (current.getX() + p.x) >= (x - 1) || (current.getX() + p.x <= 0)) {
                // wyjście poza zakres
                return false;
            }

            if (board[current.getY() + p.y]
                    [current.getX() + p.x] != Color.GRAY) {
                return false;
            }
        }
        return true;
    }

    public void move(Movements movements) {
        // ruch klocka
        int t_x = current.getX();
        int t_y = current.getY();
        int t_r = current.getRotation();

        switch (movements) {
            case MOVE_DOWN: // w dół
                if (current.getY() == y - 2) {
                    fixBoard();
                    break;
                } else {
                    current.setY(current.getY() + 1);
                    if (!canBeMoved()) {
                        current.setY(t_y);
                        fixBoard();
                    }
                }
                break;
            case MOVE_LEFT: // w lewo
                if (current.getX() == 1) {
                    return;
                } else {
                    current.setX(current.getX() - 1);
                    if (!canBeMoved()) {
                        current.setX(t_x);
                    }
                }
                break;
            case MOVE_RIGHT: // w prawo
                if (current.getX() == x - 2) {
                    return;
                } else {
                    current.setX(current.getX() + 1);
                    if (!canBeMoved()) {
                        current.setX(t_x);
                    }
                }
                break;
            case ROTATE: // obrót
                current.rotate();
                if (!canBeMoved()) {
                    current.setRotation(t_r);
                }
                break;
        }
    }

    protected boolean canBeAdded() {
        // sprawdzenie czy jest miejsce na nowy klocek
        for (int i = (x / 2 - 2); i < (x / 2 + 2); i++) {
            for (int j = 0; j < 4; j++)
            if (board[j][i] != Color.GRAY) {
                stop = true;
                return false;
            }
        }
        return true;
    }


    protected void fixBoard() {
        //rozstawienie planszy
        addCurrentBlockToTheBoard();
        checkRows();

        if(canBeAdded()){
            generateNextBlock();
        }
    }

    protected void addCurrentBlockToTheBoard() {
        // dodanie opadniętego klocka do planszy
        for (Point p : current.getStructure()[current.getRotation()]) {
            board[current.getY() + p.y]
                    [current.getX() + p.x] = current.getColor();
        }

        // zwiększenie wyniku
        if(!stop){
            score += 10;
        }
    }

    protected void generateNextBlock() {
        // wygenerowanie nowego klocka

        int startPosition = x / 2 - 2;

        switch (nextBlock) {
            case 1:
                current = new IBlock(startPosition, 0);
                break;
            case 2:
                current = new JBlock(startPosition, 0);
                break;
            case 3:
                current = new LBlock(startPosition, 0);
                break;
            case 4:
                current = new TBlock(startPosition, 0);
                break;
            case 5:
                current = new SBlock(startPosition, 0);
                break;
            case 6:
                current = new ZBlock(startPosition, 0);
                break;
            default:
                current = new OBlock(startPosition, 0);
                break;
        }

        Random random = new Random();
        nextBlock = random.nextInt(7);
        nextBlock = random.nextInt(7);
    }

}
