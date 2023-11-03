package Sample;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Book {
    private int ISBN;
    private String title;
    private boolean isCheckedOut;

    public Book(int ISBN, String title) {
        this.ISBN = ISBN;
        this.title = title;
        this.isCheckedOut = false;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        isCheckedOut = true;
    }

    public void returnBook() {
        isCheckedOut = false;
    }
}

class Library {
    private List<Book> books;
    private Map<Integer, Book> bookMap;

    public Library() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    public void addBook(Book book) {
        if (bookMap.containsKey(book.getISBN())) {
            throw new IllegalArgumentException("A book with the same ISBN already exists in the library.");
        }
        books.add(book);
        bookMap.put(book.getISBN(), book);
    }

    public void checkOutBook(int ISBN) {
        Book book = bookMap.get(ISBN);
        if (book == null) {
            throw new IllegalArgumentException("The book with ISBN " + ISBN + " is not available in the library.");
        }
        if (book.isCheckedOut()) {
            throw new IllegalArgumentException("The book with ISBN " + ISBN + " is already checked out.");
        }
        book.checkOut();
    }

    public void returnBook(int ISBN) {
        Book book = bookMap.get(ISBN);
        if (book == null) {
            throw new IllegalArgumentException("The book with ISBN " + ISBN + " is not available in the library.");
        }
        if (!book.isCheckedOut()) {
            throw new IllegalArgumentException("The book with ISBN " + ISBN + " has not been checked out.");
        }
        book.returnBook();
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        
        library.addBook(new Book(1, "Book 1"));
        library.addBook(new Book(2, "Book 2"));

        try {
            
            library.checkOutBook(3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            
            library.returnBook(1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            
            library.addBook(new Book(1, "Duplicate Book"));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
