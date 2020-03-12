package com.drinkler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class App {

    static public Graph graph;

    public static void main(String[] args) {
        try {
            readFile("graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile(String filename) throws IOException {
        File file = new File("res/" + filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {

            String[] splittedLine = line.trim().split(" ");

            // Add Links
            if (line.contains("-")) {
                graph.addLinkToNode(splittedLine[0],
                        Integer.parseInt(splittedLine[4], 0, splittedLine[4].length() - 1, 10), splittedLine[2]);
                graph.addLinkToNode(splittedLine[2],
                        Integer.parseInt(splittedLine[4], 0, splittedLine[4].length() - 1, 10), splittedLine[0]);
            }
            // Add Nodes
            else if (line.contains("=")) {
                graph.createNewNode(Integer.parseInt(splittedLine[2], 0, splittedLine[2].length() - 1, 10),
                        splittedLine[0], 0);
            }
            // Skip comments and last line
            else if (line.contains("//") || line.contains("}")) {
                continue;
            }
            // Create Graph
            else if (line.contains("Graph")) {
                graph = new Graph(splittedLine[1]);
            }
            // Line layout couldn't be detected
            else {
                System.err.println("Line could not be detected!");
                System.exit(1);
            }
        }
        br.close();

        graph.writeKeysWithLinks();
    }

}
