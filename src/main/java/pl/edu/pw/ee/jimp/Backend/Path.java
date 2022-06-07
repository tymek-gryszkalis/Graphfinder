package pl.edu.pw.ee.jimp.Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Path {
    public ArrayList<Integer> vertexPath;
    static final double INFINITY = 99999;

    public Path() {
        vertexPath = new ArrayList<Integer>();
    }

    public void dijkstra(Graph graph, int beg, int end) {
        HashMap<Integer, Double> distance = new HashMap<Integer, Double>();
        HashMap<Integer, Integer> previous = new HashMap<Integer, Integer>();
        PrioQueue pq = new PrioQueue();
        for (int i = 0; i < graph.size(); i++) {
            distance.put(i, INFINITY);
            previous.put(i, -1);
        }
        distance.remove(beg);
        distance.put(beg, 0.0);
        pq = pq.copy(graph);

        int u;
        while (pq.size() > 0) {
            u = pq.pop(distance);
            ArrayList<Integer> vers = (ArrayList)graph.get(u).get("Vers");
            ArrayList<Integer> cons = (ArrayList)graph.get(u).get("Cons");
            for (int i = 0; i < vers.size(); i++) {
                if (distance.get(vers.get(i)) > distance.get(u) + cons.get(i)) {
                    distance.remove(vers.get(i));
                    distance.put(vers.get(i), distance.get(u) + cons.get(i));
                    previous.remove(vers.get(i));
                    previous.put(vers.get(i), u);
                }
            }
        }

        int indx = end;
        while(previous.get(indx) != -1) {
            vertexPath.add(previous.get(indx));
        }
        Collections.reverse(vertexPath);
    }
}
