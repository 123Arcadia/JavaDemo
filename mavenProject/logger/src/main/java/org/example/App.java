package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    static Logger logger = LoggerFactory.getLogger(App.class.getName());
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000/2/2; i++) {
            logger.error("[info]Hello World!"+i);
            logger.warn("[warn] Hello zcw!");
            System.out.println("Hello World! " + i);
        }
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + " ms");
    }
}
