package pl.edu.pw.ee.jimp.Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Generator {
    private static Random r = new Random();

    private static Graph genFull(int x, int y, double lb, double hb) {
        Graph graph = new Graph();

        int versToGen = x*y;
        for (int i = 0; i < versToGen; i++) {
            ArrayList<Integer> vertices = new ArrayList();
            ArrayList<Double> connections = new ArrayList();
            Random r = new Random();
            int temp;

            // check up
            temp = i - x;
            if (temp >= 0) {
                double con = lb + (hb - lb) * r.nextDouble();
                vertices.add(temp);
                connections.add(con);
            }

            // check right
            temp = i + 1;
            if (temp % x != 0) {
                double con = lb + (hb - lb) * r.nextDouble();
                vertices.add(temp);
                connections.add(con);
            }

            // check down
            temp = i + x;
            if (temp < versToGen) {
                double con = lb + (hb - lb) * r.nextDouble();
                vertices.add(temp);
                connections.add(con);
            }

            // check left
            temp = i - 1;
            if ((temp != -1 && temp % (x) != x - 1) || temp == 0) {
                double con = lb + (hb - lb) * r.nextDouble();
                vertices.add(temp);
                connections.add(con);
            }
            graph.add(i, vertices, connections);
        }
        return graph;
    }

    private static Graph genRandom(Graph graph, int x, int y, int numofbord) {
        int fullAm = x * (y - 1) + y * (x - 1);
        int amToDel = fullAm - numofbord;
        amToDel = amToDel < 0 ? 0 : amToDel;
        amToDel = amToDel > fullAm ? fullAm : amToDel;
        
        int curver = 0;
        for (int i = 0; i < amToDel; i++) {
            curver += 100 * r.nextInt();
            curver %= x * y;

            HashMap temp = graph.get(curver);
            if (((ArrayList<Integer>)temp.get("Vers")).size() == 0) {
                i--;
                continue;
            }
            graph.remove(curver);
            ArrayList<Integer> vers = (ArrayList<Integer>)temp.get("Vers");
            ArrayList<Double> cons = (ArrayList<Double>)temp.get("Cons");
            int indx = vers.size() * r.nextInt();
            int curver_b = vers.get(indx);
            vers.remove(indx);
            cons.remove(indx);
            graph.add(curver, vers, cons);

            temp = graph.get(curver_b);
            graph.remove(curver_b);
            vers = (ArrayList<Integer>)temp.get("Vers");
            cons = (ArrayList<Double>)temp.get("Cons");
            indx = vers.indexOf(curver);
            vers.remove(indx);
            cons.remove(indx);
            graph.add(curver_b, vers, cons);
        }

        return graph;
    }

    private static Graph genConsistent(Graph graph, int x, int y, int numofbord) {
        Graph newgraph = graph.clone();
        while (Consistency.bfs(newgraph)) {
            newgraph = genRandom(graph, x, y, numofbord);
            System.out.println(newgraph);
            newgraph = graph.clone();
            continue;     
        }
        return newgraph;
    }

    public static Graph genGraph(int x, int y, double lb, double hb, int numofbord, boolean isConsistent) {
        Graph graph = genFull(x, y, lb, hb);
        if (isConsistent) {
            graph = genConsistent(graph, x, y, numofbord);
        } else {
            graph = genRandom(graph, x, y, numofbord);
        }

        return graph;
    }
}
