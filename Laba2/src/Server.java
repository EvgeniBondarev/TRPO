import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;
    private static Map<String, ObjectOutputStream> clients = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Новый клиент подключен: " + socket);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                String username = (String) inputStream.readObject();
                clients.put(username, outputStream);

                // Отправляем список активных пользователей новому клиенту
                sendActiveUsersList();

                new ServerThread(socket, inputStream, outputStream, username).start();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void sendActiveUsersList() {
        try {
            for (Map.Entry<String, ObjectOutputStream> entry : clients.entrySet()) {
                entry.getValue().writeObject("[Сервер]: Пользователь " + entry.getKey() + " активен");
                entry.getValue().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerThread extends Thread {
        private final Socket socket;
        private final ObjectInputStream inputStream;
        private final ObjectOutputStream outputStream;
        private final String username;

        public ServerThread(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream, String username) {
            this.socket = socket;
            this.inputStream = inputStream;
            this.outputStream = outputStream;
            this.username = username;
        }

        public void run() {
            try {
                while (true) {
                    String recipient = (String) inputStream.readObject();
                    String message = (String) inputStream.readObject();

                    ObjectOutputStream recipientStream = clients.get(recipient);
                    if (recipientStream != null) {
                        recipientStream.writeObject(username + ": " + message);
                        recipientStream.flush();
                    } else {
                        outputStream.writeObject("Пользователь " + recipient + " не найден");
                        outputStream.flush();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
