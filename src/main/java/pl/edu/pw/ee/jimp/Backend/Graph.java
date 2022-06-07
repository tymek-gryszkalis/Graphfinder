package pl.edu.pw.ee.jimp.Backend;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    HashMap<Integer, HashMap<String, Object>> graph;

    public Graph() {
        graph = new HashMap<Integer, HashMap<String, Object>>();
    }

    public void add(int adress, ArrayList<Integer> vertices, ArrayList<Double> weights) {
        HashMap<String, Object> temp = new HashMap<String, Object>();
        temp.put("Cons", weights);
        temp.put("Vers", vertices);
        graph.put(adress, temp);
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    public int size() {
        return graph.size();
    }

    public HashMap get(int x) {
        return graph.get(x);
    }

    public void remove(int x) {
        graph.remove(x);
    }

    public Graph clone() {
        Graph temp = new Graph();
        for (int i = 0; i < graph.size(); i++) {
            ArrayList<Integer> vers = (ArrayList)graph.get(i).get("Vers");
            ArrayList<Double> cons = (ArrayList)graph.get(i).get("Cons");
            temp.add(i, vers, cons);
        }
        return temp;
    }

    public ArrayList<Integer> axis() {
        ArrayList<Integer> axis = new ArrayList<Integer>();
        int num = 0;
        int indx = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (indx == 2) {
                break;
            }
            ArrayList<Integer> temp = (ArrayList)graph.get(i).get("Cons");
            if (temp.size() == 2) {
                num = i;
                indx++;
            }
        }
        axis.add(num + 1);
        axis.add(graph.size() / (num + 1));

        return axis;
    }

}
