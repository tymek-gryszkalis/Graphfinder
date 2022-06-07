package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.edu.pw.ee.jimp.Backend.Consistency;
import pl.edu.pw.ee.jimp.Backend.Filehandler;
import pl.edu.pw.ee.jimp.Backend.Graph;
import pl.edu.pw.ee.jimp.Backend.Path;

public class GraphTest {
    Graph graphCon, graphNonCon, graphDijkstra;
    int dijkstraValue;

    @BeforeEach
    void setUp() {
        try {
            graphDijkstra = Filehandler.readGraph("C:/Users/lalar/source/repos/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphDijkstra.jimp");
            graphCon = Filehandler.readGraph("C:/Users/lalar/source/repos/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphCon.jimp");
            graphNonCon = Filehandler.readGraph("C:/Users/lalar/source/repos/2022L_JIMP2_proj_git_GR3_gr6/PROJEKT JAVA/graphfinder/src/test/java/test/graphNonCon.jimp");
        } catch (Exception e) {}
    }

    @Test
    void graphConsistentCheck() {
        assertTrue(Consistency.bfs(graphCon));
    }

    @Test
    void graphNonConsistentCheck() {
        assertFalse(Consistency.bfs(graphNonCon));
    }

    @Test
    void graphPathCheck() {
        Path path = new Path();
        path.dijkstra(graphDijkstra, 0, 3);
        dijkstraValue = 2;
        assertEquals(dijkstraValue, path.vertexPath.size());
    }
}
