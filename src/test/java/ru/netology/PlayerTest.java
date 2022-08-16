package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    // тест добавления игрока
    @Test
    public void shouldInstallPlayer() {
        Player player = new Player("PETR");

        String actual = "PETR";
        String expected = player.getName();

        assertEquals(actual, expected);
    }


    // тест метода, который принимает жанр игры (одно из полей объекта игры) и суммирует время,
    // проигранное во все игры этого жанра этим игроком
    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGame0() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 0);

        int expected = 0;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGameRPG() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 0;
        int actual = player.sumGenre("РПГ");
        assertEquals(expected, actual);
    }


    // тест RuntimeException в методе play
    @Test
    public void shouldPlayRuntimeException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> {
            player.play(game, 2);
        });

    }


    // тест метода, который принимает жанр и возвращает игру этого жанра, в которую играли больше всего
    // Если в игры этого жанра не играли, возвращается null
    @Test
    public void shouldMostPlayerByGenre() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Name1");
        player.installGame(game);
        player.play(game, 3);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);

    }

    @Test
    public void shouldMostPlayerByGenreNull() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Name1");
        player.installGame(game);
        player.play(game, 0);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);

    }

}

