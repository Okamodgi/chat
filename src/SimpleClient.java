import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Подключено к серверу.");

            // Получаем потоки ввода и вывода для обмена данными с сервером
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                System.out.print("Введите сообщение для сервера: ");
                Scanner consoleScanner = new Scanner(System.in);
                String messageToServer = consoleScanner.nextLine();
                out.println(messageToServer);

                if (messageToServer.equalsIgnoreCase("завершить")) {
                    break;
                }

                // Чтение ответа от сервера
                String response = in.nextLine();
                System.out.println("Ответ от сервера: " + response);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
