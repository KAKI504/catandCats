package kg.Kinopoisk.Task2;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MovieManager {
    private final List<Movie> movies;

    public MovieManager(String jsonFilePath) {
        this.movies = loadMoviesFromFile(jsonFilePath);
    }

    private List<Movie> loadMoviesFromFile(String jsonFilePath) {
        File file = new File(jsonFilePath);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + jsonFilePath);
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, MovieList.class).getMovies();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void printFilteredMovies(Predicate<Movie> filter) {
        List<Movie> filteredMovies = movies.stream()
                .filter(filter)
                .collect(Collectors.toList());

        if (filteredMovies.isEmpty()) {
            System.out.println("\nНичего не найдено");
            return;
        }

        System.out.printf("\nНайдено результатов: %d\n", filteredMovies.size());
        filteredMovies.forEach(System.out::println);
    }

    public void printMovies() {
        if (movies.isEmpty()) {
            System.out.println("\nСписок фильмов пуст");
            return;
        }

        System.out.println("\nСписок всех фильмов:");
        movies.forEach(System.out::println);
    }

    public void searchMoviesByName(String name) {
        String searchName = name.toLowerCase();
        System.out.printf("\nПоиск фильмов по названию '%s':\n", name);
        printFilteredMovies(movie ->
                movie.getName().toLowerCase().contains(searchName));
    }

    public void searchMoviesByActor(String actorName) {
        String searchActor = actorName.toLowerCase();
        System.out.printf("\nПоиск фильмов с актером '%s':\n", actorName);
        printFilteredMovies(movie ->
                movie.getCast().stream()
                        .anyMatch(cast ->
                                cast.getFullName().toLowerCase().contains(searchActor)));
    }

    public void searchMoviesByDirector(String directorName) {
        String searchDirector = directorName.toLowerCase();
        System.out.printf("\nПоиск фильмов режиссера '%s':\n", directorName);
        printFilteredMovies(movie ->
                movie.getDirector().getFullName().toLowerCase().contains(searchDirector));
    }

    public void searchMoviesByYear(String year) {
        try {
            int searchYear = Integer.parseInt(year);
            System.out.printf("\nПоиск фильмов %d года:\n", searchYear);
            printFilteredMovies(movie -> movie.getYear() == searchYear);
        } catch (NumberFormatException e) {
            System.out.println("\nНекорректный формат года");
        }
    }

    public void showActorRoles(String actorName) {
        String searchActor = actorName.toLowerCase();
        List<String> roles = movies.stream()
                .flatMap(movie -> movie.getCast().stream()
                        .filter(cast -> cast.getFullName().toLowerCase().contains(searchActor))
                        .map(cast -> String.format("• Фильм: %s\n  Роль:  %s",
                                movie.getName(), cast.getRole())))
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            System.out.println("\nАктер не найден");
            return;
        }

        System.out.printf("\nСписок ролей актера '%s':\n", actorName);
        System.out.println("=".repeat(50));
        roles.forEach(System.out::println);
        System.out.println("=".repeat(50));
    }

    public void listAllActorsAndRoles() {
        if (movies.isEmpty()) {
            System.out.println("\nСписок актеров пуст");
            return;
        }

        System.out.println("\nСписок всех актеров по фильмам:");
        System.out.println("=".repeat(50));
        movies.forEach(movie -> {
            System.out.printf("\nФильм: %s\n", movie.getName());
            movie.getCast().stream()
                    .map(cast -> "  • " + cast.toString())
                    .sorted()
                    .forEach(System.out::println);
        });
        System.out.println("=".repeat(50));
    }

    private static class MovieList {
        private List<Movie> movies;
        public List<Movie> getMovies() {
            return movies;
        }
    }
}