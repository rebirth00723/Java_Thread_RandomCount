public class J_Timer_T extends Thread{

    private boolean iControler;
    private static int iSec;
    private static int maxTime;
    J_Timer_T(){
        System.out.println("Timer active.");
        iSec = -1;
        maxTime = 16;
        iControler = true;
    }

    public void terminate(){
        iControler = false;
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Timer close.");
    }

    public static boolean isTimeout(){

        return (iSec - maxTime) >= 0;
    }

    public static int getiSec(){
        return iSec;
    }

    @Override
    public void run(){
        while(iControler && iSec <= maxTime){
            try {
                iSec+=1;
                J_Queue.add('D', iSec);
                for(int i = 0 ;i < 4; i++){
                    if(iControler && iSec <= maxTime)
                        Thread.sleep(250);
                    else break;

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
