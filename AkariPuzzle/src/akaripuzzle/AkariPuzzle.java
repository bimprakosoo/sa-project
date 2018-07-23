/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akaripuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Alola
 */
public class AkariPuzzle {

//    public static char[][] board = {
//        {'E', 'E', '1', 'E', 'E', 'B', 'E'},
//        {'3', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'B'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'B', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', '3'},
//        {'E', '2', 'E', 'E', 'B', 'E', 'E'},};
//    public static char[][] board = {
//        {'E', 'E', 'E', 'E', 'E', 'B', 'E'},
//        {'3', 'E', 'E', 'E', 'B', 'E', 'E'},
//        {'E', '4', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', '3', 'E'},
//        {'E', 'E', 'B', 'E', 'E', 'E', '3'},
//        {'E', 'B', 'E', 'E', 'E', 'E', 'E'},};
    public static char[][] board = {
        {'E', 'E', 'E', 'B', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', '4', 'E', 'E', 'E'},
        {'B', 'E', '4', 'E', '2', 'E', '1'},
        {'E', 'E', 'E', '3', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', '1', 'E', 'E', 'E'},};
//    public static char[][] board = {
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'B', 'E', '3', 'E', 'E'},
//        {'E', '2', 'E', 'E', 'E', '2', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'B', 'E', 'E', 'E', '1', 'E'},
//        {'E', 'E', '4', 'E', '2', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},};

    public static ArrayList<int[]> blackCell = new ArrayList<int[]>();
    public static ArrayList<int[]> blackCellSolved = new ArrayList<int[]>();
    public static ArrayList<int[]> unsolvedCell = new ArrayList<int[]>();
    public static ArrayList<String> steps = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static boolean isBlack(int[] cell) {
        if (board[cell[0]][cell[1]] == 'B' || board[cell[0]][cell[1]] == '1' || board[cell[0]][cell[1]] == '2' || board[cell[0]][cell[1]] == '3' || board[cell[0]][cell[1]] == '4') {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(int[] cell) {
        if (board[cell[0]][cell[1]] == 'E') {
            return true;
        }
        return false;
    }

    public static void getBlackCell() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isBlack(new int[]{i, j})) {
                    if (board[i][j] == 'B') {
                        blackCell.add(new int[]{i, j, -1});
                    } else {
                        blackCell.add(new int[]{i, j, Character.getNumericValue(board[i][j])});
                    }
                }
            }
        }
    }

    public static void getUnsolvedCell() {
        unsolvedCell = new ArrayList<int[]>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'E') {
                    unsolvedCell.add(new int[]{i, j});
                }
            }
        }
    }

    public static ArrayList<int[]> getNeighbour(int[] cell, String mode) {
        ArrayList<int[]> neighbour = new ArrayList<int[]>();
        ArrayList<int[]> lampNeighbour = new ArrayList<int[]>();
        ArrayList<int[]> allNeighbour = new ArrayList<int[]>();
        // tetangga atas
        if ((cell[0] - 1) >= 0) {
            allNeighbour.add(new int[]{(cell[0] - 1), cell[1], 0});
            if ((board[(cell[0] - 1)][cell[1]] == 'E' || board[(cell[0] - 1)][cell[1]] == 'O')) {
                neighbour.add(new int[]{(cell[0] - 1), cell[1], 0});
            }
            if ((board[(cell[0] - 1)][cell[1]] == 'O')) {
                lampNeighbour.add(new int[]{(cell[0] - 1), cell[1], 0});
            }
        }
        // tetangga kanan
        if ((cell[1] + 1) <= 6) {
            allNeighbour.add(new int[]{(cell[0]), (cell[1] + 1), 1});
            if ((board[cell[0]][(cell[1] + 1)] == 'E' || board[cell[0]][(cell[1] + 1)] == 'O')) {
                neighbour.add(new int[]{(cell[0]), (cell[1] + 1), 1});
            }
            if ((board[cell[0]][(cell[1] + 1)] == 'O')) {
                lampNeighbour.add(new int[]{(cell[0]), (cell[1] + 1), 1});
            }
        }
        // tetangga bawah
        if ((cell[0] + 1) <= 6) {
            allNeighbour.add(new int[]{(cell[0] + 1), (cell[1]), 2});
            if ((board[(cell[0] + 1)][cell[1]] == 'E' || board[(cell[0] + 1)][cell[1]] == 'O')) {
                neighbour.add(new int[]{(cell[0] + 1), (cell[1]), 2});
            }
            if ((board[(cell[0] + 1)][cell[1]] == 'O')) {
                lampNeighbour.add(new int[]{(cell[0] + 1), (cell[1]), 2});
            }
        }
        // tetangga kiri
        if ((cell[1] - 1) >= 0) {
            allNeighbour.add(new int[]{(cell[0]), (cell[1] - 1), 3});
            if ((board[cell[0]][(cell[1] - 1)] == 'E' || board[cell[0]][(cell[1] - 1)] == 'O')) {
                neighbour.add(new int[]{(cell[0]), (cell[1] - 1), 3});
            }
            if ((board[cell[0]][(cell[1] - 1)] == 'O')) {
                lampNeighbour.add(new int[]{(cell[0]), (cell[1] - 1), 3});
            }
        }

        if (mode.equals("all")) {
            return allNeighbour;
        }
        if (mode.equals("lamp")) {
            return lampNeighbour;
        }
        return neighbour;
    }

    public static void main(String[] args) {

        // scanning blackcell
        getBlackCell();
        // sorting
        int max = 4;
        ArrayList<int[]> temp = new ArrayList<int[]>();
        while (max > -2) {
            for (int i = 0; i < blackCell.size(); i++) {
                if (blackCell.get(i)[2] == max) {
                    temp.add(new int[]{blackCell.get(i)[0], blackCell.get(i)[1], blackCell.get(i)[2]});
                }
            }
            max -= 1;
        }
        blackCell = temp;

        for (int[] b : blackCell){
            System.out.println("Black Cell : [ "+b[0]+", "+b[1]+", "+b[2]+" ]");
        }
    }
}
