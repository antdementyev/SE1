package tictactoe;

public class Game {
    public StringBuffer board;
    private final int NOMOVE = -1;

    public Game(String s) {board = new StringBuffer(s);}

    public Game(StringBuffer s, int position, char player) {
        board = new StringBuffer();
        board.append(s);
        board.setCharAt(position, player);
    }

    public int move(char player){
        int defaultMove = NOMOVE;
        for (int move = 0; move < 9; move++) {
            if (isCellFree(move)) {
                Game game = play(move, player);
                if (game.winner() == player) {
                    defaultMove = move;
                    break;
                } else if (defaultMove == NOMOVE) {
                    defaultMove = move;
                }
            }
        }
        return defaultMove;
    }

    public Game play(int move, char player) {
        return new Game(this.board, move, player);
    }

    public char winner(){
        if (isWinner(0))
            return board.charAt(0);
        if (isWinner(3))
            return board.charAt(3);
        if (isWinner(6))
            return board.charAt(6);
        return '-';
    }

    private boolean isWinner(int pos){
        return !isCellFree(pos) 
                && board.charAt(pos) == board.charAt(pos + 1) 
                && board.charAt(pos + 1) == board.charAt(pos + 2);
    }

    private boolean isCellFree(int pos) {
        return board.charAt(pos) == '-';
    }
}
