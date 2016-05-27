package by.bsu.up.chat.server;

import by.bsu.up.chat.Constants;

public class MyResp {

    private int statusCode;
    private String body;

    public MyResp(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public static MyResp badRequest(String body) {
        return new MyResp(Constants.RESPONSE_CODE_BAD_REQUEST, body);
    }

    public static MyResp ok() {
        return new MyResp(Constants.RESPONSE_CODE_OK, "");
    }

    public static MyResp ok(String body) {
        return new MyResp(Constants.RESPONSE_CODE_OK, body);
    }

    public static MyResp withCode(int code) {
        return new MyResp(code, "");
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
