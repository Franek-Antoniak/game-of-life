package com.company.paco.game.of.life.Main;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the keyboard
        Scanner keyboard = new Scanner(System.in);
        // Get n and seed from the user
        System.out.print("Enter n: ");
        int n = keyboard.nextInt();
        System.out.print("Enter seed: ");
        int seed = keyboard.nextInt();
        // Set seed for random number generator
        Random rand = new Random(seed);
        // Create array of booleans named map
        int[][] map = new int[n][n];
        // Initialize map with random values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = rand.nextBoolean() ? 1 : 0;
            }
        }
        // Print map
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] == 1 ? "O" : " ");
            }
            System.out.println();
        }
    }
}
