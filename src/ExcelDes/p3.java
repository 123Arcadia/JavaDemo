package ExcelDes;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class p3 {

    private static final String KEY = "MySecretKey12345"; // 16�ֽ���Կ

    public static void main(String[] args) {
        String plainText = "This is a secret message!";
        System.out.println("ԭʼ���ģ�" + plainText);

        // ����
        String encryptedText = encrypt(plainText);
        System.out.println("���ܺ�����ģ�" + encryptedText);

        // ����
        String decryptedText = decrypt(encryptedText);
        System.out.println("���ܺ�����ģ�" + decryptedText);
    }

    public static String encrypt(String plainText) {
        try {
            // ����AES�����㷨ʵ��
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // ����AES��Կ
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            // ��ʼ�������㷨ʵ��
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // ��������
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            // �����ܺ������ת����Base64������ַ���
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.err.println("����ʧ�ܣ�" + e.getMessage());
            return null;
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            // ����AES�����㷨ʵ��
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // ����AES��Կ
            SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
            // ��ʼ�������㷨ʵ��
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // ��Base64������ַ���ת���ɼ��ܺ������
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            // ��������
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // �����ܺ������ת�����ַ���
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.err.println("����ʧ�ܣ�" + e.getMessage());
            return null;
        }
    }
}