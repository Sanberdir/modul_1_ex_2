package students_and_books;

import java.util.Objects;


public class Book {
    private final String title;
    private final int pages;
    private final int year;

    public Book(String title, int pages, int year) {
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    public int getPages() { return pages; }
    public int getYear() { return year; }

    public static Book parse(String data) {
        String[] parts = data.split(",");
        if (parts.length < 3) return new Book("Неизвестно", 0, 0);
        return new Book(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year);
    }

    @Override
    public String toString() {
        return String.format("«%s» (страниц: %d, год: %d)", title, pages, year);
    }
}
