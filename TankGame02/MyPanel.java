package tankgame;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    tankgame.myTank myTank = null;
    public MyPanel(){
        myTank = new myTank(100,100);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drawTank(myTank.getX(), myTank.getY(), g,0,0);
    }

    /**
     *
     * @param x   坐标x
     * @param y   坐标y
     * @param g 画笔
     * @param direct  方向
     * @param type  类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0:   //mine
                g.setColor(Color.cyan);
                break;
            case 1:  //enemy
                g.setColor(Color.yellow);
                break;
        }

        switch (direct){  //drawtank
            case 0:
                g.fill3DRect(x, y, 10,60,false);  //left
                g.fill3DRect(x+30, y, 10,60,false);  //right
                g.fill3DRect(x+10, y+10, 20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            default:
                System.out.println("no use");
        }

    }
}
