import java.util.ArrayList;
import java.util.Comparator;

public class Author {
    private String name;
    private ArrayList<Book> books;

    public Author() {
        this.name = "";
        this.books = new ArrayList<>();
    }

    public Author(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Author(Author curAuthor) {
        this.name = curAuthor.name;
        this.books = curAuthor.books;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int countBooks() {
        return books.size();
    }

    public double averagePages() {
        int sum = 0;
        for (Book book : books) sum += book.getPages();
        return (double) sum / books.size();
    }

    public double averageRating() {
        double sum = 0;
        for (Book book : books) sum += book.getRating();
        return sum / books.size();
    }

    public Book bestBook() {
        books.sort(Comparator.comparing(Book::getRating).reversed());
        return books.get(0);
    }

    public String nameBestBook() {
        return bestBook().getName();
    }

    public long ISBNBestBook() {
        return bestBook().getISBN();
    }

    public ArrayList<Book> sortReviews() {
        books.sort(Comparator.comparing(Book::getReviews));
        return books;
    }
}
