package blocks;

import java.awt.*;

public class IBlock extends Block{

    public IBlock(int x, int y) {
        super(x, y);

        Structure = new Point[][]{
                {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)},
                {new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)},
                {new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3)},
                {new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0)}
        };
        this.color = Color.RED;
    }
}
