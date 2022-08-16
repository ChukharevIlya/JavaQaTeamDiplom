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

    // тест добавления игры игроку, если игра уже была, никаких изменений происходить не должно
    @Test
    public void shouldInstallGameForPlayer() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
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
}

