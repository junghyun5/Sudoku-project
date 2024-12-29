package com.finalprojects.finalpro;

import java.util.ArrayList;
import java.util.Random;


public class Sudoku {
    private final int size = 3;
    private int[][] Board;
    //data attributes

    public Sudoku(){
        this.setBoard(new int[this.size][this.size]);
        this.setBoard(sRandom());
    }


    public int[][] sRandom() { //generate 3x3 random values
        int size = 3;
        Random rand = new Random();
        int[][] numbers = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int randomNumber = rand.nextInt(9)+1;
                while (this.contains(randomNumber, numbers)) {
                    randomNumber = rand.nextInt(9)+1;
                }
                numbers[i][j] = randomNumber;
            }
        }
        return numbers;
    }

    public boolean contains(int num, int[][] array) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (array[i][j] == num) {
                    found = true;
                }
            }
        }
        return found;
    }

//    public static ArrayList<Integer> sudokuRandom(){
//        Random rand = new Random();
//        ArrayList<Integer> numbers = new ArrayList<Integer>();
//        // ArrayList<Integer> is one of the list data structures provided by Java,
//        //and it is a dynamically resizable array.
//        while(numbers.size() < 9){
//            int randNum = rand.nextInt(9)+1; //1~9
//            if(!numbers.contains(randNum)){
//                numbers.add(randNum);
//            }
//        }
//        return numbers;
//    }



    //helpers
    public static int[][] solution(int[][] array1, int[][] array2) { //matrix multiplication
        int[][] matrix = new int[array1.length][array2[0].length];

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2[0].length; j++) {
                int temp = 0;
                for (int k = 0; k < array2.length; k++) {
                    temp += array1[i][k] * array2[k][j];
                }
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }


    //getters

    public int[][] getBoard() {
        return this.Board;
    }

    //setters
    public void setBoard(int[][] board) {
        this.Board = board;
    }




    //toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<size;i++){
            for(int j = 0; j<size; j++) {
                sb.append(this.Board[i][j]);
                if (j < size - 1) {
                    sb.append(" ");
                }


            }
            sb.append("\n");
        }
        return sb.toString();
    }

}

