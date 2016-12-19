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
public class ShipNotInFleetException extends Exception {
    
    public ShipNotInFleetException(String not_in_fleet) {
        super(not_in_fleet);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
