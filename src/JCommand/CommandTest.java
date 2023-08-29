package JCommand;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandTest {

//    private final JCommander jcommander;

    @Test
    public void commandTest() throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\helloin01.txt");
        if (file.exists()) {
            System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
        }

        System.out.println(1 / 2);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(22, 222);
        map.put(33, 333);
        System.out.println(map.get(1));

        DA: for (int i = 0; i < 5; i++) {
            for (int j = i; j < 8; j++) {
                if (j == 5) {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    break DA;
                }
            }
        }

        System.out.println((double) 1 / 2 * 3);

    }

}


