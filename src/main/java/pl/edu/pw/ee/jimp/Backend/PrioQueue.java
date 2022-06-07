package pl.edu.pw.ee.jimp.Backend;

import java.util.ArrayList;
import java.util.HashMap;

public class PrioQueue {
    ArrayList<Integer> pq = new ArrayList<Integer>();

    PrioQueue copy(Graph graph) {
        PrioQueue temp = new PrioQueue();
        for (int i = 0; i < graph.size(); i++) {
            temp.add(i);
        }
        return temp;
    }

    void add(int i) {
        pq.add(i);
    }

    int size() {
        return pq.size();
    }

    int pop(HashMap<Integer, Double> comp) {
        int lowest_ind = 0;
        double lowest_dist = 999999.0;
        for (int i : pq) {
            if (comp.get(i) < lowest_dist) {
                lowest_dist = comp.get(i);
                lowest_ind = i;
            }
        }
        pq.remove(lowest_ind);
        return lowest_ind;
    }

    public String toString() {
        return pq.toString();
    }
}
