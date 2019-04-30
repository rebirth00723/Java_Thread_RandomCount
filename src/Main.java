
public class Main {

    public static void main(String args[]){

        J_Printer_T tPrinter = new J_Printer_T();
        J_Timer_T tTimer = new J_Timer_T();

        Thread threads[] = {new J_RandomCount_T('A')
                        , new J_RandomCount_T('B')
                        , new J_RandomCount_T('C')};
        try {
            tPrinter.start();
            tTimer.start();

            for(Thread t:threads){
                t.start();
            }

            for(Thread t:threads){
                t.join();
            }
            tPrinter.terminate();
            tTimer.terminate();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
