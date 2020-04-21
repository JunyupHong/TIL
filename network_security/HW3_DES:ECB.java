import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES_ECB {
    public static SecretKey key;

    public static void main(String[] args) {
        String plainText = "Now is the time for";

        byte[] k = {0x01, 0x23, 0x45, 0x67, (byte)0x89, (byte)0xAB, (byte)0xCD, (byte)0xEF};
        key = new SecretKeySpec(k, 0, k.length, "DES");

        try {
            String str = "";
            System.out.println("Plain Text: " + plainText);

            // ZERO PADDING
            System.out.println("\nZero Padding: ");
            byte[][] zeroPaddingResult = zeroPadding(plainText);
            for (int i = 0; i < zeroPaddingResult.length; i++) {
                str = new String(zeroPaddingResult[i]);
                byte[] cipherText = DES_encrypt(str);

                for (int j = 0; j < cipherText.length; j++) {
                    System.out.print(String.format("%02X ", cipherText[j]));
                }
                System.out.print("\n");
            }

            // ANSI
            System.out.println("\nANSI: ");
            byte[][] ansiResult = ansi(plainText);
            for (int i = 0; i < ansiResult.length; i++) {
                str = new String(ansiResult[i]);

                byte[] cipherText = DES_encrypt(str);
                for(int j = 0; j < cipherText.length; j++) {
                    System.out.print(String.format("%02X ", cipherText[j]));
                }
                System.out.print("\n");
            }

            // PKCS
            System.out.println("\nPKCS: ");
            byte[][] pkcsResult = pkcs(plainText);
            for (int i = 0; i < pkcsResult.length; i++) {
                str = new String(pkcsResult[i]);
                byte[] cipherText = DES_encrypt(str);
                for(int j = 0; j < cipherText.length; j++) {
                    System.out.print(String.format("%02X ", cipherText[j]));
                }
                System.out.print("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static byte[][] zeroPadding(String data) {
        int blockCount = data.length() / 8 + 1;

        byte[][] result = new byte[blockCount][8];
        byte[] bytes = data.getBytes();

        for(int i = 0; i < data.length(); i++) {
            result[i/8][i%8] = bytes[i];
        }

        return result;
    }

    public static byte[][] ansi(String data) {
        int blockCount = data.length() / 8 + 1;
        // plainText 의 길이가 8의 배수인 경우 block을 하나 더 늘려준다
        if (data.length() % 8 == 0) blockCount++;

        byte[][] result = new byte[blockCount][8];
        Integer nullCount = blockCount * 8 - data.length();
        result[blockCount-1][7] = nullCount.byteValue();

        byte[] bytes = data.getBytes();


        for(int i = 0; i < data.length(); i++) {
            result[i/8][i%8] = bytes[i];
        }

        return result;
    }

    public static byte[][] pkcs(String data) {
        int blockCount = data.length() / 8 + 1;
        // plainText 의 길이가 8의 배수인 경우 block을 하나 더 늘려준다
        if (data.length() % 8 == 0) blockCount++;

        byte[][] result = new byte[blockCount][8];
        Integer nullCount = blockCount * 8 - data.length();
        byte[] bytes = data.getBytes();
        for(int i = 0; i < result.length * result[0].length; i++) {
            if (i >= data.length()) result[i/8][i%8] = nullCount.byteValue();
            else result[i/8][i%8] = bytes[i];
        }

        return result;
    }



   public static byte[] DES_encrypt(String data) throws Exception {
        if (data == null || data.length() == 0) return new byte[0];

        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainToByte = data.getBytes();

        return cipher.doFinal(plainToByte);
    }
}

