package objects.blocks;

import objects.Block;
import objects.enums.Rotation;

import java.awt.*;
import java.util.Arrays;

public class IBlock extends Block{

    public IBlock(int x, int y) {
        super(x, y);

        Structure = new Point[][]{
                {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
                {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
                {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)}
        };
        this.color = Color.RED;
    }
}
