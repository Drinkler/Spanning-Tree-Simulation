package com.floriandrinkler;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonHandler {

    public Graph readJson(Map<String, Object> json) {
        Graph tmpGraph = null;

        // Save map as json object
        JSONObject obj = new JSONObject(json);

        // Get graph name
        String graphName = obj.getJSONObject("graph").getString("name");

        // Create graph and save graph name
        tmpGraph = new Graph(graphName);

        // Cycle each node in nodes json array
        JSONArray nodes = obj.getJSONArray("nodes");
        for (Object nodeObj : nodes) {
            JSONObject node = (JSONObject) nodeObj;

            // Get node name and id
            String nodeName = node.getString("name");
            int nodeId = node.getInt("id");

            // Save node
            tmpGraph.createNewNode(nodeId, nodeName);
        }

        // Cycle each link in links json array
        JSONArray links = obj.getJSONArray("links");
        for (Object linkObj : links) {
            JSONObject link = (JSONObject) linkObj;

            // Cycle each node in link
            ArrayList<String> nodeLinkNames = new ArrayList<String>();
            JSONArray nodesLink = link.getJSONArray("nodes");
            for (Object nodeLink : nodesLink) {
                // Get name of node in link
                nodeLinkNames.add(nodeLink.toString());
            }

            // Get weight of link
            int weight = link.getInt("weight");

            // Save links
            tmpGraph.addLinkToNode(nodeLinkNames.get(0), weight, nodeLinkNames.get(1));
            tmpGraph.addLinkToNode(nodeLinkNames.get(1), weight, nodeLinkNames.get(0));
        }

        return tmpGraph;
    }

    public String writeJson(Graph graph) {
        JSONObject json = new JSONObject();

        // Get and save graph name to json
        JSONObject jsonGraph = new JSONObject();
        jsonGraph.put("name", graph.getName());
        json.put("graph", jsonGraph);

        JSONArray links = new JSONArray();

        for (Node node : graph.getMap().keySet()) {
            // Check if root
            if (node.getPredecessor() == null) {
                // Add root to json
                json.put("root", node.getName());
            } else {
                // New link in links
                JSONObject link = new JSONObject();

                // Get nodes
                JSONArray linkNodes = new JSONArray();
                linkNodes.put(node.getName());
                linkNodes.put(node.getPredecessor().getName());

                // Get weight
                link.put("nodes", linkNodes);
                link.put("weight", graph.getLinkWeight(node.getName(), node.getPredecessor().getName()));

                // Add link to links
                links.put(link);
            }
        }

        // Add links to json
        json.put("links", links);

        return json.toString();
    }

}