package com.drinkler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filehandler {

    private int MAX_ITEMS = 100; // The input file can have a max of MAX_ITEMS lines
    // private int MAX_IDENT = 100; // Max length of the node identifier/name

    public Graph readFile(String filename) throws IOException {

        Graph tmpGraph = null;

        checkMaxLinesInFile(filename);

        File file = new File("res/" + filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {

            String[] splittedLine = line.trim().split(" ");

            // Add Links
            if (line.contains("-")) {
                tmpGraph.addLinkToNode(splittedLine[0],
                        Integer.parseInt(splittedLine[4], 0, splittedLine[4].length() - 1, 10), splittedLine[2]);
                tmpGraph.addLinkToNode(splittedLine[2],
                        Integer.parseInt(splittedLine[4], 0, splittedLine[4].length() - 1, 10), splittedLine[0]);
            }
            // Add Nodes
            else if (line.contains("=")) {
                tmpGraph.createNewNode(Integer.parseInt(splittedLine[2], 0, splittedLine[2].length() - 1, 10),
                        splittedLine[0], 0);
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

    private void checkMaxLinesInFile(String filename) throws IOException {
        Path path = Paths.get("res/" + filename);
        if (Files.lines(path).count() > MAX_ITEMS) {
            System.err.println("The input file exceeds " + MAX_ITEMS + " of lines!");
            System.exit(2);
        }
    }

}