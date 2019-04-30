import java.util.Random;

public class J_RandomCount_T extends Thread{

    char id;

    public J_RandomCount_T(char name){
        this.id = name;
        J_Queue.add(id, 0);
    }

    public int getRand(){
        Random rand;
        int iRandNum;

        rand = new Random();
        iRandNum = rand.nextInt(3)+1;
        return iRandNum;
    }
    @Override
    public void run() {

        for(int i = 0 , r = getRand(); i < 1; i++, r=getRand()){

            int iSec;

            iSec = J_Timer_T.getiSec();

            J_Queue.add(id, r);

            while(J_Timer_T.getiSec()-iSec < r
                    && !J_Timer_T.isTimeout()){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            J_Shared_Data_Controler.add(id, r);

        }
    }
}
