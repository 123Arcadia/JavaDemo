package JCommand;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandTest {

//    private final JCommander jcommander;
//    public static final Logger logger = LoggerFactory.getLogger(CommandTest.class);

//    public static void main(String[] args) {
//        execCommand("echo \"Hello, World!\" > src/JCommand/JcTest.txt");
//        execCommand("echo \"Hello, World!\"");
//        tets_ls();
//    }

    /**
     * 执行并返回状态码
     *
     */
    @Test
    public void execCommand() {

        String cmd = "echo \"Hello, World!\" > src/JCommand/JcTest.txt";
        BufferedReader br = null;
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            System.out.println("os.name = " + System.getProperty("os.name"));
            //os.name = Windows 11
            String prefix1 = isWindows ? "cmd" : "/bin/sh";
            String prefix2 = isWindows ? "/c" : "-c";
            //打开命令提示符（Command Prompt）并执行跟随在 /c 后面的命令，然后关闭命令提示符窗口 /c 参数就是告诉 cmd.exe 运行指定命令并立即退出
            Process p = Runtime.getRuntime().exec(new String[]{prefix1, prefix2, cmd});
            // 0 表示正常
            int returnCode = p.waitFor();
            if (returnCode != 0) {
                br = new BufferedReader(new InputStreamReader(p.getErrorStream(),  isWindows ? "GB2312" : "UTF-8"));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                System.out.println("shell 执行失败: " + sb.toString());
//                logger.error("shell 执行失败: {}", sb);
                throw new RuntimeException("shell 执行失败: " +sb.toString());
            }
            System.out.printf("cmd = %s, returnCode = %s\n", cmd, returnCode);
//            logger.info("cmd = {}, returnCode = {}", cmd, returnCode);
//            return true;
            Assert.assertEquals(returnCode, 0);
            System.out.println("shell successfully!: " +cmd.toString() +", returnCode=" +returnCode);
        } catch (Exception e) {
//            e.printStackTrace();
            System.err.println(ExceptionUtils.getStackTrace(e));
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test_ls() {
        try {
            Process lsProcess = Runtime.getRuntime().exec(new String[]{"cmd","/c","ls"});

            int waitFor = lsProcess.waitFor();
            if (waitFor!=0){
                System.out.println("error! " + waitFor + ", lsPro: " + lsProcess.getErrorStream());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}


