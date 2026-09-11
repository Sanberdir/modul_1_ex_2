import students_and_books.Book;
import students_and_books.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== НАЧАЛО ВЫПОЛНЕНИЯ ПРОГРАММЫ ===");

        List<Student> students = loadStudentsFromFile("./students.txt");

        System.out.println("[Задание 3] Коллекция List<Student> успешно заполнена из файла.\n");

        System.out.println("=== КНИГИ КАЖДОГО СТУДЕНТА ===");

        for (Student student : students) {
            student.printBooks();
            System.out.println();
        }

        System.out.println("=== ЗАПУСК ЕДИНОГО СТРИМА (ЗАДАНИЕ 4) ===");

        students.stream()
                .peek(student -> System.out.println("[Задание 4.1] Вывод студента: " + student))
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .peek(book -> System.out.println("   -> [Задание 4.4-4.6] Книга прошла сортировку, уникализацию и фильтр: " + book))
                .limit(3)
                .map(Book::getYear)
                .peek(year -> System.out.println("      -> [Задание 4.7-4.8] Год попал в лимит из 3-х элементов: " + year))
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("\n[Задание 4.10] Год выпуска найденной книги: " + year),
                        () -> System.out.println("\n[Задание 4.10] Подходящая книга отсутствует в базе данных.")
                );

        System.out.println("\n=== ВСЕ ЗАДАНИЯ УСПЕШНО ВЫПОЛНЕНЫ ===");
    }

    private static List<Student> loadStudentsFromFile(String fileName) {
        List<Student> loadedStudents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    loadedStudents.add(Student.parse(line));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + fileName + ". Причина: " + e.getMessage());
        }
        return loadedStudents;
    }
}
