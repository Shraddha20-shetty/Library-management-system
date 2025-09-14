import java.util.*;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    @Override
    public String toString() {
        return id + ". " + title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // Admin Functions
    public void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("✅ Book added successfully!");
    }

    public void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = sc.nextInt();
        books.removeIf(b -> b.id == id);
        System.out.println("✅ Book removed successfully!");
    }

    // User Functions
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("❌ No books in the library.");
            return;
        }
        System.out.println("\n📚 Available Books:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void searchBook() {
        sc.nextLine();
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;

        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword) || b.author.toLowerCase().contains(keyword)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("❌ No matching book found.");
        }
    }

    public void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id && !b.isIssued) {
                b.isIssued = true;
                System.out.println("✅ Book issued successfully!");
                return;
            }
        }
        System.out.println("❌ Book not available.");
    }

    public void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        for (Book b : books) {
            if (b.id == id && b.isIssued) {
                b.isIssued = false;
                System.out.println("✅ Book returned successfully!");
                return;
            }
        }
        System.out.println("❌ Invalid book ID or book not issued.");
    }
}

public class DigitalLibraryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n==== DIGITAL LIBRARY SYSTEM ====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Simple admin login
                    System.out.print("Enter Admin Password: ");
                    String pass = sc.next();
                    if (!pass.equals("admin123")) {
                        System.out.println("❌ Wrong Password!");
                        break;
                    }
                    while (true) {
                        System.out.println("\n---- ADMIN MENU ----");
                        System.out.println("1. Add Book");
                        System.out.println("2. Remove Book");
                        System.out.println("3. View Books");
                        System.out.println("4. Logout");
                        System.out.print("Enter choice: ");
                        int adminChoice = sc.nextInt();

                        if (adminChoice == 1) library.addBook();
                        else if (adminChoice == 2) library.removeBook();
                        else if (adminChoice == 3) library.viewBooks();
                        else if (adminChoice == 4) break;
                        else System.out.println("❌ Invalid choice!");
                    }
                    break;

                case 2:
                    while (true) {
                        System.out.println("\n---- USER MENU ----");
                        System.out.println("1. View Books");
                        System.out.println("2. Search Book");
                        System.out.println("3. Issue Book");
                        System.out.println("4. Return Book");
                        System.out.println("5. Logout");
                        System.out.print("Enter choice: ");
                        int userChoice = sc.nextInt();

                        if (userChoice == 1) library.viewBooks();
                        else if (userChoice == 2) library.searchBook();
                        else if (userChoice == 3) library.issueBook();
                        else if (userChoice == 4) library.returnBook();
                        else if (userChoice == 5) break;
                        else System.out.println("❌ Invalid choice!");
                    }
                    break;

                case 3:
                    System.out.println("👋 Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}