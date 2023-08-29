package XmlParse;

import java.io.InputStream;

public class xmlParse {

    public static void main(String[] args) {
        InputStream inputStream = xmlParse.class.getResourceAsStream("InitWeb_zh.xml");
        System.out.println("inputStream = " + inputStream);

        int x = 0;
        try {
            x = 1 / 0;
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        //inputStream = java.io.BufferedInputStream@5fd0d5ae
        //e.getMessage() = / by zero
    }



}
