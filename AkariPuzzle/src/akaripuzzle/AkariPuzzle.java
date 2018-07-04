/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akaripuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author ALOLA
 */
public class AkariPuzzle {

    public static char[][] board = {
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'B', 'E'},
//        {'E', 'E', 'E', '3', 'E', 'E', 'E'},
//        {'E', 'E', 'B', 'B', '2', 'E', 'E'},
//        {'E', 'E', 'E', '3', 'E', 'E', 'E'},
//        {'E', '2', 'E', 'E', 'E', 'B', 'E'},
//        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', '1', 'E', 'E', 'B', 'E'},
        {'3', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E', 'E', 'B'},
        {'E', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'B', 'E', 'E', 'E', 'E', 'E', 'E'},
        {'E', 'E', 'E', 'E', 'E', 'E', '3'},
        {'E', '2', 'E', 'E', 'B', 'E', 'E'},
    };

    /**
     * @param args the command line arguments
     */
    public static ArrayList<int[]> getTetangga(int[] cell) {
        ArrayList<int[]> tetangga = new ArrayList<int[]>();
        // tetangga atas
        if ((cell[0] - 1) >= 0 && board[(cell[0] - 1)][cell[1]] == 'E') {
            tetangga.add(new int[]{(cell[0] - 1), cell[1], 0});
        }
        // tetangga kanan
        if ((cell[1] + 1) <= 6 && board[cell[0]][(cell[1] + 1)] == 'E') {
            tetangga.add(new int[]{(cell[0]), (cell[1] + 1), 1});
        }
        // tetangga bawah
        if ((cell[0] + 1) <= 6 && board[(cell[0] + 1)][cell[1]] == 'E') {
            tetangga.add(new int[]{(cell[0] + 1), (cell[1]), 2});
        }
        // tetangga kiri
        if ((cell[1] - 1) >= 0 && board[cell[0]][(cell[1] - 1)] == 'E') {
            tetangga.add(new int[]{(cell[0]), (cell[1] - 1), 3});
        }

        return tetangga;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<int[]> blackCell = new ArrayList<int[]>();
        ArrayList<int[]> blackCellSolved = new ArrayList<int[]>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'B' || board[i][j] == '0' || board[i][j] == '1' || board[i][j] == '2' || board[i][j] == '3' || board[i][j] == '4') {
                    if (board[i][j] == 'B') {
                        blackCell.add(new int[]{i, j, -1});
                    } else {
                        blackCell.add(new int[]{i, j, Character.getNumericValue(board[i][j])});
                    }
                }
            }
        }

//        System.out.println("Before : ");
//        for (int i = 0; i < blackCell.size(); i++) {
//            System.out.println("Blackcell : " + (i + 1) + " [ " + blackCell.get(i)[0] + ", " + blackCell.get(i)[1] + ", " + blackCell.get(i)[2] + " ]");
//        }
        int max = 4;
        ArrayList<int[]> temp = new ArrayList<int[]>();
        while (max > -2) {
            for (int i = 0; i < blackCell.size(); i++) {
                if (blackCell.get(i)[2] == max) {
//                    blackCell.remove(i);
                    temp.add(new int[]{blackCell.get(i)[0], blackCell.get(i)[1], blackCell.get(i)[2]});
                }
            }
            max -= 1;
        }
        blackCell = temp;

        System.out.println("\nAfter : ");
        for (int i = 0; i < blackCell.size(); i++) {
            System.out.println("Blackcell : " + (i + 1) + " [ " + blackCell.get(i)[0] + ", " + blackCell.get(i)[1] + ", " + blackCell.get(i)[2] + " ]");
            ArrayList<int[]> ttg = getTetangga(blackCell.get(i));
            for (int j = 0; j < ttg.size(); j++) {
                System.out.println("Tetangga : " + (j + 1) + " [ " + ttg.get(j)[0] + ", " + ttg.get(j)[1] + ", " + ttg.get(j)[2] + " ]");
            }
            System.out.println("");
        }

    }

}
