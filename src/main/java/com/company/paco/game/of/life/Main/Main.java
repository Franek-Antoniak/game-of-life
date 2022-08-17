package com.company.paco.game.of.life.Main;

import com.company.paco.game.of.life.GameOfLife;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameInput gameInput = new GameInput.Builder().setN()
                .setMaxGenerations()
                .build();
        GameOfLife gameOfLife = new GameOfLife(gameInput.getN(), gameInput.getMaxGenerations());
        gameOfLife.start();
    }

    /**
     * Static local class to getInput for user
     * Input is needed to initialize GameOfLife class
     */

     static class GameInput {
        private int n;
        private int maxGenerations;
        private int seed;

        private GameInput(Builder builder) {
            if (builder.n != null)
                this.n = builder.n;
            if (builder.maxGenerations != null)
                this.maxGenerations = builder.maxGenerations;
            if (builder.seed != null)
                this.seed = builder.seed;
        }

        public int getN() {
            return n;
        }

        public int getMaxGenerations() {
            return maxGenerations;
        }

        public int getSeed() {
            return seed;
        }

        static class Builder {
            private final Scanner keyboard = new Scanner(System.in);
            private Integer n = null;
            private Integer maxGenerations = null;
            private Integer seed = null;

            Builder setN() {
                System.out.print("Enter n: ");
                this.n = keyboard.nextInt();
                return this;
            }

            Builder setMaxGenerations() {
                System.out.print("Enter max generations: ");
                this.maxGenerations = keyboard.nextInt();
                return this;
            }

            Builder setSeed() {
                System.out.print("Enter seed: ");
                this.seed = keyboard.nextInt();
                return this;
            }

            GameInput build() {
                return new GameInput(this);
            }
        }
    }
}
