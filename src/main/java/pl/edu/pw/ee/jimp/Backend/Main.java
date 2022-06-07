package pl.edu.pw.ee.jimp.Backend;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Graph graphCon = new Graph();
        Graph graphNonCon = new Graph();
        Graph graphDijkstra = new Graph();
        graph = Generator.genGraph(2, 3, 0, 1, 7, false);
        System.out.println(graph);

        try {
            graphDijkstra = Filehandler.readGraph("C:/Users/gryszkat/Desktop/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphDijkstra.jimp");
            graphCon = Filehandler.readGraph("C:/Users/gryszkat/Desktop/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphCon.jimp");
            graphNonCon = Filehandler.readGraph("C:/Users/gryszkat/Desktop/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphNonCon.jimp");
        } catch (Exception e) {}

        System.out.println(Consistency.bfs(graphCon));
        System.out.println(Consistency.bfs(graphNonCon));
        Path path = new Path();
        try {
            path.dijkstra(graphDijkstra, 0, 3);
        } catch (Exception e) {
            System.out.println("Dijkstra nie działa");
        }
        System.out.println(path.vertexPath.size());
    }
}
