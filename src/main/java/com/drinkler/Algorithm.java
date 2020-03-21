package com.drinkler;

import java.util.ArrayList;

public class Algorithm {

    public void algorithm() {

        // String nodes[] = { "A", "B", "C", "D", "E", "F" };

        // int nodesID[] = { 5, 1, 3, 7, 6, 4 };

        // int mapWeights[][] = { { 0, 10, 10, 0, 0, 0 }, // A
        //         { 10, 0, 0, 15, 10, 0 }, // B
        //         { 10, 0, 0, 3, 10, 0 }, // C
        //         { 0, 15, 3, 0, 2, 10 }, // D
        //         { 0, 10, 10, 2, 0, 2 }, // E
        //         { 0, 0, 0, 10, 2, 0 } // F
        // };

        // String predecessors[] = { "", "", "", "", "", "" };

        // int nodeWeight[] = { 0, 0, 0, 0, 0, 0 };

        String nodes[] = { "A", "B", "C", "D" };

        int nodesID[] = { 7, 3, 5, 1 };

        int mapWeights[][] = { { 0, 1, 0, 5 }, // A
        { 1, 0, 1, 0 }, // B
        { 0, 1, 0, 2 }, // C
        { 5, 0, 2, 0 }, // D
        };

        String predecessors[] = { "", "", "", "" };

        int nodeWeight[] = { 0, 0, 0, 0 };

        for (int k = 0; k < 10; k++) {
            for (int node = 0; node < nodeWeight.length; node++) {
                for (int nodePair = 0; nodePair < mapWeights[node].length; nodePair++) {
                    if (mapWeights[node][nodePair] != 0) {
                        if (nodesID[node] < nodesID[nodePair]) {
                            nodesID[nodePair] = nodesID[node];
                            nodeWeight[nodePair] = nodeWeight[node] + mapWeights[node][nodePair];
                            predecessors[nodePair] = nodes[node];
                        } else if (nodesID[node] == nodesID[nodePair]) {
                            if ((nodeWeight[node] + mapWeights[node][nodePair]) < nodeWeight[nodePair]) {
                                nodeWeight[nodePair] = nodeWeight[node] + mapWeights[node][nodePair];
                                predecessors[nodePair] = nodes[node];
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Weights:");
        for (int i = 0; i < mapWeights.length; i++) {
            String line = "{";
            for (int j = 0; j < mapWeights[i].length; j++) {
                line += " " + mapWeights[i][j] + ", ";
            }
            line += "}";
            System.out.println(line);
        }

        System.out.println();

        System.out.println("Predecessors:");
        String line = "{";
        for (int i = 0; i < predecessors.length; i++) {
            line += " " + predecessors[i] + ", ";
        }
        line += "}";
        System.out.println(line);

        System.out.println();

        System.out.println("Node Weights: ");
        line = "{";
        for (int i = 0; i < nodeWeight.length; i++) {
            line += " " + nodeWeight[i] + ", ";
        }
        line += "}";
        System.out.println(line);

        System.out.println();

        System.out.println("NodeIDs:");
        line = "{";
        for (int i = 0; i < nodesID.length; i++) {
            line += " " + nodesID[i] + ", ";
        }
        line += "}";
        System.out.println(line);
    }

    public void spanningTree(Graph graph) {
        for (int i = 0; i < 10; i++) {
            for (Node node : graph.getMap().keySet()) {
                ArrayList<Link> links = graph.getMap().get(node);
                for (int j = 0; j < links.size(); j++) {
                    Link link = links.get(j);
                    Node nodePair = links.get(j).getNodePair();
                    if (node.getId() < nodePair.getId()) {
                        nodePair.setId(node.getId());
                        nodePair.setWeight(node.getWeight() + link.getWeight());
                        nodePair.setPredecessor(node);
                    } else if (node.getId() == nodePair.getId()) {
                        if ((node.getWeight() + link.getWeight()) < nodePair.getWeight()) {
                            nodePair.setWeight(node.getWeight() + link.getWeight());
                            nodePair.setPredecessor(node);
                        }
                    }
                }
            }
        }
    }

}