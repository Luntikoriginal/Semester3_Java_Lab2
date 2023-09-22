import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        ArrayList<Book> books = new ArrayList<>();
        readAndCreateMassiveBooks("data_book.txt", books);
        ArrayList<Author> authors = new ArrayList<>();
        createMassiveAuthors(books, authors);
        outTableOnConsole(authors);
        outTableInFile(authors);
        infoAuthor(authors);
        infoBook(books);
    }

    public static void readAndCreateMassiveBooks(String file, ArrayList<Book> books) {
        try {
            Scanner sc = new Scanner(new File(file));
            ArrayList<String> data_book = new ArrayList<>();
            while(sc.hasNext()) {
                String str = sc.nextLine();
                data_book.add(str);
            }
            for (int i = 0; i + 9 < data_book.size(); ) {
                List<String> elements = data_book.subList(i, i + 10);
                if (checkAllStr(elements)) {
                    books.add(new Book(Integer.parseInt(elements.get(0)), elements.get(1), elements.get(2),
                            Double.parseDouble(elements.get(3)), Long.parseLong(elements.get(4)),
                            Long.parseLong(elements.get(5)), elements.get(6), Integer.parseInt(elements.get(7)),
                            Integer.parseInt(elements.get(8)), Integer.parseInt(elements.get(9))));
                    i += 10;
                } else {
                    i++;
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        }
        catch (Exception e) {
            System.out.println("Непредвиденная ошибка с чтением файла!");
        }
    }

    public static boolean checkAllStr(List<String> elements) {
        return elements.get(3).matches("[0-5].[0-9]+") && elements.get(4).matches("[0-9]{10}") &&
                elements.get(5).matches("[0-9]{13}") && elements.get(7).matches("[0-9]+") &&
                elements.get(8).matches("[0-9]+") && elements.get(9).matches("[0-9]+");
    }

    public static void createMassiveAuthors(ArrayList<Book> books, ArrayList<Author> authors) {
        for (Book book : books) {
            if (!authors.isEmpty()) {
                boolean flag = true;
                for (Author author : authors) {
                    if (book.getAuthor().equals(author.getName())) {
                        author.addBook(book);
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    authors.add(new Author());
                    authors.get(authors.size() - 1).setName(book.getAuthor());
                    authors.get(authors.size() - 1).addBook(book);
                }
            } else {
                authors.add(new Author());
                authors.get(0).setName(book.getAuthor());
                authors.get(0).addBook(book);
            }
        }
    }

    public static void outTableOnConsole(ArrayList<Author> authors) {
        System.out.printf("%-110s %-20s %-20s %-20s %s\n", "Автор(ы)", "Количество книг", "Средняя толщина", "Средний рейтинг", "Самая рейтинговая книга");
        for (Author author : authors) {
            System.out.printf("%-110s %-20d %-20.2f %-20.2f %s ISBN: %d\n", author.getName(), author.countBooks(), author.averagePages(), author.averageRating(), author.nameBestBook(), author.ISBNBestBook());
        }
    }

    public static void outTableInFile(ArrayList<Author> authors) {
        try {
            PrintWriter pw = new PrintWriter("Table of authors.txt");
            pw.printf("%-110s %-20s %-20s %-20s %s\n", "Автор(ы)", "Количество книг", "Средняя толщина", "Средний рейтинг", "Самая рейтинговая книга");
            for (Author author : authors) {
                pw.printf("%-110s %-20d %-20.2f %-20.2f %s ISBN: %d\n", author.getName(), author.countBooks(), author.averagePages(), author.averageRating(), author.nameBestBook(), author.ISBNBestBook());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
        } catch (Exception e) {
            System.out.println("Файл заполнен с ошибкой!");
        }
    }

    public static void infoAuthor(ArrayList<Author> authors) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите имя писателя: ");
        String nameAuthor = sc.nextLine();
        boolean flag = true;
        for (Author author : authors) {
            if (author.getName().equals(nameAuthor)) {
                flag = false;
                System.out.println("Книги писателя отсортированные по кол-ву рецензий:");
                for (Book book : author.sortReviews()) System.out.println(book.getName() + "  " + book.getReviews() + " рецензий");
                break;
            }
        }
        if (flag) System.out.println("Писатель не найден (возможно он работает в соавторстве)");
    }

    public static void infoBook(ArrayList<Book> books) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите назвнание книги или ISBN: ");
        String str = sc.nextLine();
        boolean flag = true;
        if (str.matches("[0-9]{13}")) {
            for (Book book : books) {
                if (book.getISBN() == Long.parseLong(str)) {
                    flag = false;
                    System.out.print("Найдена книга: ");
                    book.infoBook();
                    break;
                }
            }
        } else {
            for (Book book : books) {
                if (book.getName().contains(str)) {
                    System.out.print("Найдена книга: ");
                    flag = false;
                    book.infoBook();
                }
            }
        }
        if (flag) System.out.println("Книга не найдена!");
    }
}
