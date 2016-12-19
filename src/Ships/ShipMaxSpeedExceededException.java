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
public class ShipMaxSpeedExceededException extends Exception {
    
    public ShipMaxSpeedExceededException(String Ship_speed_exceeded) {
        super(Ship_speed_exceeded);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
