package com.drinkler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class App {

    static int MAX_ITEMS = 100; // The input file can have a max of MAX_ITEMS lines
    static int MAX_IDENT = 100; // Max length of the node identifier/name

    static ArrayList<Node> nodes = new ArrayList<Node>();

    static int[][] kantengewichte = new int[6][6];

    public static void main(String[] args) {
        try {
            readGraph("graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readGraph(String filename) throws IOException {

        // Die Eingabedatei kann max. MAX_ITEMS Zeilen enthalten.
        Path path = Paths.get("res/" + filename);
        if (Files.lines(path).count() > MAX_ITEMS) {
            System.err.println("Die Eingabedatei hat zu viele Zeilen. Erlaubtes Maximum ist " + MAX_ITEMS + "!");
            System.exit(1);
        }

        File file = new File("res/" + filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {

            // Kommentarzeilen beginnen mit „//“
            if (line.contains("//") || line.contains("{") || line.contains("}"))
                continue;

            // Leerzeilen, führende Tabs oder Blanks werden ignoriert
            String[] lineSplitted = line.trim().split(" ");
            int lastElement = lineSplitted.length - 1;
            lineSplitted[lastElement] = lineSplitted[lastElement].replace(";", "");

            int value = Integer.parseInt(lineSplitted[lastElement]);

            // "[A-Z][A-Z,0-9]{0," + (MAX_IDENT - 1) + "}"
            // Die Graph- bzw. Knotenbezeichner beginnen mit einem Buchstaben gefolgt von
            // alphanumerischen Zeichen bis zu einer über die Präprozessorkonstante
            // MAX_IDENT vorgegebenen Maximallänge

            // Zeilen mit Definition der Knotengewichte und Zeilen mit Definition einer
            // Kante können bel. gemischt werden.
            String regex = "[A-Z][A-Z,0-9]{0," + (MAX_IDENT - 1) + "}";
            // Edges
            if (lineSplitted[0].matches(regex) && lineSplitted[1].matches("-") && lineSplitted[2].matches(regex)) {
                getNodeById(getNodeIdWithName(lineSplitted[0])).setLink(lineSplitted[2], value);
            }
            // Nodes
            else if (lineSplitted[0].matches(regex)) {
                nodes.add(new Node(value, lineSplitted[0]));
            } else {
                System.err.println("Falsch formatierte Knotenbezeichner oder Überschreitung der Maximallänge von "
                        + MAX_IDENT + "!");
                System.exit(2);
            }
        }
        br.close();

    }

    public static Node getNodeById(int id) {
        Node node = null;
        for (Node tempNode : nodes) {
            if (tempNode.getId() == id) {
                node = tempNode;
                break;
            }
        }
        return node;
    }

    public static int getNodeIdWithName(String name) {
        int id = 0;
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                id = node.getId();
                break;
            }
        }
        return id;
    }

    /*
     * Prints the matrix onto the terminal in an easy seeable way
     */
    public static void printMatrix() {
        String line = "";

        for (int i = 0; i < kantengewichte.length; i++) {
            for (int j = 0; j < kantengewichte[i].length; j++) {
                line += kantengewichte[i][j] + " ";
            }
            System.out.println(line);
            line = "";
        }
    }
}
