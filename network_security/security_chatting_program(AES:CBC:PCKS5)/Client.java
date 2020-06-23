import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.LocalDateTime;
import java.util.Base64;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        BufferedReader reader;
        ObjectInputStream objectInputStream;

        String receiveMsg;

        SecureRandom secureRandom = new SecureRandom();
        PublicKey serverPublicKey;

        SecretKey aes256Key;
        byte[] bytesAES256Key;
        Cipher cipher;

        try {
            socket = new Socket("127.0.0.1", 8000);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            // Receive server's public key
            serverPublicKey = (PublicKey) objectInputStream.readObject();
            // System.out.println(serverPublicKey);
            System.out.println("> Received Public Key: " + Base64.getEncoder().encodeToString(serverPublicKey.getEncoded()));


            // AES key 생성
            System.out.println("> Creating AES 256 Key...");

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, secureRandom);
            aes256Key = keyGenerator.generateKey();

            bytesAES256Key = aes256Key.getEncoded();
            // secretKeySpec = new SecretKeySpec(AES256Key, "AES");
            System.out.println("\tAES 256 Key: " + Base64.getEncoder().encodeToString(bytesAES256Key));

                // AES key를 server의 public 키로 암호화
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, serverPublicKey);
            byte[] encryptedAES256Key = cipher.doFinal(aes256Key.getEncoded());
            String stringEncryptedAES256Key = Base64.getEncoder().encodeToString(encryptedAES256Key);
            System.out.println("\tEncrypted AES Key: " + stringEncryptedAES256Key);

            // IV 생성
            byte[] iv = new byte[16];
            secureRandom.nextBytes(iv);
            byte[] encryptedIV = cipher.doFinal(iv);
            String stringEncryptedIV = Base64.getEncoder().encodeToString(encryptedIV);
            System.out.println("> IV: " + Base64.getEncoder().encodeToString(iv));
            System.out.println("\t Encrypted IV: " + stringEncryptedIV);


            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aes256Key, new IvParameterSpec(iv, 0, 16));


            // Output은 쓰레드 사용해서 동시에 입, 출력이 가능하게 함
            ClientOutput clientOutput = new ClientOutput(socket, aes256Key, stringEncryptedAES256Key, iv, stringEncryptedIV);
            clientOutput.start();

            while(true) {
                // 소켓으로부터 message 받아오기
                receiveMsg = reader.readLine();
                // 복호화
                byte[] bytesReceiveMsg = Base64.getDecoder().decode(receiveMsg);
                String decryptedMsg = new String(cipher.doFinal(bytesReceiveMsg));
                System.out.println("> Received : " + decryptedMsg);
                System.out.println("\t Encrypted Message : " + receiveMsg);

                // exit 이면 종료
                if (decryptedMsg.substring(0, 7).equals("exit  [")) {
                    break;
                }
            }
            if (!socket.isClosed()) {
                socket.close();
            }

            System.out.println("Connection closed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientOutput extends Thread{
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    String encryptedAES256Key;
    String encryptedIV;
    SecretKey AESKey;
    Cipher cipher;

    ClientOutput(Socket socket, SecretKey AESKey, String encryptedAES256Key, byte[] iv, String encryptedIV){
        this.socket = socket;
        this.AESKey = AESKey;
        this.encryptedAES256Key = encryptedAES256Key;
        this.encryptedIV = encryptedIV;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, AESKey, new IvParameterSpec(iv, 0, 16));
        } catch (Exception e) {
            ClientOutput.interrupted();
            e.printStackTrace();
        }
    }
    public void run() {
        String inputMsg;
        String inputMsgTime;

        // 암호화한 AES key 전송
        writer.println(encryptedAES256Key);
        writer.flush();

        // 암화한 IV 전송
        writer.println(encryptedIV);
        writer.flush();

        while(true) {
            try {
                // 입력받기
                inputMsg = reader.readLine();

                inputMsgTime = inputMsg + "  [ " + LocalDateTime.now().toString() + " ] ";

                // inputMsg 암호화
                byte[] encryptedMsg = cipher.doFinal(inputMsgTime.getBytes(StandardCharsets.UTF_8));
                String encryptedString = Base64.getEncoder().encodeToString(encryptedMsg);

                // 전송
                writer.println(encryptedString);
                writer.flush();

                if(inputMsg.equals("exit")){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
