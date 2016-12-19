/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author Muhammad Amin
 */
public class OverBoardException extends Exception {
    
    public OverBoardException(String over_board_move) {
        super(over_board_move);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
