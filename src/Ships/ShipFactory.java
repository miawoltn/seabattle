/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

/**
 * Handles Ship creation by using the static method Build which accepts the ShipType and name;
 * 
 * @author Muhammad Amin
 */
public class ShipFactory {
    
    
    /**
     * Creates and returns the appropriate Ship instance using the ShipType.
     * 
     * @param shipType @code{enum} which represents type of ship that needs to be created by the Build method.
     * @param name Name to be assigned to the created @see{Ship}.
     * @return Instance of the Ship class corresponding to the @see{ShipType} @code{Enum} passed to the Build function.
     *
     */
    public static Ship Build(ShipType shipType, String name) {
         if(null != shipType)
             switch (shipType) {
            case BattleShip:
                return new BattleShip(name);
            case Minesweeper:
                return new Minesweeper(name);
            case Mine:
                return new Mine(name);
            default:
                break;
        }
         
         return new BattleShip(name);
    }
}
