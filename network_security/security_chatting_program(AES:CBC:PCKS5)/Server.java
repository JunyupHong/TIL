import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.LocalDateTime;
import java.util.Base64;

public class Server {
    public static void main(String[] args){
        PublicKey publicKey;
        PrivateKey privateKey;

        BufferedReader reader;
        ObjectOutputStream objectOutputStream;

        Cipher cipher;
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator;

        try {
            ServerSocket server = new ServerSocket(8000);

            // client 연결
            Socket socket = server.accept();
            System.out.println("Connected Client!");

            // RSA key pair 생성
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
            System.out.println("> Creating RSA Key Pair...");
            System.out.println("\tPrivate Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            System.out.println("\tPublic Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));


            // 연결된 client에 public key 전송
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // System.out.println(publicKey);
            objectOutputStream.writeObject(publicKey);
            objectOutputStream.flush();

            // 연결된 client 로부터 AES key를 받음
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // private key로 복호화
            String message = reader.readLine();
            byte[] encryptedAES256Key = Base64.getDecoder().decode(message.getBytes());
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] AES256Key = cipher.doFinal(encryptedAES256Key);

            System.out.println("> Received AES Key: " + message);
            System.out.println("\t Decrypted AES Key: " + Base64.getEncoder().encodeToString(AES256Key));

            // client로 부터 IV를 받음
            message = reader.readLine();
                // private key로 복호화
            byte[] encryptedIV = Base64.getDecoder().decode(message.getBytes());
            byte[] iv = cipher.doFinal(encryptedIV);
            System.out.println("> Received IV: " + message);
            System.out.println("\t Decrypted IV: " + Base64.getEncoder().encodeToString(iv));

            // thread 생성
                // input thread
            Input input = new Input(socket, privateKey, AES256Key, iv);
            input.start();
                // output thread
            Output output = new Output(socket, publicKey, AES256Key, iv);
            output.start();

            input.interrupted();
            output.interrupted();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Input extends Thread {
    BufferedReader reader;
    Socket socket;
    String receiveMsg;

    PrivateKey privateKey;
    Cipher cipher;
    byte[] AESKey;
    byte[] iv;
    Input(Socket socket, PrivateKey privateKey, byte[] AESKey, byte[] iv){
        this.socket = socket;
        this.privateKey = privateKey;
        this.AESKey = AESKey;
        this.iv = iv;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            SecretKey aes256Key = new SecretKeySpec(AESKey, 0, AESKey.length, "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aes256Key, new IvParameterSpec(iv, 0, 16));
        } catch(Exception e) {
            e.printStackTrace();
        }
        while(true) {
            if (Input.interrupted()) break;
            try {
                receiveMsg = reader.readLine();

                // 복호화
                byte[] bytesReceiveMsg = Base64.getDecoder().decode(receiveMsg);
                String decryptedMsg = new String(cipher.doFinal(bytesReceiveMsg));
                System.out.println("> Received : " + decryptedMsg);
                System.out.println("\t Encrypted Message : " + receiveMsg);

                if (decryptedMsg.substring(0, 7).equals("exit  [")) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        System.out.println("Connection closed!");
        try {
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Output extends Thread {
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;
    PublicKey publicKey;
    DataOutputStream dataOutputStream;
    Cipher cipher;
    byte[] AESKey;
    byte[] iv;
    Output(Socket socket, PublicKey publicKey, byte[] AESKey, byte[] iv){
        this.socket = socket;
        this.publicKey = publicKey;
        this.AESKey = AESKey;
        this.iv = iv;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            Output.interrupted();
        }
    }
    public void run() {
        String inputMsg;
        String inputMsgTime;
        while(true) {
            if (Output.interrupted()) break;
            try {
                //키보드로부터 입력
                inputMsg = reader.readLine();

                inputMsgTime = inputMsg + "  [ " + LocalDateTime.now().toString() + " ] ";
                // inputMsg 암호화
                SecretKey aes256Key = new SecretKeySpec(AESKey, 0, AESKey.length, "AES");
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, aes256Key, new IvParameterSpec(iv, 0, 16));
                byte[] encryptedMsg = cipher.doFinal(inputMsgTime.getBytes(StandardCharsets.UTF_8));

                String encryptedString = Base64.getEncoder().encodeToString(encryptedMsg);
//                System.out.println("send: " + encryptedString);
//                dataOutputStream.writeBytes(encryptedString + "\n");
                writer.println(encryptedString);
                writer.flush();

                // exit이면 종료
                if(inputMsg.equals("exit")){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}