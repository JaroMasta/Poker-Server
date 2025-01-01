package pl.edu.agh.kis.pz1.game;

import lombok.Getter;

import java.util.List;

public class GameVariants {
    @Getter
    public static List<String> gameVariants = List.of("Five Card Draw");
    public String toString() {
        //TODO make toString method more user friendly meaning have it return a numbered list of available games
        return "1. Five Card Draw";
    }
}
