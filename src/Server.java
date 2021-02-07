import javax.crypto.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Server {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        ServerSocket server = new ServerSocket(6666);
        Socket socket = server.accept();
        String message = "Hello Client this is server";
        //
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        DataOutputStream dataOutputStream1 = new DataOutputStream(socket.getOutputStream());
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Sent Key = " + encodedKey);
        dataOutputStream1.writeUTF(encodedKey);
        //

        Cipher ciper = Cipher.getInstance("DES/ECB/PKCS5Padding");
        ciper.init(Cipher.ENCRYPT_MODE,key);
        byte[] text = ciper.doFinal(message.getBytes());

        //

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
        System.out.println("Send: " + message);


        ObjectOutputStream byteoutput = new ObjectOutputStream(socket.getOutputStream());
        byteoutput.writeObject(text);
        System.out.println("Send: " + text);


        socket.close();
        server.close();








    }
}
