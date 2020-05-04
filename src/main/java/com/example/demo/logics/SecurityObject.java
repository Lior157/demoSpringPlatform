package com.example.demo.logics;

import java.util.List;

public class SecurityObject {
    private String reqID;
    private String functionName ;
    private List<Object> object;


    public SecurityObject(String reqID, String functionName, List<Object> object) {
        this.reqID = reqID;
        this.functionName = functionName;
        this.object = object;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Object> getObject() {
        return object;
    }

    public void setObject(List<Object> object) {
        this.object = object;
    }
}
