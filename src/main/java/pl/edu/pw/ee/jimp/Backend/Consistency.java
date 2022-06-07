package pl.edu.pw.ee.jimp.Backend;

import java.util.ArrayList;

public class Consistency {
    static public boolean bfs(Graph graph) {
        ArrayList<Integer> queue = new ArrayList();
        ArrayList<Integer> beento = new ArrayList();
        queue.add(0);

        while (queue.size() > 0) {
            int curver = queue.get(0);
            queue.remove(0);
            beento.add(curver);

            ArrayList<Integer>vers = (ArrayList<Integer>)graph.get(curver).get("Vers");
            for (int i = 0; i < vers.size(); i++) {
                if (beento.contains(vers.get(i)) || queue.contains(vers.get(i))) {
                    continue;
                }
                queue.add(vers.get(i));
            }
        }
        if (beento.size() == graph.size()) {
            return true;
        } else {
            return false;
        }
    }
}
