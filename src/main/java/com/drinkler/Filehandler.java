package com.drinkler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filehandler {

    public Graph readFile(String filename, int maxItems, int maxNodeId, int maxIdent, int maxKosten)
            throws IOException {

        Graph tmpGraph = null;

        checkMaxLinesInFile(filename, maxItems);

        File file = new File("res", filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {

            String[] splittedLine = line.trim().split(" ");

            // Add Links
            if (line.contains("-")) {
                int weight = Integer.parseInt(splittedLine[4], 0, splittedLine[4].length() - 1, 10);
                if (weight < 0 || weight > maxKosten) {
                    System.err.println("Weight of a link exceeds the range of {0, " + maxKosten + "}.");
                    System.exit(3);
                }

                tmpGraph.addLinkToNode(splittedLine[0], weight, splittedLine[2]);
                tmpGraph.addLinkToNode(splittedLine[2], weight, splittedLine[0]);
            }
            // Add Nodes
            else if (line.contains("=")) {
                int id = Integer.parseInt(splittedLine[2], 0, splittedLine[2].length() - 1, 10);
                if (id < 1 || id > maxNodeId) {
                    System.err.println("ID of a node exceeds the range of {1, " + maxNodeId + "}.");
                    System.exit(5);
                }

                String name = splittedLine[0];
                if (maxIdent < 0 || !name.matches("[a-z,A-Z][a-z,A-Z,0-9]{0," + (maxIdent - 1) + "}")) {
                    System.err.println("Name of a node doesn't matches the requirements.");
                    System.exit(4);
                }

                tmpGraph.createNewNode(id, name);
            }
            // Skip comments and last line
            else if (line.contains("//") || line.contains("}")) {
                continue;
            }
            // Create Graph
            else if (line.contains("Graph")) {
                tmpGraph = new Graph(splittedLine[1]);
            }
            // Line layout couldn't be detected
            else {
                System.err.println("Line could not be detected!");
                System.exit(1);
            }
        }
        br.close();

        return tmpGraph;
    }

    public void writeFile(Graph graph, String filename) throws IOException {
        File file = new File("res", filename);
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("Spanning-Tree of " + graph.getName() + " {\n");
        for (Node node : graph.getMap().keySet()) {
            fileWriter.append("\t" + ((node.getPredecessor() == null) ? "Root: " + node.getName() + ";\n"
                    : node.getName() + " - " + node.getPredecessor().getName() + ";\n"));
        }
        fileWriter.append("}");

        fileWriter.close();
    }

    private void checkMaxLinesInFile(String filename, int maxItems) throws IOException {
        Path path = Paths.get("res", filename);
        if (Files.lines(path).count() > maxItems) {
            System.err.println("The input file exceeds the allowed max lines of " + maxItems + "!");
            System.exit(2);
        }
    }

}