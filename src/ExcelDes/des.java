package ExcelDes;

import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class des {

    private static final String KEY = "MySecretKey12345"; // 16字节密钥
    public static String socketHost;
    public static String socketPort;
    public static String socketAddress;

    public static String yardServiceHost;
    public static String yardServicePort;
    public static String yardAddress;

    public static String ctosServiceHost;
    public static String ctosServicePort;
    public static String ctosAddress;

    public static String userID;
    public static String password;
    public static String strReqParam;

    public static String logPrefix;
    public static String logMsg;
    public static String logReceiptSuccess;
    public static String heartbeatPrefix;

    /**
     * 加密
     * @param args
     */
    public static void main(String[] args) {
//        FileInputStream inputStream = null;
//        try {
//            File file = new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\params.csv");
//            if (file.exists()) {
//                inputStream = new FileInputStream(file.getAbsolutePath());
//                List<String> ctemp = Csvop.importCsv(inputStream);
//                ctemp.remove(0);
//                System.out.println("(init): read params.csv");
//
//                for (String e : ctemp) {
//                    String[] line = e.split(",");
//                    String name = line[0];
//                    if (name.equals("socket")) {
//                        socket = encrypt(line[1]);
//                        System.out.println("socket = " + socket);
//                        continue;
//                    } else if (name.equals("USER_ID")){
////                        password = line[1];
//                        user_id = encrypt(line[1]);
//                        System.out.println("user_id = " + user_id);
//                        continue;
//                    } else if (name.equals("PASSWORD")) {
////                        heartbeatPrefix = line[1].replace("'", "");
//                        password = encrypt(line[1].replace("'", ""));
//                        System.out.println("password = " + password);
//                        continue;
//                    }
//
//                }
//            }
//            strReqParam = "user_id," + user_id + " socket," + socket + " password," + password;
//            System.out.println("strReqParam = " + strReqParam);
//            inputStream.close();
//
//        } catch (Exception e) {
//            System.out.println("(init error): params.csv READ ERROR!");
//        }
        FileInputStream inputStream = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\params.csv");
            if (file.exists()) {
                inputStream = new FileInputStream(file.getAbsolutePath());
                List<String> ctemp = Csvop.importCsv(inputStream);
                ctemp.remove(0);
                System.out.println("(init): read params.csv");

                for (String e : ctemp) {
                    String[] line = e.split(",");
                    String name = line[0];
                    if (name.equals("socket")) {
                        socketAddress = encrypt(line[1]);
                        String[] split = socketAddress.split(":");
                        socketHost = split[0];
                        socketPort = split[1];
                        continue;
                    } else if (name.equals("yard_webservice")) {
                        yardAddress = line[1];
                        continue;
                    } else if (name.equals("ctos_webservice")) {
                        ctosAddress = line[1];
                        continue;
                    } else if (name.equals("USER_ID")) {
                        userID = line[1];
                        continue;
                    } else if (name.equals("PASSWORD")){
                        password = line[1];
                    } else if (name.equals("log_prefix")) {
//                        logPrefix = line[1].replace("'", "");
                        logPrefix = line[1];
                    } else if (name.equals("log_msg")) {
                        logMsg = line[1];
                    } else if (name.equals("log_receipt_success")) {
                        logReceiptSuccess = line[1];
                    } else if (name.equals("heartbeat_prefix")) {
//                        heartbeatPrefix = line[1].replace("'", "");
                        heartbeatPrefix = line[1];
                    }
                }
            }
            System.out.println("socket,"+socketAddress);
            System.out.println("yard_webservice,"+yardAddress);
            System.out.println("ctos_webservice,"+ctosAddress);
            System.out.println("USER_ID,"+userID);
            System.out.println("PASSWORD,"+password);
            System.out.println("log_prefix,"+logPrefix);
            System.out.println("log_msg,"+logMsg);
            System.out.println("log_receipt_success,"+logReceiptSuccess);
            System.out.println("heartbeat_prefix,"+heartbeatPrefix);
            strReqParam = "USER_ID:'" + userID + "',PASSWORD:'" + password + "',LANGUAGE:'en-US',JSONTYPE:'Y'";
            // strReqParam = USER_ID:'CMHIT',PASSWORD:'1',LANGUAGE:'en-US',JSONTYPE:'Y'
            System.out.println("strReqParam = " + strReqParam);
            inputStream.close();

        } catch (Exception e) {
            System.out.println("(init error): params.csv READ ERROR!");
        }


        String[] split = strReqParam.split(",");
        List<String> list = Arrays.asList(split);
        System.out.println("list = " + list);
        OutputStreamWriter os = null;
        //写入
        try {
            os = new OutputStreamWriter(new FileOutputStream(new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\paramsOut.csv")));
            if (list != null && !list.isEmpty()) {
                for (String s : list) {
                    os.append(s).append("\r");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("e.getMessage() = " + e.getMessage());
                }
            }
        }

    }

    /*
    编码
     */
    @Test
    public void encode() {
        FileInputStream inputStream = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\params.csv");
            if (file.exists()) {
                inputStream = new FileInputStream(file.getAbsolutePath());
                List<String> ctemp = Csvop.importCsv(inputStream);
                ctemp.remove(0);
                System.out.println("(init): read params.csv");

                for (String e : ctemp) {
                    String[] line = e.split(",");
                    String name = line[0];
                    if (name.equals("socket")) {
                        socketAddress = encrypt(line[1]);
//                        String[] split = socketAddress.split(":");
//                        socketHost = split[0];
//                        socketPort = split[1];
                        continue;
                    } else if (name.equals("yard_webservice")) {
                        yardAddress = encrypt(line[1]);
                        continue;
                    } else if (name.equals("ctos_webservice")) {
                        ctosAddress = encrypt(line[1]);
                        continue;
                    } else if (name.equals("USER_ID")) {
                        userID = encrypt(line[1]);
                        continue;
                    } else if (name.equals("PASSWORD")){
                        password = encrypt(line[1]);
                    } else if (name.equals("log_prefix")) {
//                        logPrefix = line[1].replace("'", "");
                        logPrefix = encrypt(line[1]);
                    } else if (name.equals("log_msg")) {
                        logMsg = encrypt(line[1]);
                    } else if (name.equals("log_receipt_success")) {
                        logReceiptSuccess = encrypt(line[1]);
                    } else if (name.equals("heartbeat_prefix")) {
//                        heartbeatPrefix = line[1].replace("'", "");
                        heartbeatPrefix = encrypt(line[1]);
                    }
                }
            }
            System.out.println("socket,"+socketAddress);
            System.out.println("yard_webservice,"+yardAddress);
            System.out.println("ctos_webservice,"+ctosAddress);
            System.out.println("USER_ID,"+userID);
            System.out.println("PASSWORD,"+password);
            System.out.println("log_prefix,"+logPrefix);
            System.out.println("log_msg,"+logMsg);
            System.out.println("log_receipt_success,"+logReceiptSuccess);
            System.out.println("heartbeat_prefix,"+heartbeatPrefix);
            strReqParam = "USER_ID:'" + userID + "',PASSWORD:'" + password + "',LANGUAGE:'en-US',JSONTYPE:'Y'";
            // strReqParam = USER_ID:'CMHIT',PASSWORD:'1',LANGUAGE:'en-US',JSONTYPE:'Y'
            System.out.println("strReqParam = " + strReqParam);
            inputStream.close();

        } catch (Exception e) {
            System.out.println("(init error): params.csv READ ERROR!");
        }


//        String[] split = strReqParam.split(",");
//        List<String> list = Arrays.asList(split);
//        System.out.println("list = " + list);
//        OutputStreamWriter os = null;
//        //写入
//        try {
//            os = new OutputStreamWriter(new FileOutputStream(new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\paramsOut.csv")));
//            os.append("key").append("\r");
//            os.append("value").append("\r");
//            if (list != null && !list.isEmpty()) {
//                for (String s : list) {
//                    os.append(s).append("\r");
//                }
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());;
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    System.out.println("e.getMessage() = " + e.getMessage());
//                }
//            }
//        }

    }

    /**
     * 解密
     * @param
     * @return
     */
    @Test
    public void decode() {
//        FileInputStream inputStream = null;
//        try {
//            File file = new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\paramsOut.csv");
//            if (file.exists()) {
//                inputStream = new FileInputStream(file);
//                List<String> ctemp = Csvop.importCsv(inputStream);
////                ctemp.remove(0);
//                System.out.println("(init): read params.csv");
//                System.out.println("ctemp = " + ctemp);
//                System.out.println("ctemp.size = " + ctemp.size()); // 3
//                for (String s : ctemp) {
//                    System.out.println(s);
//                }
//                for (String e : ctemp) {
//                    String[] line = e.split(",");
//                    String name = line[0];
//                    if (name.equals("socket")) {
//                        System.out.println("line = " + line[1]);
//                        socket = decrypt(line[1]);
//                        System.out.println("socket = " + socket);
//                        continue;
//                    } else if (name.equals("user_id")){
////                        password = line[1];
//                        System.out.println("line = " + line[1]);
//                        user_id = decrypt(line[1]);
//                        System.out.println("user_id = " + user_id);
//                        continue;
//                    } else if (name.equals("password")) {
////                        heartbeatPrefix = line[1].replace("'", "");
//                        System.out.println("line = " + line[1]);
//                        password = decrypt(line[1]).replace("'", "");
//                        System.out.println("password = " + password);
//                        continue;
//                    }
//
//                }
//            }
//            strReqParam = "user_id:'" + user_id + "',socket:'" + socket + "',passwordz:'" + password +"'";
//            System.out.println("strReqParam = " + strReqParam);
//            inputStream.close();
//
//        } catch (Exception e) {
//            System.out.println("(init error): params.csv READ ERROR!");
//        }
        FileInputStream inputStream = null;
        try {
            File file = new File("D:\\javaProject\\javaTesting\\src\\ExcelDes\\paramsOut.csv");
            if (file.exists()) {
                inputStream = new FileInputStream(file.getAbsolutePath());
                List<String> ctemp = Csvop.importCsv(inputStream);
                ctemp.remove(0);
                System.out.println("(init): read params.csv");

                for (String e : ctemp) {
                    String[] line = e.split(",");
                    String name = line[0];
                    if (name.equals("socket")) {
                        socketAddress = decrypt(line[1]);
//                        String[] split = socketAddress.split(":");
//                        socketHost = split[0];
//                        socketPort = split[1];
                        continue;
                    } else if (name.equals("yard_webservice")) {
                        yardAddress = decrypt(line[1]);
                        continue;
                    } else if (name.equals("ctos_webservice")) {
                        ctosAddress = decrypt(line[1]);
                        continue;
                    } else if (name.equals("USER_ID")) {
                        userID = decrypt(line[1]);
                        continue;
                    } else if (name.equals("PASSWORD")){
                        password = decrypt(line[1]);
                    } else if (name.equals("log_prefix")) {
//                        logPrefix = line[1].replace("'", "");
                        logPrefix = decrypt(line[1]);
                    } else if (name.equals("log_msg")) {
                        logMsg = decrypt(line[1]);
                    } else if (name.equals("log_receipt_success")) {
                        logReceiptSuccess = decrypt(line[1]);
                    } else if (name.equals("heartbeat_prefix")) {
//                        heartbeatPrefix = line[1].replace("'", "");
                        heartbeatPrefix = decrypt(line[1]);
                    }
                }
            }
            System.out.println("socket,"+socketAddress);
            System.out.println("yard_webservice,"+yardAddress);
            System.out.println("ctos_webservice,"+ctosAddress);
            System.out.println("USER_ID,"+userID);
            System.out.println("PASSWORD,"+password);
            System.out.println("log_prefix,"+logPrefix);
            System.out.println("log_msg,"+logMsg);
            System.out.println("log_receipt_success,"+logReceiptSuccess);
            System.out.println("heartbeat_prefix,"+heartbeatPrefix);
            strReqParam = "USER_ID:'" + userID + "',PASSWORD:'" + password + "',LANGUAGE:'en-US',JSONTYPE:'Y'";
            // strReqParam = USER_ID:'CMHIT',PASSWORD:'1',LANGUAGE:'en-US',JSONTYPE:'Y'
            System.out.println("strReqParam = " + strReqParam);
            inputStream.close();

        } catch (Exception e) {
            System.out.println("(init error): params.csv READ ERROR!");
        }
    }


    public static String encrypt(String plainText) {
        try {
            // 创建AES加密算法实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 创建AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            // 初始化加密算法实例
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 加密数据
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            // 将加密后的数据转换成Base64编码的字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("加密失败：" + e.getMessage());
            return null;
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            // 创建AES解密算法实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 创建AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            // 初始化解密算法实例
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // 将Base64编码的字符串转换成加密后的数据
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            // 解密数据
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // 将解密后的数据转换成字符串
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.err.println("解密失败：" + e.getMessage());
            return null;
        }
    }
}
