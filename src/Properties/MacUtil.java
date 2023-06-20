package Properties;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * 根据系统名获得mac地址
 * @author lichao
 */
public class MacUtil {

    private static final Pattern macPattern = Pattern.compile(".*((:?[0-9a-f]{2}[-:]){5}[0-9a-f]{2}).*",
            Pattern.CASE_INSENSITIVE);

    @Test
    public void properties() {
        List<String> macList = new ArrayList<>();
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            macList = getMacWin();
        } else if (osName.startsWith("Mac OS")) {
            macList = getMacMac();
        } else if (osName.startsWith("Linux")) {
            macList = getMacLin();
        } else {
            macList = getMacUnix();
        }
        System.out.println("osName = " + osName);
        System.out.println("macList = " + macList);
        //osName = Windows 11
        //macList = [00-FF-35-51-6C-0F, 38-7A-0E-A2-14-7B, 3A-7A-0E-A2-14-7B, 38-7A-0E-A2-14-7C, 9C-2D-CD-27-D5-CC]
    }

    public static List<String> getMacWin() {
        try {
            java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            StringBuilder sb = new StringBuilder();
            ArrayList<String> tmpMacList = new ArrayList<>();
            while (en.hasMoreElements()) {
                NetworkInterface iFace = en.nextElement();
                List<InterfaceAddress> addresses = iFace.getInterfaceAddresses();
                for (InterfaceAddress addr: addresses) {
                    InetAddress ip = addr.getAddress();
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    if (network == null) {
                        continue;
                    }
                    byte[] mac = network.getHardwareAddress();
                    if (mac == null) {
                        continue;
                    }
                    sb.delete(0, sb.length());
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    tmpMacList.add(sb.toString());
                }
            }
            if (tmpMacList.size() <=0 ) {
                return tmpMacList;
            }
            /**
             * 同一个网卡的ipv4,ipv6得到的mac相同，流式处理去重
             */
            List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
            return unique;
        } catch (Exception e) {
            System.out.println("获取MAC地址错误！");
        }
        return null;
    }

    public static List<String> getMacMac() {
        String macCommand = "ifconfig";
        try {
            List<String> macList = new ArrayList<>();
            // 执行命令
            Process process = Runtime.getRuntime().exec(macCommand);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = null; (line = bufReader.readLine()) != null; ) {
                Matcher matcher = macPattern.matcher(line);
                if (matcher.matches()) {
                    macList.add(matcher.group(1));
                    macList.add(matcher.group(1).toUpperCase());
                }
            }
            process.destroy();
            bufReader.close();
            List<String> unique = macList.stream().distinct().collect(Collectors.toList());
            return unique;
        } catch (IOException e) {
            System.out.println("获取MAC地址错误！");
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getMacLin() {
        String[] linuxCommand = { "/sbin/ifconfig", "-a" };
        try {
            List<String> macList = new ArrayList<>();
            // 执行命令
            Process process = Runtime.getRuntime().exec(linuxCommand);
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            for (String line = null; (line = bufReader.readLine()) != null; ) {
                Matcher matcher = macPattern.matcher(line);
                if (matcher.matches()) {
                    macList.add(matcher.group(1));
                    macList.add(matcher.group(1).toUpperCase());
                }
            }
            process.destroy();
            bufReader.close();
            List<String> unique = macList.stream().distinct().collect(Collectors.toList());
            return unique;
        } catch (IOException e) {
            System.out.println("获取MAC地址错误！");
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getMacUnix() {
        List<String> macList = new ArrayList<>();
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            // 一般取eth0作为本地主网卡
            process = Runtime.getRuntime().exec("ifconfig eth0");
            // 显示信息中包含有mac地址信息
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                // 寻找标示字符串[hwaddr]
                index = line.toLowerCase().indexOf("hwaddr");
                if (index >= 0) {
                    // 取出mac地址并去除2边空格
                    macList.add(line.substring(index + "hwaddr".length() + 1).trim());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("获取MAC地址错误！");
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }
        return macList;
    }
}
