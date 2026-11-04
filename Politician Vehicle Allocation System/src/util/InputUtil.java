
package util;

import java.util.Scanner;

public final class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    private InputUtil() {
    }

    public static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {

                System.out.println(
                    "Invalid input! Please enter a number."
                );
            }
        }
    }

    public static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty!");
        }
    }
}
