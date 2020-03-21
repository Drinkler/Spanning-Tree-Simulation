package com.drinkler;

public class Node {

    private int id;
    private String name;
    private int weight;
    private Node predecessor;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
        this.weight = 0;
        this.predecessor = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

}