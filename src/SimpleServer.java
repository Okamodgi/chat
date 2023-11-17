import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try {
            // Создаем серверный сокет
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен. Ожидание подключения...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключен.");

            // Получаем потоки ввода и вывода для обмена данными с клиентом
            Scanner in = new Scanner(clientSocket.getInputStream());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String messageFromClient = in.nextLine();
                System.out.println("Получено от клиента: " + messageFromClient);

                // Проверка на завершение программы сервера
                if (messageFromClient.equalsIgnoreCase("завершить")) {
                    out.println("Сервер завершает выполнение.");
                    System.exit(0); // Завершаем программу сервера
                } else {
                    System.out.print("Введите сообщение для клиента: ");
                    Scanner consoleScanner = new Scanner(System.in);
                    String messageToClient = consoleScanner.nextLine();
                    out.println(messageToClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
