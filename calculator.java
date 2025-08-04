import java.util.Scanner;

public class calculator {

    public static double add(double a, double b) {
        return a + b;
    }
    public static double subtract(double a, double b) {
        return a - b;
    }
    public static double multiply(double a, double b) {
        return a * b;
    }
    public static double divide(double a, double b) {
        if (b == 0) {
            System.err.println("Error: Division by zero is not allowed.");
            return Double.NaN;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Calculator Menu ---");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice = scan.nextInt();

            if (choice == 5) {
                System.out.println("Exiting calculator. Goodbye!");
                running = false;
                break;
            }

            System.out.print("Enter first number: ");
            double num1 = scan.nextDouble();
            System.out.print("Enter second number: ");
            double num2 = scan.nextDouble();

            double result = 0;

            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    System.out.println("Result: " + result);
                    break;

                case 2:
                    result = subtract(num1, num2);
                    System.out.println("Result: " + result);
                    break;

                case 3:
                    result = multiply(num1, num2);
                    System.out.println("Result: " + result);
                    break;

                case 4:
                    result = divide(num1, num2);
                    if (!Double.isNaN(result)) {
                        System.out.println("Result: " + result);
                    }
                    break;

                default:
                    System.err.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
