/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;
 
import java.util.List;

/**
 *
 * @author Muhammad Amin
 */
public abstract class ShipDecorator implements Ship { 
    
    public abstract List<ShipType> getDestroyableShips(); 
    public abstract List<ShipType> getPredatorShips();
}
