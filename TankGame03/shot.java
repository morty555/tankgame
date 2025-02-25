package TankGame03;

import javax.swing.plaf.SplitPaneUI;

public class shot implements Runnable{
    int x;
    int y;
    int direct=0;
    int speed=2;

    boolean isLive = true;

    public shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct){
                case 0:
                    y-= speed;
                    break;
                case 1:
                    x+= speed;
                    break;
                case 2:
                    y+= speed;
                    break;
                case 3:
                    x-= speed;
                    break;
            }
            if(!(x>0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive = false;
                break;
            }
        }

    }

}
