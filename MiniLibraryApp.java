package BookProject;

import java.util.*;

public class MiniLibraryApp {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final Map<Integer, Integer> issuedBooks = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void issueBook(int bookId, int userId) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.isIssued()) {
                book.setIssued(true);
                issuedBooks.put(bookId, userId);
                System.out.println("Book issued to user " + userId);
                return;
            }
        }
        System.out.println("Book not available or already issued.");
    }

    public void returnBook(int bookId) {
        if (issuedBooks.containsKey(bookId)) {
            for (Book book : books) {
                if (book.getId() == bookId) {
                    book.setIssued(false);
                    issuedBooks.remove(bookId);
                    System.out.println("Book returned successfully.");
                    return;
                }
            }
        } else {
            System.out.println("This book is not issued.");
        }
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static void main(String[] args) {
        MiniLibraryApp library = new MiniLibraryApp();
        Scanner sc = new Scanner(System.in);

        // Sample data
        library.addBook(new Book(1, "The Alchemist", "Paulo Coelho"));
        library.addBook(new Book(2, "1984", "George Orwell"));
        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. List Books");
            System.out.println("2. List Users");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> library.listBooks();
                case 2 -> library.listUsers();
                case 3 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(bookId, userId);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                }
                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
