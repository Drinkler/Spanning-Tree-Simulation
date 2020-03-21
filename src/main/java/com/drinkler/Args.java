package com.drinkler;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = { "-i", "--input" }, description = "Name of the input file.")
    private String inputFilename = "graph.txt";

    @Parameter(names = { "-o", "--output" }, description = "Name of the output file.")
    private String outputFilename = "output.txt";

    @Parameter(names = { "--maxident" }, description = "Max allowed length of a node name.")
    private int maxIdent = 100;

    @Parameter(names = { "--maxitems" }, description = "Max allowed lines of the input file.")
    private int maxItems = 100;

    @Parameter(names = { "--maxkosten" }, description = "Max allowed range for the weight of a link.")
    private int maxKosten = 100;

    @Parameter(names = { "--maxnodeid" }, description = "Max allowed range for the id of a node.")
    private int maxNodeId = 100;

    @Parameter(names = {
            "--pbu" }, description = "The amount of broadcasts for each node. (Lower numbers could output wrong solutions!)")
    private int pbu = 10;

    @Parameter(names = { "-h", "--help" }, help = true, description = "Shows all available options.")
    private boolean help;

    public boolean getHelp() {
        return help;
    }

    public String getInputFilename() {
        return inputFilename;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public int getMaxIdent() {
        return maxIdent;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public int getMaxKosten() {
        return maxKosten;
    }

    public int getPbu() {
        return pbu;
    }

    public int getMaxNodeId() {
        return maxNodeId;
    }

}