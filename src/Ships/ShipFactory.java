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
public class ShipFactory {
    
    public static Ship Build(ShipType shipType, String name) {
         if(null != shipType)
             switch (shipType) {
            case BattleShip:
                return new BattleShip(name,shipType);
            case Minesweeper:
                return new Minesweeper(name);
            case Mine:
                return new Mine(name);
            default:
                break;
        }
         
         return new BattleShip(name, shipType);
    }
}
