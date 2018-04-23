package blocks;

import enums.Rotation;

import java.awt.*;

public class Block {

    //
    public int count;

    protected int x, y;

    protected Color color;
    protected Rotation rotation;


    protected Point Structure[][];

    // CONSTRUCTORS
    public Block() {
    }

    public Block(int x, int y) {
        count++;

        this.x = x;
        this.y = y;

        this.rotation = Rotation.S;
        this.color = java.awt.Color.WHITE;
    }

    public void rotate() {
        rotation = Rotation.values()[(int) ((rotation.ordinal() + 1) % Rotation.values().length)];
    }

    public void show(int number) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                boolean b = false;
                for (Point p : Structure[number]) {
                    if (p.x == x && p.y == y) {
                        b = true;
                    }
                }

                if (b) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showRow(int number) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                boolean b = false;
                for (Point p : Structure[0]) {
                    if (p.x == x && p.y == y) {
                        b = true;
                    }
                }

                if (b) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // GETTERS AND SETTERS
    public int getRotation() {
        return rotation.ordinal();
    }

    public void setRotation(int rotation) {
        this.rotation = Rotation.values()[rotation];
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point[][] getStructure() {
        return Structure;
    }

    public void setStructure(Point[][] structure) {
        Structure = structure;
    }
}
