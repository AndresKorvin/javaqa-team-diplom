package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @ParameterizedTest
    @CsvSource({
            "Дюна, Стратегия, Дум, Шутер, NFS, Гонки", //Все игры с разными названиями и жанрами
            "Дюна, Стратегия, Дум, Шутер, Дюна, Гонки", //Игра №3 с повторяющимся называнием
            "Дюна, Стратегия, Дум, Шутер, NFS, Стратегия", //Игра №3 с повторяющимся жанром
            "Дюна, Стратегия, Дум, Шутер, Дюна, Стратегия" //Игра №3 с повторяющимися и названием, и жанром
    })
    public void shouldContainsGame(String name1, String genre1, String name2, String genre2, String name3, String genre3) {
        // Проверка двух методов store.publishGame и store.containsGame, так как один без другого проверить невозможно, из-за того что поля приватные
        // Баг - Метод containsGame класса GameStore не проверяет наличие Последнего элемента в списке List<Game> games

        GameStore store = new GameStore();
        Game game1 = store.publishGame(name1, genre1);
        Game game2 = store.publishGame(name2, genre2);
        Game game3 = store.publishGame(name3, genre3);

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
    }

    @Test
    public void shouldRememberDirectoryObject() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        GameStore expected = store;
        GameStore actual = game.getStore();

        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @CsvSource({
            "Дюна, Стратегия, Дум, Шутер, NFS, Гонки", //Все игры с разными названиями и жанрами
            "Дюна, Стратегия, Дум, Шутер, Дюна, Гонки", //Игра №3 с повторяющимся называнием
            "Дюна, Стратегия, Дум, Шутер, NFS, Стратегия", //Игра №3 с повторяющимся жанром
    })
    public void shouldNotContainsGame(String name1, String genre1, String name2, String genre2, String name3, String genre3) {

        GameStore storeTest = new GameStore();
        Game game1 = storeTest.publishGame(name1, genre1);

        GameStore store = new GameStore();
        Game game2 = store.publishGame(name2, genre2);
        Game game3 = store.publishGame(name3, genre3);

        assertFalse(storeTest.containsGame(game2));
        assertFalse(storeTest.containsGame(game3));
        assertFalse(store.containsGame(game1));

    }

    @ParameterizedTest
    @CsvSource({
            "Андрей, 4, Игорь, 3, Роман, 5", // 3 разных игрока с одной игрой
            "Игорь, 0, Роман, 0, Андрей, 1", // 3 игрока, максимальное время - 1 час
            "Андрей, 7, Игорь, 3, Игорь, 6", // 2 игрока, один из них играл несколько раз
            "Игорь, 0, Игорь, 0, Андрей, 100" // 2 игрока, один из них играл два раза по 0 часов

    })
    public void shouldAddPlayTime(String name1, int hours1, String name2, int hours2, String name3, int hours3) {
        // Проверка двух методов store.addPlayTime и store.getMostPlayer, так как один без другого проверить невозможно, из-за того что поля приватные
        // Баг - Метод addPlayTime класса GameStore не суммируется с прошлым значением для этого игрока
        // Баг - Метод addPlayTime класса GameStore не выводит время, если максимальное время - 1 час

        GameStore store = new GameStore();

        store.addPlayTime(name1, hours1);
        store.addPlayTime(name2, hours2);
        store.addPlayTime(name3, hours3);

        String expected = name3;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotGetMostPlayer() {

        GameStore store = new GameStore();

        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "Андрей, 4, Игорь, 3, Роман, 5", // 3 разных игрока с разным временем
            "Андрей, 7, Игорь, 3, Игорь, 6", // 2 игрока, один из них играл несколько раз
            "Игорь, 0, Игорь, 0, Андрей, 1" // 2 игрока, у одного два раза по 0 часов

    })
    public void shouldGetSumPlayedTime(String name1, int hours1, String name2, int hours2, String name3, int hours3) {
        // Баг - Метод getSumPlayedTime класса GameStore не суммирует общее количество времени всех игроков, проведённого за играми этого каталога

        GameStore store = new GameStore();

        store.addPlayTime(name1, hours1);
        store.addPlayTime(name2, hours2);
        store.addPlayTime(name3, hours3);

        int expected = hours1 + hours2 + hours3;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}
