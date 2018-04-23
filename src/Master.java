import blocks.*;
import enums.Movements;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Master extends JPanel {

    static Game game;
    static Master master;

    final BufferedImage iBlack = ImageIO.read(new File("D:\\Inne\\black.png"));
    final BufferedImage iGray = ImageIO.read(new File("D:\\Inne\\gray.png"));
    final BufferedImage iBlue = ImageIO.read(new File("D:\\Inne\\blue.png"));
    final BufferedImage iGreen = ImageIO.read(new File("D:\\Inne\\green.png"));
    final BufferedImage iYellow = ImageIO.read(new File("D:\\Inne\\yellow.png"));
    final BufferedImage iOrange = ImageIO.read(new File("D:\\Inne\\orange.png"));
    final BufferedImage iRed = ImageIO.read(new File("D:\\Inne\\red.png"));
    final BufferedImage iCyan = ImageIO.read(new File("D:\\Inne\\cyan.png"));
    final BufferedImage iMagenta = ImageIO.read(new File("D:\\Inne\\magenta.png"));


    public Master() throws IOException {
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        drawBg(g2d);
        drawBoard(g2d);
        drawBlock(g2d);
        drawNextBlock(g2d);
        drawTexts(g2d);
    }

    public void drawTexts(Graphics2D g2d){
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        g2d.setColor(Color.WHITE);
        g2d.drawString("NEXT BLOCK", 304, 40);
        g2d.drawString("SCORE", 324, 220);
        g2d.drawString(String.valueOf(game.score), 324, 240);

        if(game.stop){
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 28));
            g2d.drawString("GAME OVER!", 260, 340);
        }
    }


    public void drawBg(Graphics2D g2d) {
        g2d.drawImage(iBlack, 0, 0, 480, 400, null);
    }

    public void drawBoard(Graphics2D g2d) {
        for (int y = 0; y < game.y; y++) {
            for (int x = 0; x < game.x; x++) {
//                g2d.setColor(game.board[y][x]);
//                g2d.fillRect(x*20, y*20, 20, 20);

                if (game.board[y][x] == Color.GRAY)
                    g2d.drawImage(iGray, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.BLACK)
                    g2d.drawImage(iBlack, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.BLUE)
                    g2d.drawImage(iBlue, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.GREEN)
                    g2d.drawImage(iGreen, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.CYAN)
                    g2d.drawImage(iCyan, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.MAGENTA)
                    g2d.drawImage(iMagenta, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.RED)
                    g2d.drawImage(iRed, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.YELLOW)
                    g2d.drawImage(iYellow, x * 20, y * 20, 20, 20, null);
                if (game.board[y][x] == Color.ORANGE)
                    g2d.drawImage(iOrange, x * 20, y * 20, 20, 20, null);

            }
        }
    }


    public void drawBlock(Graphics2D g2d) {
        for (Point p : game.current.getStructure()[game.current.getRotation()]
                ) {
            //g2d.setColor(game.current.getColor());
            //g2d.fillRect((int)((game.current.getX() + p.getX())*20), (int)((game.current.getY() + p.getY())*20), 20, 20);

            int t_x = (int) (game.current.getX() + p.getX());
            int t_y = (int) (game.current.getY() + p.getY());

            if (game.current.getColor() == Color.BLUE)
                g2d.drawImage(iBlue, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.GREEN)
                g2d.drawImage(iGreen, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.CYAN)
                g2d.drawImage(iCyan, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.MAGENTA)
                g2d.drawImage(iMagenta, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.RED)
                g2d.drawImage(iRed, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.YELLOW)
                g2d.drawImage(iYellow, t_x * 20, t_y * 20, 20, 20, null);
            if (game.current.getColor() == Color.ORANGE)
                g2d.drawImage(iOrange, t_x * 20, t_y * 20, 20, 20, null);
        }
    }

    public void drawNextBlock(Graphics2D g2d) {
        int x = 16, y = 3;
        Block temp;
        switch (game.nextBlock) {
            case 1:
                temp = new IBlock(x, y);
                break;
            case 2:
                temp = new JBlock(x, y);
                break;
            case 3:
                temp = new LBlock(x, y);
                break;
            case 4:
                temp = new TBlock(x, y);
                break;
            case 5:
                temp = new SBlock(x, y);
                break;
            case 6:
                temp = new ZBlock(x, y);
                break;
            default:
                temp = new OBlock(x, y);
                break;
        }

        for (int i = -1; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g2d.drawImage(iGray, (x+i) * 20, (y+j) * 20, 20, 20, null);
            }
        }

        for (Point p : temp.getStructure()[0]
                ) {
            //g2d.setColor(game.current.getColor());
            //g2d.fillRect((int)((game.current.getX() + p.getX())*20), (int)((game.current.getY() + p.getY())*20), 20, 20);

            int t_x = (int) (temp.getX() + p.getX());
            int t_y = (int) (temp.getY() + p.getY());

            if (temp.getColor() == Color.BLUE)
                g2d.drawImage(iBlue, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.GREEN)
                g2d.drawImage(iGreen, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.CYAN)
                g2d.drawImage(iCyan, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.MAGENTA)
                g2d.drawImage(iMagenta, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.RED)
                g2d.drawImage(iRed, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.YELLOW)
                g2d.drawImage(iYellow, t_x * 20, t_y * 20, 20, 20, null);
            if (temp.getColor() == Color.ORANGE)
                g2d.drawImage(iOrange, t_x * 20, t_y * 20, 20, 20, null);
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        JFrame frame = new JFrame("Tetris");
        game = new Game();
        game.init();
        master = new Master();
        frame.add(master);
        frame.setSize(498, 446);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Keyboard controls
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.move(Movements.ROTATE);
                        master.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        game.move(Movements.MOVE_DOWN);
                        master.repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        game.move(Movements.MOVE_LEFT);
                        master.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.move(Movements.MOVE_RIGHT);
                        master.repaint();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        new Thread() {
            @Override
            public void run() {
                while (!game.stop) {
                    try {
                        game.move(Movements.MOVE_DOWN);
                        master.repaint();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }
}