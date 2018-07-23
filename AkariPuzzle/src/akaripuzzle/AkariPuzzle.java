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

    public static void setRayRow(int[] cell) {
        int row = cell[0];
        int col = cell[1];
        // left row
        for (int left = (col - 1); left >= 0; left--) {
            if (board[row][left] == 'E' || board[row][left] == 'R') {
                board[row][left] = 'R';
            } else {
                break;
            }
        }
        // right row
        for (int right = (col + 1); right <= 6; right++) {
            if (board[row][right] == 'E' || board[row][right] == 'R') {
                board[row][right] = 'R';
            } else {
                break;
            }
        }
    }

    public static void setRayColumn(int[] cell) {
        int row = cell[0];
        int col = cell[1];
        // up column
        for (int up = (row - 1); up >= 0; up--) {
            if (board[up][col] == 'E' || board[up][col] == 'R') {
                board[up][col] = 'R';
            } else {
                break;
            }
        }
        // down column
        for (int down = (row + 1); down <= 6; down++) {
            if (board[down][col] == 'E' || board[down][col] == 'R') {
                board[down][col] = 'R';
            } else {
                break;
            }
        }
    }

    public static void setLamp(ArrayList<int[]> nbr) {
        for (int index = 0; index < nbr.size(); index++) {
            if (board[nbr.get(index)[0]][nbr.get(index)[1]] == 'E') {
                board[nbr.get(index)[0]][nbr.get(index)[1]] = 'O';
                setRayRow(nbr.get(index));
                setRayColumn(nbr.get(index));
                steps.add("Put Lamp in : [ " + nbr.get(index)[0] + ", " + nbr.get(index)[1] + " ]");
            }
        }
    }

    public static void Solve(int[] cell, int idx) {
        ArrayList<int[]> nbr = getNeighbour(cell, "default");
        ArrayList<int[]> lNbr = getNeighbour(cell, "lamp");
        int neighbour = nbr.size() - lNbr.size();
        System.out.println("Blackcell " + idx + " :  [ " + cell[0] + ", " + cell[1] + ", " + cell[2] + " ]");
        if (cell[2] == nbr.size() || (lNbr.size() == cell[2]) || cell[2] == -1) {
            // || (cell[2] == -1 && nbr.size() > 0)
            if ((cell[2] == nbr.size() && lNbr.size() < cell[2])) {
                setLamp(nbr);
            }
            // move blackCell to solved
            blackCellSolved.add(blackCell.get(idx));
            System.out.println("Solved");
        } else {
            ArrayList<int[]> temp = new ArrayList<int[]>();
            // searching for candidate cell
            for (int[] n : nbr) {
                int c = 0;
                for (int[] l : lNbr) {
                    if (Arrays.equals(n, l)) {
                        c++;
                    }
                }
                if (c == 0) {
                    ArrayList<int[]> tNbr = getNeighbour(n, "all");
                    boolean candidate = true;
                    for (int[] tn : tNbr) {
                        int blackCons = 0;
                        if (isBlack(tn)) {
                            ArrayList<int[]> tLamp = getNeighbour(tn, "lamp");
                            if (board[tn[0]][tn[1]] == 'B') {
                                blackCons = -1;
                            } else {
                                blackCons = Character.getNumericValue(board[tn[0]][tn[1]]);
                            }

                            if ((tLamp.size() >= blackCons)) {
                                candidate = false;
                            }
                        }
                    }
                    if (candidate) {
                        temp.add(n);
                        setLamp(temp);
                        blackCellSolved.add(blackCell.get(idx));
                        System.out.println("Solved");
                    }
                }
            }
        }
        System.out.println("");
    }

    public static void solving() {
        ArrayList<int[]> temp = new ArrayList<int[]>();
        temp = new ArrayList<int[]>();
        // solving the blackcell first
        System.out.println("\nSolving : \n");
        while (blackCell.size() > 0) {
            for (int i = 0; i < blackCell.size(); i++) {
                Solve(blackCell.get(i), i);
            }

            // reset blackCell list
            temp = new ArrayList<int[]>();
            for (int[] a : blackCell) {
                int check = 0;
                for (int[] b : blackCellSolved) {
                    if (a == b) {
                        check++;
                    }
                }
                if (check == 0) {
                    temp.add(a);
                }
            }
            blackCell = temp;
        }

        System.out.println("");
        System.out.println("List Solved: ");
        for (int j = 0; j < blackCellSolved.size(); j++) {
            System.out.println("BlackCell " + (j + 1) + " : [ " + blackCellSolved.get(j)[0] + ", " + blackCellSolved.get(j)[1] + ", " + blackCellSolved.get(j)[2] + " ]");
        }

        System.out.println("");
        System.out.println("List Unsolved: ");
        if (blackCell.size() > 0) {
            for (int j = 0; j < blackCell.size(); j++) {
                System.out.println("BlackCell " + (j + 1) + " : [ " + blackCell.get(j)[0] + ", " + blackCell.get(j)[1] + ", " + blackCell.get(j)[2] + " ]");
            }
        }

        // print result
        System.out.println("\nResult : ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
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
        
        for (int[] b : blackCell) {
            System.out.println("Black Cell : [ " + b[0] + ", " + b[1] + ", " + b[2] + " ]");
        }
         Display pl = new Display();
         pl.setVisible(true);
    }
}
