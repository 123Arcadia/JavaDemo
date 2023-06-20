package ExcelDes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class p3 {

    private static final String KEY = "MySecretKey12345"; // 16字节密钥

    public static void main(String[] args) {
        String plainText = "This is a secret message!";
        System.out.println("原始明文：" + plainText);

        // 加密
        String encryptedText = encrypt(plainText);
        System.out.println("加密后的密文：" + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText);
        System.out.println("解密后的明文：" + decryptedText);
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