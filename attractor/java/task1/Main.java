package kg.attractor.java.task1;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        var cats = Cat.makeCats(10);
        System.out.println("Исходный список:");
        Printer.print(cats);

        // А сюда добавьте код, который будет сортировать коллекцию котов
        // используйте лямбда-выражения и ссылки на методы
        // cats.sort(?);
        // Printer.print(cats);

        System.out.println("Сортировка по породе:");
        cats.sort(Cat.byBreed);
        Printer.print(cats);

        System.out.println("Сортировка по имени и возрасту:");
        cats.sort(Cat.byNameAndAge);
        Printer.print(cats);

        System.out.println("Список без кошек определенного цвета:");
        var filteredByColor = cats.stream()
                .filter(Cat.byColor(Cat.Color.TABBY))
                .collect(Collectors.toList());
        Printer.print(filteredByColor);

        System.out.println("Список без кошек с именем длины 5:");
        var filteredByNameLength = cats.stream()
                .filter(Cat.byNameLength(5))
                .collect(Collectors.toList());
        Printer.print(filteredByNameLength);
    }

}
