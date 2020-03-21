package com.drinkler;

import java.io.IOException;

public final class App {

    static private Graph graph;
    static private Filehandler filehandler;
    static private Algorithm algorithm;

    // TODO : set command line arguments: help, outputfile, inputfile, maxident, maxitems, maxnodeid, maxkosten

    public static void main(String[] args) {
        algorithm = new Algorithm();
        filehandler = new Filehandler();

        try {
            graph = filehandler.readFile("graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        algorithm.spanningTree(graph);

        outputConsole();

        try {
            filehandler.writeFile(graph, "output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void outputConsole() {
        System.out.println("Spanning-Tree of " + graph.getName() + " {");
        for (Node node : graph.getMap().keySet()) {
            System.out.println((node.getPredecessor() == null) ? "Root: " + node.getName() + ";"
                    : node.getName() + " - " + node.getPredecessor().getName() + ";");
        }
        System.out.println("}");
    }

}
