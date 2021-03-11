import java.util.HashMap;

public class Board {

    private String[][] board;
    private HashMap<Integer, int[]> boardMap = new HashMap<>();

    private final int MAX_ROWS_INDEX = 2;
    private final int MAX_COLS_INDEX = 2;
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

    public Board(){
        this.board = new String[3][3];
        int index = 0;
        for(int i = 0; i <= MAX_ROWS_INDEX; i++){
            for(int j = 0; j <= MAX_COLS_INDEX; j++){
                int[] cord = new int[2];
                cord[0] = i;
                cord[1] = j;
                this.boardMap.put(index, cord);
                board[i][j] = Integer.toString(index);
                index++;
            }
        }
    }

    public void showString(){
        for(int i = 0; i <= 2; i++){
            String gameRow = "";
            for(int j = 0; j <= 2; j++){
                gameRow += this.board[i][j] + " ";
            }
            System.out.println(gameRow);
        }
    }

    public Boolean updateBoard(int index, String turn){
        if(validateMove(index)) {
            int[] coordinates = this.boardMap.get(index);
            if (board[coordinates[0]][coordinates[1]] != "X" &&
                    board[coordinates[0]][coordinates[1]] != "O") {
                board[coordinates[0]][coordinates[1]] = turn;
                return true;
            }
        }
        return false;
    }
    public boolean validateWin(String turn){
        for(int[] win : this.possibleWins){
            int winCount = 0;
            for(int pos : win){
                int[] coordinates = boardMap.get(pos);
                if(board[coordinates[0]][coordinates[1]] == turn){
                    winCount++;
                    if(winCount == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean validateMove(int pos){
        int[] coordinates = this.boardMap.get(pos);
        return board[coordinates[0]][coordinates[1]].equals(String.valueOf(pos));
    }
    public String getPosValue(int pos){
        int[] coordinates = boardMap.get(pos);
        return board[coordinates[0]][coordinates[1]];
    }
    public int[][] getPossibleWins(){
        return this.possibleWins;
    }
}
