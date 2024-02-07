import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Подключено к серверу...");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ваше имя: ");
            String username = scanner.nextLine();
            outputStream.writeObject(username);

            // Выводим список активных пользователей при запуске клиента
            new Thread(() -> {
                try {
                    while (true) {
                        String message = (String) inputStream.readObject();
                        System.out.println(message);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                System.out.print("Введите имя получателя: ");
                String recipient = scanner.nextLine();
                System.out.print("Введите сообщение: ");
                String message = scanner.nextLine();

                outputStream.writeObject(recipient);
                outputStream.writeObject(message);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
