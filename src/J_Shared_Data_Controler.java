
public class J_Shared_Data_Controler {
    static int iShared = 0;

    synchronized static void add(char id, int adder){

        int tmp =iShared;
        iShared+=adder;
        J_Queue.add(id, tmp, iShared);
    }
}
