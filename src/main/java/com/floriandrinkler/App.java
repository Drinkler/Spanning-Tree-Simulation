package com.floriandrinkler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import com.beust.jcommander.JCommander;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class App {

    static private Graph graph;
    static private Filehandler filehandler;
    static private Algorithm algorithm;
    static private Args arguments;
    static private JsonHandler jsonHandler;

    public static void main(String[] args) {

        filehandler = new Filehandler();
        algorithm = new Algorithm();
        arguments = new Args();
        jsonHandler = new JsonHandler();

        handleArguments(args, arguments);

        // Get file extension of inputfile
        String[] inputfile = arguments.getInputFilename().split(Pattern.quote("."));
        String fileExtension = inputfile[inputfile.length - 1];

        if (fileExtension.equals("txt")) {
            try {
                graph = filehandler.readFile(arguments.getInputFilename(), arguments.getMaxItems(),
                        arguments.getMaxNodeId(), arguments.getMaxIdent(), arguments.getMaxKosten());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (fileExtension.equals("json")) {
            try {
                graph = jsonHandler.readJson(jsonHandler
                        .jsonToMap(new JSONObject(new JSONTokener(new FileInputStream(arguments.getInputFilename())))));
            } catch (JSONException | FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File extension isn't supported.");
            Runtime.getRuntime().exit(11);
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
        jc.setProgramName("Spanning-Tree-Simulation");
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
