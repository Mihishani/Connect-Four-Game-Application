package lk.ijse.dep.service;

public class AiPlayer extends Player{
    public AiPlayer(Board newBoard) {
        super(newBoard);
    }
    @Override
    public void movePiece(int col) {
        col=(int)(Math.random()*6);
        board.updateMove(col,Piece.GREEN);
       // board.getBoardUI().update(col,true);
        board.getBoardUI().update(col,false);

        Winner winner=this.board.findWinner();
        if (winner.getWinningPiece()!=Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);
        }else if (!board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}

