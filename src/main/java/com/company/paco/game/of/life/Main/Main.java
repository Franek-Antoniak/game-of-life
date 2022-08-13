package com.company.paco.game.of.life.Main;

import com.company.paco.game.of.life.GameOfLife;
import com.company.paco.game.of.life.tools.Cleaner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        golInput input = new golInput();
        GameOfLife gameOfLife = new GameOfLife(input.n, input.seed, input.maxGenerations);
        gameOfLife.start();
    }

    /**
     * Static local class to getInput for user
     * Input is needed to initialize GameOfLife class
     */
    final static class golInput {
        int n;
        int seed;
        int maxGenerations;

        public golInput() {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter n: ");
            this.n = keyboard.nextInt();
            System.out.print("Enter seed: ");
            this.seed = keyboard.nextInt();
            System.out.print("Enter max generations: ");
            this.maxGenerations = keyboard.nextInt();
        }
    }
}
