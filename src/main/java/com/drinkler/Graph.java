package com.drinkler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Graph {

    private String name;
    private HashMap<Node, ArrayList<Link>> map = new HashMap<Node, ArrayList<Link>>();

    public Graph(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLinkWeight(int nodeId, int nodePairId) {
        return map.get(getNodeById(nodeId)).stream().filter(link -> link.getNodePair().getId() == nodePairId)
                .map(link -> link.getWeight()).collect(Collectors.toList()).get(0);
    }

    public int getLinkWeight(int nodeId, String nodePairName) {
        return map.get(getNodeById(nodeId)).stream().filter(link -> link.getNodePair().getName().equals(nodePairName))
                .map(link -> link.getWeight()).collect(Collectors.toList()).get(0);
    }

    public int getLinkWeight(String nodeName, String nodePairName) {
        return map.get(getNodeByName(nodeName)).stream()
                .filter(link -> link.getNodePair().getName().equals(nodePairName)).map(link -> link.getWeight())
                .collect(Collectors.toList()).get(0);
    }

    public int getLinkWeight(String nodeName, int nodePairId) {
        return map.get(getNodeByName(nodeName)).stream().filter(link -> link.getNodePair().getId() == nodePairId)
                .map(link -> link.getWeight()).collect(Collectors.toList()).get(0);
    }

    public void createNewNode(int id, String name, int weight) {
        map.put(new Node(id, name, weight), new ArrayList<Link>());
    }

    public void addLinkToNode(int nodeId, int weightLink, int nodePairId) {
        map.get(getNodeById(nodeId)).add(new Link(weightLink, getNodeById(nodePairId)));
    }

    public void addLinkToNode(int nodeId, int weightLink, String nodePairName) {
        map.get(getNodeById(nodeId)).add(new Link(weightLink, getNodeByName(nodePairName)));
    }

    public void addLinkToNode(String nodeName, int weightLink, String nodePairName) {
        map.get(getNodeByName(nodeName)).add(new Link(weightLink, getNodeByName(nodePairName)));
    }

    public void addLinkToNode(String nodeName, int weightLink, int nodePairId) {
        map.get(getNodeByName(nodeName)).add(new Link(weightLink, getNodeById(nodePairId)));
    }

    public Node getNodeByName(String name) {
        return map.keySet().stream().filter(node -> node.getName().equals(name)).collect(Collectors.toList()).get(0);
    }

    public Node getNodeById(int id) {
        return map.keySet().stream().filter(node -> node.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void writeKeys() {
        map.keySet().forEach(key -> System.out.println(key.getName() + ": " + key.getId()));
    }

    public void writeLinks() {
        map.values().forEach(links -> {
            links.forEach(link -> System.out.println(link.getNodePair().getName() + ": " + link.getWeight()));
            System.out.println();
        });
    }

    public void writeKeysWithLinks() {
        map.entrySet().forEach(entry -> {
            System.out.println("Key: " + entry.getKey().getName());
            entry.getValue()
                    .forEach(link -> System.out.println(link.getNodePair().getName() + ": " + link.getWeight()));
            System.out.println();
        });
    }

}