import java.util.Scanner;
/**
 * Tic Tac Toe Game
 * -----------------
 * A simple command-line two-player Tic Tac Toe game in Java.
 *
 * Features:
 * 1. Two players take turns to place their symbol ('X' or 'O') on a 3x3 board.
 * 2. Validates moves to prevent placing a symbol on an occupied cell.
 * 3. Checks for a win after each move (rows, columns, diagonals).
 * 4. Detects draw when the board is full and no player has won.
 * 5. Allows multiple rounds; players can choose to play again.
 * 6. Provides a clear text-based interface for player input and board display.
 *
 * Classes:
 * - Player: Represents a player with a name and a symbol.
 * - Board: Represents the game board and provides methods to place moves, print the board,
 *          check for wins, and check if the board is full.
 * - Game: Handles the game loop, player turns, input validation, and replay logic.
 * - TicTacToe: Contains the main method to start the game.
 *
 * Usage:
 * 1. Run the program.
 * 2. Enter the names for Player 1 and Player 2.
 * 3. Players take turns entering row and column (0-2) to place their symbol.
 * 4. The game announces a winner or a draw at the end of each round.
 * 5. Players can choose to play again or exit.
 *
 */
class Player{
    String name;
    char symbol;
    Player(String name,char symbol){
        this.name=name;
        this.symbol=symbol;
    }
}

class Board{
    char[][] board=new char[3][3];
    Board(){
        reset();
    }
    void reset(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]=' ';
            }
        }
    }
    void boardprint(){
        System.out.println("-------------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++)
                System.out.print(board[i][j]+" | ");
            System.out.println("\n-------------");
        }
    }
    boolean isFull(){
        for(char[] row:board)
            for(char c:row)
                if(c==' ')
                    return false;
        return true;
    }
    boolean checkWin(char s){
        for(int i=0;i<3;i++)
            if((board[i][0]==s && board[i][1]==s && board[i][2]==s) ||
               (board[0][i]==s && board[1][i]==s && board[2][i]==s))
                return true;
        if((board[0][0]==s && board[1][1]==s && board[2][2]==s) ||
           (board[0][2]==s && board[1][1]==s && board[2][0]==s))
            return true;
        return false;
    }
    boolean placeMove(int row,int col,char s){
        if(row<0 || row>2 || col<0 || col>2 || board[row][col]!=' ')
            return false;
        board[row][col]=s;
        return true;
    }
}

class Game{
    Board board=new Board();
    Player p1,p2;
    Scanner sc=new Scanner(System.in);
    Game(Player p1,Player p2){
        this.p1=p1;
        this.p2=p2;
    }

    void start(){
        boolean playAgain;
        do{
            board.reset();
            board.boardprint();
            Player current=p1;
            while(true){
                System.out.println(current.name+"'s turn ("+current.symbol+")");
                int row=-1,col=-1;
                while(true){
                    System.out.print("Enter row and column (0 1 2): ");
                    if(sc.hasNextInt()) row=sc.nextInt(); else sc.next();
                    if(sc.hasNextInt()) col=sc.nextInt(); else sc.next();
                    if(board.placeMove(row,col,current.symbol)) break;
                    System.out.println("Invalid move, try again.");
                }
                board.boardprint();
                if(board.checkWin(current.symbol)){
                    System.out.println(current.name+" wins the round!");
                    break;
                }else if(board.isFull()){
                    System.out.println("It's a draw between "+p1.name+" and "+p2.name+"!");
                    break;
                }
                current=(current==p1)?p2:p1;
            }
            System.out.print("Play again? (y/n): ");
            playAgain=sc.next().equalsIgnoreCase("y");
        }while(playAgain);
        System.out.println("Game over. Thanks for playing!");
    }
}

public class TicTacToe{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("        TIC TAC TOE GAME");
        System.out.println("===============================");
        System.out.println("Rules:");
        System.out.println("1. Two players take turns (X and O).");
        System.out.println("2. Enter row and column like: 0 1");
        System.out.println("3. Get three in a row to win!\n");
        System.out.print("Enter Player 1 name: ");
        String name1=sc.nextLine();
        System.out.print("Enter Player 2 name: ");
        String name2=sc.nextLine();
        new Game(new Player(name1,'X'),new Player(name2,'O')).start();
        sc.close();
    }
}
