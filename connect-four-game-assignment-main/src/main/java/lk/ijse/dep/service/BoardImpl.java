package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private Piece pieces[][];
    private BoardUI boardUI;


    public BoardImpl(BoardUI boardUI){
      this.boardUI=boardUI;

      pieces=new Piece[NUM_OF_COLS][NUM_OF_ROWS];
      for (int i=0;i<NUM_OF_COLS;i++){
            for (int j=0;j<NUM_OF_ROWS;j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }
   @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        //System.out.println("findNextAvailableSpot method");
        for (int i=0;i<pieces[col].length;i++){
            if (pieces[col][i]==(Piece.EMPTY))
                return i;
        }
        return -1;
    }

    @Override
    public boolean isLegalMoves(int col) {
        for (int i=0;i<NUM_OF_ROWS;i++){
            if (pieces[col][i]==(Piece.EMPTY)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existLegalMoves(int col) {
        return false;
    }

    @Override
    public boolean existLegalMoves() {
        for(int i=0; i<NUM_OF_COLS; i++){
            for(int j=0; j<NUM_OF_ROWS; j++){
                if(pieces[i][j] == Piece.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)]=move;

    }
    @Override
    public Winner findWinner() {
        for (int i=0;i<NUM_OF_COLS;i++){
            int green=0;
            int blue=0;
            int empty=0;
            for (int j=0;j<NUM_OF_ROWS;j++){
                if (pieces[i][j]==Piece.GREEN){
                    green +=1;
                    blue =0;
                    empty =0;
                }
                if (pieces[i][j]==Piece.BLUE){
                    green =0;
                    blue +=1;
                    empty =0;
                }
                if (pieces[i][j]==Piece.EMPTY){
                    green =0;
                    blue =0;
                    empty +=1;
                }
                if (blue==4 || green==4){
                    Piece winnerPiece=blue == 4 ? Piece.BLUE : Piece.GREEN;
                    return new Winner(winnerPiece, i, j, i - 3, j);
                }
            }
        }
        for (int i=0;i<NUM_OF_ROWS;i++){
            int green=0;
            int blue=0;
            int empty=0;
            for (int j=0;j<NUM_OF_COLS;j++){
                if (pieces[j][i]==Piece.GREEN){
                    green +=1;
                    blue =0;
                    empty =0;
                }
                if (pieces[j][i]==Piece.BLUE){
                    green =0;
                    blue +=1;
                    empty =0;
                }
                if (pieces[j][i]==Piece.EMPTY){
                    green =0;
                    blue =0;
                    empty +=1;
                }
                if (blue==4 || green==4){
                    Piece winnerPiece=blue == 4 ? Piece.BLUE : Piece.GREEN;
                    return new Winner(winnerPiece, j, i, j - 3, i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}


