package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class PlayerTest {
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Котики против собачек", "РПГ");
    Game game3 = store.publishGame("Паззл", "Аркады");

    @Test
    public void shouldPlayerPlayGame(){ //не перезаписывает время игры
        Player player = new Player("Petya");
        player.installGame(game1);
        int actual = player.play(game1, 10);
        int expected = 10;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldPlayNotInstallGame() { //не выкидывает RuntimeException
        Player player = new Player("Petya");
        player.installGame(game1);
        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game3, 10);
        });
    }

    @Test
    public void shouldNotReinstallGame(){ //переустанавливает имеющуюся игру, а не должен
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 10);
        player.installGame(game1);
        int actual = player.sumGenre(game1.getGenre());
        int expected = 0;
        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void shouldSumGenreIfOneGame() { // метод play все еще не перезаписывает время и сумма не работает
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSomeGame() { // метод play все еще не перезаписывает время и сумма не работает
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game3, 5);

        int expected = 8;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNoGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 0;
        int actual = player.sumGenre("РПГ");
        assertEquals(expected, actual);
    }
    @Test
    public void shouldMostPlayerByGenreExist() { //возвращает null вместо game3
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game3, 5);

        assertEquals(game3, player.mostPlayerByGenre("Аркады"));
    }

    @Test
    public void shouldMostPlayerByGenreNotExist() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game3, 5);

        assertEquals(null, player.mostPlayerByGenre("РПГ"));
    }

    // другие ваши тесты
}
