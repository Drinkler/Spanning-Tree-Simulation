package com.floriandrinkler;

public class Link {

    private int weight;
    private Node nodePair;

    public Link(int weight, Node nodePair) {
        this.weight = weight;
        this.nodePair = nodePair;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getNodePair() {
        return nodePair;
    }

    public void setNodePair(Node nodePair) {
        this.nodePair = nodePair;
    }

}