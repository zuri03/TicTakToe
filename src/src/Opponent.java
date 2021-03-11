import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Opponent {

    private Board board;
    private Integer plannedMove;
    private ArrayList<Integer> movesMade;
    private final int[][] possibleWins = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    public Opponent(Board board){
        this.board = board;
        this.plannedMove = null;
        this.movesMade = new ArrayList<>();
    }

    public int makeMove(){
        if(plannedMove == null) {
            if(!movesMade.isEmpty()) {
                for (int i = 0; i < possibleWins.length; i++) {
                    int winningMove = guaranteedWin(possibleWins[i]);
                    if(winningMove != -1) {
                        return winningMove;
                    }
                }
                for(int move : movesMade){
                    int[] returnMove = getPossibleWins(move);
                    this.plannedMove = returnMove[1];
                    this.movesMade.add(returnMove[0]);
                    return returnMove[0];
                }
            }
        }
        if(board.validateMove(plannedMove)) {
            this.movesMade.add(plannedMove);
            return plannedMove;
        }
        Random random = new Random();
        int randomValue;
        while(true){
            randomValue = random.nextInt(9);
            if(board.validateMove(randomValue)){
                this.movesMade.add(randomValue);
                return randomValue;
            }
        }
    }

    private int guaranteedWin(int[] possibleWin){
        int winMovesMade = 0;
        int returnVal = -1;
        for(int move : movesMade){
            for(int win : possibleWin){

                if(win == move){
                    winMovesMade++;
                } else { returnVal = win; }
            }
            if(winMovesMade == 2) return returnVal;
        }
        return returnVal;
    }

    private int[] getPossibleWins(int pos){
        int[] win = new int[3];
        for(int i = 0; i < possibleWins.length; i++){
            win = possibleWins[i];
            String[] values = new String[3];
            for(int j = 0; j < win.length; j++){
                values[j] = board.getPosValue(win[j]);
            }
            if(!Arrays.asList(values).contains("X")){
                int[] returnVals = new int[2];
                for(int k = 0; k < win.length;k++){
                    if(board.getPosValue(win[k]) != "O"){
                        returnVals[k] = win[k];
                    }
                }
            }
        }
        return win;
    }
}

