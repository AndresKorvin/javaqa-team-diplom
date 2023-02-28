package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameStoreTest {

    // Тест сразу двух методов store.publishGame и store.containsGame
    // Так как поле List<Game> games класса GameStore приватное, метод publishGame не проверить без метода containsGame
    @ParameterizedTest
    @CsvSource({
            "Дюна, Стратегия, Дум, Шутер, NFS, Гонки", //Все игры с разными названиями и жанрами
            "Дюна, Стратегия, Дум, Шутер, Дюна, Гонки", //Игра №3 с повторяющимся называнием
            "Дюна, Стратегия, Дум, Шутер, NFS, Стратегия" //Игра №3 с повторяющимся жанром
    })
    public void shouldAddGame(String name1,String genre1,String name2,String genre2,String name3,String genre3) {

        GameStore store = new GameStore();
        Game game1 = store.publishGame(name1, genre1);
        Game game2 = store.publishGame(name2, genre2);
        Game game3 = store.publishGame(name3, genre3);

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
    }

    // Тест сразу двух методов store.publishGame и store.containsGame
    // Так как поле List<Game> games класса GameStore приватное, метод publishGame не проверить без метода containsGame
    @ParameterizedTest
    @CsvSource({
            "Дюна, Стратегия, Дум, Шутер, Дюна, Стратегия", //Игра №3 с повторяющимися и названием, и жанром
    })
    public void shouldNotAddGame(String name1,String genre1,String name2,String genre2,String name3,String genre3) {

        GameStore store = new GameStore();
        Game game1 = store.publishGame(name1, genre1);
        Game game2 = store.publishGame(name2, genre2);
        Game game3 = store.publishGame(name3, genre3);

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));

        assertFalse(store.containsGame(game3)); // Не содержит game3
    }

    /**
     * Регистрирует количество времени, которое проиграл игрок
     * за игрой этого каталога. Игрок задаётся по имени. Время должно
     * суммироваться с прошлым значением для этого игрока
     */
    @ParameterizedTest
    @CsvSource({
            "Андрей, 1, Игорь, 3, Роман, 5,     1, 3, 5", //Игра №3 с повторяющимися и названием, и жанром

    })
    public void shouldAddPlayTime(String name1, int hours1, String name2, int hours2, String name3, int hours3,
                                  int resultHours1, int resultHours2, int resultHours3) {
        GameStore store = new GameStore();

        store.addPlayTime(name1, hours1);
        store.addPlayTime(name2, hours2);
        store.addPlayTime(name3, hours3);



    }
}
