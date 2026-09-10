package students_and_books;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Student {
    private final String name;
    private final List<Book> inventoryBooks;

    public Student(String name, List<Book> inventoryBooks) {
        this.name = name;
        this.inventoryBooks = inventoryBooks;
    }

    public List<Book> getBooks() { return inventoryBooks; }
    /// Реализация считывания студентов, нахождение их по знаку -> |
    public static Student parse(String line) {
        String[] entityParts = line.split("\\|");
        String name = entityParts[0];

        List<Book> books = Arrays.stream(entityParts[1].split(";"))
                .map(Book::parse)
                .collect(Collectors.toList());

        return new Student(name, books);
    }

    @Override
    public String toString() {
        return "Студент{имя='" + name + "', книг=" + inventoryBooks.size() + "}";
    }
}
