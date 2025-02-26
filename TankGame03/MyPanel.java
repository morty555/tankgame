package TankGame03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;

public class MyPanel extends JPanel implements KeyListener,Runnable{
  myTank myTank = null;
  Vector<Boom> booms = new Vector<Boom>();
  int enemytankssize=3;

  Image image1 = null;
  Image image2 = null;
  Image image3 = null;
  Vector<EnemyTank> enemyTanks = new Vector();
    public MyPanel(){
        myTank = new myTank(100,100);
        myTank.setSpeed(2);
        for (int i = 0; i < enemytankssize; i++) {
            EnemyTank enemyTank = new   EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            new Thread(enemyTank).start();

           shot shot = new shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();

            enemyTanks.add(enemyTank);


        }

        //image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image1.png"));
        //image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.png"));
        //image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.png"));
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.fillRect(0,0,1000,750);


        if(myTank.shot!=null&&myTank.shot.isLive==true){
            g.draw3DRect(myTank.shot.x, myTank.shot.y,3,3,false);
        }
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            if(boom.life>6){
                g.drawImage(image1,boom.x,boom.y,60,60,this);
            }
            else if(boom.life>3){
                g.drawImage(image2,boom.x,boom.y,60,60,this);
            }
            else {
                g.drawImage(image3,boom.x,boom.y,60,60,this);
            }
            boom.lifeDown();
            if(boom.life==0){
                booms.remove(boom);
            }
        }

        for (int i = 0; i <enemyTanks.size() ; i++) {
            EnemyTank enemyTank = enemyTanks.get(i);

            drawTank(myTank.getX(), myTank.getY(), g,myTank.getDirect(),1);
            if(enemyTank.isLive){
                drawTank(enemyTank.getX(), enemyTank.getY(), g,enemyTank.getDirect(),0);
                for (int j = 0; j < enemyTank.shots.size(); j++) {

                    shot shot = enemyTank.shots.get(j);
                    // shot shot = enemyTank.shots.get(0);
                    if(shot.isLive){
                        g.draw3DRect(shot.x, shot.y,3,3,false);
                    }
                    else{
                        enemyTank.shots.remove(shot);
                    }

                }
            }


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
    public  void hitTank(shot s,EnemyTank enemyTank){
        switch (enemyTank.getDirect()){
            case 0:
            case 2:
                if(s.x>enemyTank.getX()&&s.x<enemyTank.getX()+40&&s.y>enemyTank.getY()&&s.y<enemyTank.getY()+60){
                    s.isLive=false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);

                }
                break;
            case 1:
            case 3:
                if(s.x>enemyTank.getX()&&s.x<enemyTank.getX()+60&&s.y>enemyTank.getY()&&s.y<enemyTank.getY()+40){
                    s.isLive=false;
                    enemyTank.isLive = false;
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    booms.add(boom);
                }
                break;





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

       if(e.getKeyCode()==KeyEvent.VK_J){
           myTank.shotEnemyTank();
       }

       this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void run(){
        while (true){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(myTank.shot!=null&&myTank.shot.isLive){
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank =enemyTanks.get(i);
                hitTank(myTank.shot,enemyTank);
                
            }
        }

        this.repaint();}
    }
}
