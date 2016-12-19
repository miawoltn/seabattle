/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

/**
 *
 * @author Muhammad Amin
 */
public class TooManyShipMovesException extends Exception {
    
    public TooManyShipMovesException(){
        super();
    }
    
    public TooManyShipMovesException(String message){
        super(message);
    }
}
