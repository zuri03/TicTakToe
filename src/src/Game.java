import java.util.Random;
import java.util.Scanner;
import GUI.GameBoard;


public class Game {

    private Board board;
    private String turn;
    private Opponent opponent;

    public Game(){
        this.board = new Board();
        this.turn = "X";
        this.opponent = null;
    }

    public Boolean registerMove(String move){

        return board.updateBoard(Integer.parseInt(move), this.turn);
    }
    private Boolean checkWinner(){

        return board.validateWin(this.turn);
    }
    private void changeTurn(){
        if(this.turn == "X")
            this.turn = "O";
        else
            this.turn = "X";
    }

    public void setOpponent(){
        this.opponent = new Opponent(this.board);
    }

    public void opponentMove(){
        if(opponent != null){
            registerMove(String.valueOf(opponent.makeMove()));
        } else {
            Random rng = new Random();
            while(!registerMove(String.valueOf(rng.nextInt(9)))){continue;}
        }
    }
    public static void main(String[] args){
        //i = row, j = column board[i][j]
        GameBoard gameBoard = new GameBoard();
        Game tickTackToe = new Game();
        String userIn;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Which kind of opponent" + '\n' + "0 = no opponent"
                + '\n' + "1 = simple opponent" + '\n' + "2 = complex opponent:");
        userIn = scanner.nextLine();
        int userInt = Integer.valueOf(userIn);
        try{
            if(userInt > 2 || userInt < 0){
                System.out.println("You must enter a value between 0 and 2");
            } else if(userInt == 2) {
                tickTackToe.setOpponent();
            }
        } catch (Exception e){
            System.out.println("A numerical value must be entered");
        }

        tickTackToe.board.showString();
        while(true) {

            System.out.println("Enter move:");
            try {
                userIn = scanner.nextLine();//format: #,#
                tickTackToe.registerMove(userIn);
                tickTackToe.changeTurn();
            } catch (Exception e) {
                System.out.println("Exception Found");
                continue;
            }
            if(tickTackToe.checkWinner()){
                System.out.println("Winner!!!!!!!!");
                break;
            }

            System.out.println("Opponent Move");
            tickTackToe.opponentMove();
            tickTackToe.board.showString();
            tickTackToe.changeTurn();

            if(tickTackToe.checkWinner()){
                System.out.println("Winner!!!!!!!!");
                break;
            }
        }
        System.out.println("Game Over");

    }
}
