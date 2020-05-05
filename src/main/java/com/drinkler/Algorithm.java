package com.drinkler;

import java.util.ArrayList;

public class Algorithm {

    public void spanningTree(Graph graph, int pdu) {

        // Cycle the amount of Broadcasts each Node gets
        for (int i = 0; i < pdu; i++) {

            // Cycle each Node in one Broadcast
            for (Node node : graph.getMap().keySet()) {

                // Cycle each Link by each Node
                ArrayList<Link> links = graph.getMap().get(node);
                for (int j = 0; j < links.size(); j++) {

                    Link link = links.get(j);
                    Node nodePair = links.get(j).getNodePair();

                    // If the ID from a NodePair is bigger -> NodePair gets new ID by Node and the
                    // Weight will be added
                    if (node.getId() < nodePair.getId()) {
                        nodePair.setId(node.getId());
                        nodePair.setWeight(node.getWeight() + link.getWeight());
                        nodePair.setPredecessor(node);

                        // If the ID is the same but the Weight is bigger from the NodePair, it gets the
                        // new Weight
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
