package JCommand;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
public class CommandTest {

//    private final JCommander jcommander;
    public static final Logger logger = LoggerFactory.getLogger(CommandTest.class);

    public static void main(String[] args) {
        execCommand("echo \"Hello, World!\" > src/JCommand/JcTest.txt");
//        execCommand("echo \"Hello, World!\"");
        tets_ls();
    }

    /**
     * 执行并返回状态码
     *
     * @param cmd
     * @return
     */
    public static Boolean execCommand(String cmd) {


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
                logger.error("shell 执行失败: {}", sb);
                return false;
            }
            logger.info("cmd = {}, returnCode = {}", cmd, returnCode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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


    public static void tets_ls() {
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


