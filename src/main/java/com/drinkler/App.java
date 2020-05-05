package com.drinkler;

import java.io.IOException;

import com.beust.jcommander.JCommander;

public class App {

    static private Graph graph;
    static private Filehandler filehandler;
    static private Algorithm algorithm;
    static private Args arguments;

    public static void main(String[] args) {

        filehandler = new Filehandler();
        algorithm = new Algorithm();
        arguments = new Args();

        handleArguments(args, arguments);

        try {
            graph = filehandler.readFile(arguments.getInputFilename(), arguments.getMaxItems(),
                    arguments.getMaxNodeId(), arguments.getMaxIdent(), arguments.getMaxKosten());
        } catch (IOException e) {
            e.printStackTrace();
        }

        algorithm.spanningTree(graph, arguments.getPbu());

        outputConsole();

        try {
            filehandler.writeFile(graph, arguments.getOutputFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void handleArguments(String[] args, Args arguments) {
        JCommander jc = JCommander.newBuilder().addObject(arguments).build();
        jc.parse(args);

        if (arguments.getHelp()) {
            jc.usage();
            Runtime.getRuntime().exit(0);
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
