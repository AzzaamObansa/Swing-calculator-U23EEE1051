import java.util.Scanner;
import java.util.InputMismatchException;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Java Console Calculator");
        System.out.println("=======================");

        while (running) {
            displayMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            if (choice == 5) {
                running = false;
                System.out.println("Exiting calculator. Goodbye!");
                continue;
            }

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }

            double num1 = getDoubleInput(scanner, "Enter first number: ");
            double num2 = getDoubleInput(scanner, "Enter second number: ");
            performOperation(choice, num1, num2);
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nAvailable operations:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Exit");
    }

    private static void performOperation(int choice, double num1, double num2) {
        switch (choice) {
            case 1 -> System.out.printf("Result: %.2f + %.2f = %.2f%n", num1, num2, num1 + num2);
            case 2 -> System.out.printf("Result: %.2f - %.2f = %.2f%n", num1, num2, num1 - num2);
            case 3 -> System.out.printf("Result: %.2f * %.2f = %.2f%n", num1, num2, num1 * num2);
            case 4 -> {
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero!");
                } else {
                    System.out.printf("Result: %.2f / %.2f = %.2f%n", num1, num2, num1 / num2);
                }
            }
        }
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
