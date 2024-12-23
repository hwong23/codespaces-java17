import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimpleStackExample {
    public static void main(String[] args) {
        // Create a stack using Deque
        Deque<Integer> stack = new ArrayDeque<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Push an element");
            System.out.println("2. Pop an element");
            System.out.println("3. Peek at the top element");
            System.out.println("4. Check if the stack is empty");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter a number to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    System.out.println("Pushed " + value + " onto the stack.");
                }
                case 2 -> {
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty! Nothing to pop.");
                    } else {
                        int popped = stack.pop();
                        System.out.println("Popped " + popped + " from the stack.");
                    }
                }
                case 3 -> {
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty! Nothing to peek.");
                    } else {
                        System.out.println("Top element is: " + stack.peek());
                    }
                }
                case 4 -> {
                    System.out.println(stack.isEmpty() ? "Stack is empty." : "Stack is not empty.");
                }
                case 5 -> {
                    running = false;
                    System.out.println("Exiting program.");
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
