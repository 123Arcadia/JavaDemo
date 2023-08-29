package RSA;

import org.junit.Test;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSA_jia_mi {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    private byte[] resultBytes;
    private final static String ALGORITHM = "RSA";

    public RSA_jia_mi() {
        try {
            // NOTE : 将要加密内容
            String message = "你好,我很喜欢加密算法";
            System.out.println("明文是：" + message);
            // 生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);

            // 初始化密钥对生成器，密钥大小为1024位
            keyPairGen.initialize(1024);

            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();

            // 得到公钥和私钥
            publicKey = (RSAPublicKey) keyPair.getPublic();
            privateKey = (RSAPrivateKey) keyPair.getPrivate();

            // 用公钥加密
            byte[] srcBytes = message.getBytes();

            resultBytes = RSA_jia_mi.encrypt(publicKey, srcBytes);
            String result = new String(resultBytes);
            System.out.println("用公钥加密后密文是:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes) {
        if (publicKey != null) {
            try {
                // Cipher负责完成加密或解密工作，基于RSA
                Cipher cipher = Cipher.getInstance(ALGORITHM);

                // 根据公钥，对Cipher对象进行初始化
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);

                // 加密，结果保存进resultBytes，并返回
                byte[] resultBytes = cipher.doFinal(srcBytes);
                return resultBytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] encBytes) {
        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);

                // 根据私钥对Cipher对象进行初始化
                cipher.init(Cipher.DECRYPT_MODE, privateKey);

                // 解密并将结果保存进resultBytes
                byte[] decBytes = cipher.doFinal(encBytes);
                return decBytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        long rsaStart = System.currentTimeMillis();

        RSA_jia_mi rsa = new RSA_jia_mi(); // 已经设置为固定加密了
        // 解密
        byte[] decBytes = rsa.decrypt(rsa.privateKey, rsa.resultBytes);

        String dec = new String(decBytes);
        long rsaEnd = System.currentTimeMillis();
        System.out.println("用私钥解密后的结果是:" + dec);
        System.out.println("共计用时：" + (rsaEnd - rsaStart));
    }

    @Test
    public void  RSAtest() {

    }
}
