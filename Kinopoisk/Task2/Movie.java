package kg.Kinopoisk.Task2;

import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    private final String name;
    private final int year;
    private final String description;
    private final Director director;
    private final List<Cast> cast;

    public Movie(String name, int year, String description, Director director, List<Cast> cast) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.director = director;
        this.cast = cast;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Director getDirector() {
        return director;
    }

    public List<Cast> getCast() {
        return cast;
    }

    @Override
    public String toString() {
        String separator = "=".repeat(50);
        return String.format("""
                %s
                Название:  %s
                Год:      %d
                Тип:      %s
                Режиссёр: %s
                
                Актёры:
                %s
                %s
                """,
                separator,
                name,
                year,
                description,
                director.getFullName(),
                cast.stream()
                        .map(cast -> "   • " + cast.toString())
                        .collect(Collectors.joining("\n")),
                separator);
    }
}