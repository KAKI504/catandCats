package kg.attractor.java.task2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // исправьте код так, что бы на экран выводилось что-то вроде.
        // Я Misty. Я прыгаю!
        // Я Tibbles. Я сплю!
        // Я Ginger. Я кушаю!
        // для решения примените лямбда-выражения, 

        // каждый кот должен уметь выполнять что-то своё
        var cats = new ArrayList<ActiveCat>();
        cats.add(new ActiveCat(ActiveCat::jump));
        cats.add(new ActiveCat(ActiveCat::sleep));
        cats.add(new ActiveCat(ActiveCat::eat));
        // добавьте ещё два-три кота, с совершенно другими действиями
        cats.add(new ActiveCat(() -> "Я мурлыкаю!"));
        cats.add(new ActiveCat(() -> "Я играю с мячиком!"));
        cats.forEach(ActiveCat::doAction);
    }

}
