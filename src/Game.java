import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    static String[] board;
    static String turn;

    static String checkWinner(){
        for(int i=0; i<8; i++) {
            String line = switch (i) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> "";
            };
            if(line.equals("XXX")){
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }
        for(int i=0; i<9; i++) {
            if(board[i].equals(String.valueOf(i+1))) return null;
        }
        return "Draw";
    }

    static void printBoard(){
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0]+ " | "  + board[1]+ " | "  + board[2]+ " | ");
        System.out.println("|---|---|---|");
        System.out.println("| " + board[3]+ " | "  + board[4]+ " | "  + board[5]+ " | ");
        System.out.println("|---|---|---|");
        System.out.println("| " + board[6]+ " | "  + board[7]+ " | "  + board[8]+ " | ");
        System.out.println("|---|---|---|");

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        turn = "X";
        String winner = null;

        board = new String[9];
        for(int i=0; i<9; i++){
            board[i] = String.valueOf(i+1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();

        System.out.print("X will play first.");
        while(winner == null) {
            int input;
            try{
                System.out.println("Enter a slot number to place " + turn + " in: ");
                input = sc.nextInt();
                if(input<0 || input>9){
                    System.out.println("Invalid input; re-enter slot number: ");
                    continue;
                }
                if(board[input-1].equals(String.valueOf(input))) {
                    board[input-1] = turn;
                    printBoard();
                    turn = turn.equals("X") ? "O" : "X";
                    winner = checkWinner();
                }
                else{
                    System.out.println("Slot already taken. Re-enter slot number: ");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number: ");
            }
        }

        if(winner.equals("Draw")){
            System.out.println("It's a DRAW!");
        }
        else {
            System.out.println(winner + " is the winner!");
        }
        sc.close();
    }
}
