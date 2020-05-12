package com.floriandrinkler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler implements RequestHandler<Map<String, Object>, GatewayResponse> {

    private JsonHandler jsonHandler;
    private Graph graph;
    private Algorithm algorithm;

    @Override
    public GatewayResponse handleRequest(Map<String, Object> event, Context context) {

        jsonHandler = new JsonHandler();
        algorithm = new Algorithm();

        graph = jsonHandler.readJson(event);

        algorithm.spanningTree(graph, 10);

        GatewayResponse response = new GatewayResponse(jsonHandler.writeJson(graph), 200, false);

        return response;
    }
}