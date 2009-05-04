package com.sun.j2ee.blueprints.waf.controller.web;

public class ErrorMapping implements java.io.Serializable {

    private String errorClassName;
    private String screen;
    
    public ErrorMapping(String errorClassName, String screen) {
        this.errorClassName = errorClassName;
        this.screen = screen;
    }
    
    public String getScreenName() {
        return screen;
    }
    
    public String getExceptionClassName() {
        return errorClassName;
    }
}
