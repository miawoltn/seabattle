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
public class ControllerNotSetException extends Exception {
    
    public ControllerNotSetException(String controller_is_empty) {
        super(controller_is_empty);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
