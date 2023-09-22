public class Book {

    private final String name;
    private final String author;
    private final String language;
    private final double rating;
    private final int index;
    private final int pages;
    private final int marks;
    private final int reviews;
    private final long UIN;
    private final long ISBN;

    public Book() {
        index = 0;
        name = "";
        author = "";
        rating = 0;
        UIN = 0;
        ISBN = 0;
        language = "";
        pages = 0;
        marks = 0;
        reviews = 0;
    }

    public Book(Integer index, String name, String author, double rating, long UIN, long ISBN, String language, int pages, int marks, int reviews) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.UIN = UIN;
        this.ISBN = ISBN;
        this.language = language;
        this.pages = pages;
        this.marks = marks;
        this.reviews = reviews;
    }

    public Book(Book curBook) {
        this.index = curBook.index;
        this.name = curBook.name;
        this.author = curBook.author;
        this.rating = curBook.rating;
        this.UIN = curBook.UIN;
        this.ISBN = curBook.ISBN;
        this.language = curBook.language;
        this.pages = curBook.pages;
        this.marks = curBook.marks;
        this.reviews = curBook.reviews;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public long getUIN() {
        return UIN;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public int getPages() {
        return pages;
    }

    public int getMarks() {
        return marks;
    }

    public int getReviews() {
        return reviews;
    }

    public void infoBook() {
        System.out.println(name + " (автор: " + author + ", рейтинг: " + rating + ", язык: " + language + ", кол-во страниц: " + pages);
    }
}
