import javafx.util.Pair;

import java.util.*;
public class J_Queue {
    private static Queue<Pair<Character, Pair<Integer, Integer>>> queue = new LinkedList<>();

    public static boolean hasData(){
        return !queue.isEmpty();
    }

    public static void add(char id, int iSec){
        add(id, iSec, 0);
    }
    synchronized public static void add(char id, int A, int B){
        queue.add(new Pair<>(id, new Pair<>(A, B)));
    }
    public static Pair<Character, Object> get(){
        char a;
        Object b;
        int keyword;
        Pair<Character, Pair<Integer, Integer>> data;

        data = queue.poll();
        if(data != null){
            a = data.getKey();
            keyword = data.getValue().getValue();


            if(keyword == 0){
                b = data.getValue().getKey();
            }else
                b = data.getValue();

            return  new Pair<>(a, b);
        }

        return null;
    }


}
