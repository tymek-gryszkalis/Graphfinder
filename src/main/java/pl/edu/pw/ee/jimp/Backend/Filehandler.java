package pl.edu.pw.ee.jimp.Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Filehandler {
  public static Graph readGraph(String filename) throws Exception {
    // Read file
    BufferedReader reader;
    Graph graph = new Graph();

    try {
      reader = new BufferedReader(new FileReader(filename));
      String line = reader.readLine();
      int indx = 0;
      while (line != null) {
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        ArrayList<Double> connections = new ArrayList<Double>();
        if (indx >= 2) {
          String[] lineArr = line.split("\t");
          for (int i = 1; i < lineArr.length; i++) {
            if (i % 2 == 1) {
              vertices.add(Integer.valueOf(lineArr[i].substring(0, lineArr[i].length() - 1)));
            }
            if (i % 2 == 0) {
              connections.add(Double.parseDouble(lineArr[i]));
            }
          }
        } else {
          line = reader.readLine();
          indx++;
          continue;
        }
        line = reader.readLine();
        graph.add(indx - 2, vertices, connections);
        indx++;
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return graph;
  }

  public static void saveGraph(Graph graph, String filename, int x, int y) {
    try {
      FileWriter writer = new FileWriter(filename);
      writer.write(x + " " + y + "\n");
      writer.write("\n");

      for (int i = 0; i < graph.size(); i++) {
        HashMap temp = graph.get(i);
        ArrayList<Double> cons = (ArrayList<Double>)temp.get("Cons");
        ArrayList<Integer> vers = (ArrayList<Integer>)temp.get("Vers");

        String toprint = "\t";
        for (int j = 0; j < cons.size(); j++) {
          toprint += (vers.get(j) + ":\t" + cons.get(j) + "\t");
        }
        toprint = toprint.substring(0, toprint.length() - 1);
        writer.write(toprint + "\n");
      }

      writer.close();
    } catch (IOException e) {}
  }
  
}
