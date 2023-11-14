package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("[info]Hello World!");
        logger.warn("[warn] Hello zcw!");
        System.out.println("Hello World!");
    }
}
