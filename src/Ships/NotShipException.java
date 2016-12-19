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
public class NotShipException extends Exception {
    
    public NotShipException(String ship_not_found) {
        super(ship_not_found);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
