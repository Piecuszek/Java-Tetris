package blocks;

import java.awt.*;

public class SBlock extends Block {

    public SBlock(int x, int y) {
        super(x, y);

        Structure = new Point[][]{
                {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
                {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
                {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
                {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
        };
        this.color = Color.BLUE;
    }
}

