package com.drinkler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class App {

    static int[][] kantengewichte = new int[6][6];

    public static void main(String[] args) {

        // * Nodes
        int A = 0;
        int B = 1;
        int C = 2;
        int D = 3;
        int E = 4;
        int F = 5;

        try {
            readFile("edges");
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        printMatrix();
    }

    /*
     * Reads the file and saves all numbers in an two dimensional array
     */
    public static void readFile(String filename) throws NumberFormatException, IOException {
        File file = new File("res\\" + filename + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int i = 0;
        String line;

        while ((line = br.readLine()) != null) {
            String[] arrOfStr = line.split(" ");

            int j = 0;

            for (String number : arrOfStr) {
                kantengewichte[i][j] = Integer.parseInt(number);
                j++;
            }
            i++;
        }
    }

    /*
     * Prints the matrix onto the terminal in an easy seeable way
     */
    public static void printMatrix() {
        String line = "";

        for (int i = 0; i < kantengewichte.length; i++) {
            for (int j = 0; j < kantengewichte[i].length; j++) {
                line += kantengewichte[i][j] + " ";
            }
            System.out.println(line);
            line = "";
        }
    }
}
