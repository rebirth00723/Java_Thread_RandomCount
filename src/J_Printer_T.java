import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class J_Printer_T extends Thread{
    private Queue<Thread> threadQ;

    private boolean bConterol;
    private int time;

    J_Printer_T(){
        System.out.println("Printer active.");
        threadQ = new LinkedList<>();
        time = 0;
        bConterol = true;
    }
    public void terminate(){
        bConterol = false;
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Printer close.");
    }

    @Override
    public void run(){

        while((bConterol || J_Queue.hasData())
                && !J_Timer_T.isTimeout()
                ){

            Thread thread;

            Pair<Character, Object> data;
            Object dataValue;

            if(J_Queue.hasData()) {

                data = J_Queue.get();
                if (data != null) {
                    dataValue = data.getValue();
                    if (dataValue instanceof Integer)
                        thread = new Thread(() -> printerB(data));
                    else
                        thread = new Thread(() -> printerA(data));

                    thread.start();
                    threadQ.add(thread);
                }
            }


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.terminate();
            }
        }

        while(!threadQ.isEmpty()){
            try {
                threadQ.poll().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void printerA(Pair<Character, Object> data){

        Pair<Integer, Integer> countPair;

        char id;
        int countA;
        int countB;

        countPair = (Pair<Integer, Integer>)data.getValue();

        id = data.getKey();
        countA = countPair.getKey();
        countB = countPair.getValue();

        System.out.println(id + " : " + countA + " -> " + countB);
    }

    private void printerB(Pair<Character, Object> data){
        char id;
        int sec;
        id = data.getKey();
        sec = (Integer) data.getValue();

        switch (id){
            case 'A':
            case 'B':
            case 'C':
                if (sec == 0){
                    System.out.println(id+" Start!");
                }else{
                    System.out.println("["+id+"] Wait "+sec+" sec to add.");
                }
                break;
            case 'D':
                System.out.println("-----------------------"+sec+" sec");
                break;
            default:
                break;
        }
    }
}
