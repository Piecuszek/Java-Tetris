package objects.blocks;

import objects.Block;

import java.awt.*;

public class ZBlock extends Block {

    public ZBlock(int x, int y) {
        super(x, y);

        Structure = new Point[][]{
                {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
                {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)}
        };
        this.color = Color.GREEN;
    }
}