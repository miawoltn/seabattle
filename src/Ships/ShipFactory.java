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
         if(shipType == ShipType.BattleShip)
             return new BattleShip(name);
         
         else if(shipType == ShipType.Mine)
             return new Mine();
         
         return new BattleShip(name);
    }
}
