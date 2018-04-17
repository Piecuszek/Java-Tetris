package objects;

import objects.Block;
import objects.blocks.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Master extends JPanel{

//    @Override
//    public void paint(Graphics g) {
//        // TODO Auto-generated method stub
//        super.paint(g);
//        g.setColor(Color.RED);
//        g.fillOval(x, y, 40, 40);
//        g.setColor(Color.BLUE);
//        g.drawLine((int)1.5*x, 2*y, 90, 40);
//
//    }


    public static void main(String[] args) {

        ArrayList<Block> list = new ArrayList<Block>() ;
        list.add(new IBlock(0,0));
        list.add(new JBlock(0,0));
        list.add(new LBlock(0,0));
        list.add(new TBlock(0,0));
        list.add(new SBlock(0,0));
        list.add(new ZBlock(0,0));
        list.add(new OBlock(0,0));

        for (Block b : list) {
            b.show(0);
            b.show(1);
            b.show(2);
            b.show(3);
        }
    }
}
