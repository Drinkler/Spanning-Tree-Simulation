package com.drinkler;

import java.io.IOException;

public final class App {

    static private Graph graph;
    static private Filehandler filehandler;

    public static void main(String[] args) {
        filehandler = new Filehandler();

        try {
            graph = filehandler.readFile("graph.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        graph.writeKeysWithLinks();
    }

}
