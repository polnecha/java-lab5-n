import java.util.Scanner;

public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
                return value;
            } else {
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine();
            }
        }
    }

    public static String readString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}