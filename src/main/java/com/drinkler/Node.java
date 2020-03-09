package com.drinkler;

import java.util.LinkedHashMap;
import java.util.Map;

public class Node {

    private String name;
    private int id;

    private Map<String, Integer> links = new LinkedHashMap<String, Integer>();

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Map<String, Integer> getLinks() {
        return links;
    }

    public void setLink(String name, int cost) {
        links.put(name, cost);
    }

}