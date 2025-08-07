import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class User {
    private String name;
    private List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.issue();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();
    private Map<String, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(String name) {
        users.put(name, new User(name));
    }

    public void issueBook(String userName, String bookTitle) {
        User user = users.get(userName);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.isIssued()) {
                user.borrowBook(book);
                System.out.println("Book issued to " + userName);
                return;
            }
        }

        System.out.println("Book not available!");
    }

    public void returnBook(String userName, String bookTitle) {
        User user = users.get(userName);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        for (Book book : user.getBorrowedBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                user.returnBook(book);
                System.out.println("Book returned by " + userName);
                return;
            }
        }

        System.out.println("This user did not borrow that book.");
    }

    public void showAvailableBooks() {
        System.out.println("\nAvailable books:");
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        library.registerUser("Alice");
        library.registerUser("Bob");

        library.showAvailableBooks();

       
        library.issueBook("Alice", "1984");
        library.issueBook("Bob", "1984");  
        library.issueBook("Bob", "To Kill a Mockingbird");

        library.showAvailableBooks();

        
        library.returnBook("Alice", "1984");
        library.issueBook("Bob", "1984");  

        library.showAvailableBooks();
    }
}
