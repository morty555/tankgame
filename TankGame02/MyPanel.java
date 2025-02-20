package TankGame02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener {
  myTank myTank = null;
  int enemytankssize=3;
  Vector<EnemyTank> enemyTanks = new Vector();
    public MyPanel(){
        myTank = new myTank(100,100);
        myTank.setSpeed(2);
        for (int i = 0; i < enemytankssize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);
        drawTank(myTank.getX(), myTank.getY(), g,myTank.getDirect(),1);

        for (int i = 0; i <enemyTanks.size() ; i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(), enemyTank.getY(), g,enemyTank.getDirect(),0);
        }
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
//direct 0123 up right down left
        switch (direct){  //drawtank
            case 0:
                g.fill3DRect(x, y, 10,60,false);  //left
                g.fill3DRect(x+30, y, 10,60,false);  //right
                g.fill3DRect(x+10, y+10, 20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1:
                g.fill3DRect(x, y, 60,10,false);  //left
                g.fill3DRect(x, y+30, 60,10,false);  //right
                g.fill3DRect(x+10, y+10, 40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2:
                g.fill3DRect(x, y, 10,60,false);  //left
                g.fill3DRect(x+30, y, 10,60,false);  //right
                g.fill3DRect(x+10, y+10, 20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3:
                g.fill3DRect(x, y, 60,10,false);  //left
                g.fill3DRect(x, y+30, 60,10,false);  //right
                g.fill3DRect(x+10, y+10, 40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:
                System.out.println("no use");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_W){
            myTank.setDirect(0);
            myTank.moveUp();

       }else if(e.getKeyCode()==KeyEvent.VK_D){
           myTank.setDirect(1);
           myTank.moveRight();
        }
       else if(e.getKeyCode()==KeyEvent.VK_S){
           myTank.setDirect(2);
           myTank.moveDown();
       }
       else if(e.getKeyCode()==KeyEvent.VK_A){
           myTank.setDirect(3);
           myTank.moveLeft();
       }
       this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
