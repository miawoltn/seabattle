/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author MuhammadAmin
 */
public class PlayerNotSetException extends Exception {
    
     public PlayerNotSetException(String player_not_set) {
        super(player_not_set);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
