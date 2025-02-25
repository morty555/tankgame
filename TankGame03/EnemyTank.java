package TankGame03;

import java.util.Vector;

public class EnemyTank extends Tank {
    Vector<shot> shots = new Vector<shot>();
    boolean isLive = true;
    public EnemyTank(int x,int y){
        super(x,y);
    }
}
