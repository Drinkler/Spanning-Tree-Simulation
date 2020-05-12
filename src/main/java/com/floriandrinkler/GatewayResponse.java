package com.floriandrinkler;

public class GatewayResponse {

    private String body;
    private Integer statusCode;
    private boolean isBase64Encoded;

    public GatewayResponse(String body, Integer statusCode, boolean isBase64Encoded) {
        this.body = body;
        this.statusCode = statusCode;
        this.isBase64Encoded = isBase64Encoded;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean isBase64Encoded) {
        this.isBase64Encoded = isBase64Encoded;
    }

}
