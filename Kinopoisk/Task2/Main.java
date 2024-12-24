package kg.Kinopoisk.Task2;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        MovieManager manager = new MovieManager("data/movies.json");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String input = scanner.nextLine();

            if (processMenuChoice(input, manager, scanner)) {
                break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
            
            Выберите действие:
            1 - Показать все фильмы
            2 - Искать фильм по названию
            3 - Найти фильмы по имени актера
            4 - Найти фильмы по имени режиссера
            5 - Найти фильмы по году выпуска
            6 - Показать роли актера
            7 - Показать всех актёров и их роли
            0 - Выход""");
    }

    private static boolean processMenuChoice(String input, MovieManager manager, Scanner scanner) {
        return switch (input) {
            case "1" -> handleOperation(manager::printMovies);
            case "2" -> handleOperation("Введите название для поиска: ",
                    manager::searchMoviesByName, scanner);
            case "3" -> handleOperation("Введите имя актера: ",
                    manager::searchMoviesByActor, scanner);
            case "4" -> handleOperation("Введите имя режиссера: ",
                    manager::searchMoviesByDirector, scanner);
            case "5" -> handleOperation("Введите год выпуска: ",
                    manager::searchMoviesByYear, scanner);
            case "6" -> handleOperation("Введите имя актера: ",
                    manager::showActorRoles, scanner);
            case "7" -> handleOperation(manager::listAllActorsAndRoles);
            case "0" -> {
                System.out.println("Выход из программы.");
                yield true;
            }
            default -> {
                System.out.println("Неверный ввод. Повторите попытку.");
                yield false;
            }
        };
    }

    private static boolean handleOperation(Runnable operation) {
        operation.run();
        return false;
    }

    private static boolean handleOperation(String prompt, Consumer<String> operation, Scanner scanner) {
        System.out.print(prompt);
        operation.accept(scanner.nextLine());
        return false;
    }
}