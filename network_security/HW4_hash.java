import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash {
    public static void main(String[] argv) throws NoSuchAlgorithmException {
        System.out.println("텍스트를 입력하세요");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        sc.close();
        System.out.println("\n===== hash value =====");

        hash(text, "md5");
        hash(text, "sha-1");
        hash(text, "sha-256");
        hash(text, "sha-512");
    }

    public static void hash(String text, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] textBytes = text.getBytes();
        md.update(textBytes);
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        System.out.println(algorithm + ": " + sb.toString());
    }

}

