package com.drinkler;

import java.util.ArrayList;

public class Algorithm {

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