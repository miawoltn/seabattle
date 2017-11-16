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
public enum ShipType {    

    BattleShip(4),
    Destroyer(6),
    Minesweeper(3),
    Mine(0),
    Submarine(2);
    
    private final int value;
    ShipType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
