/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

import Board.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class 
 * 
 * 
 * @author Muhammad Amin
 * @version 11/24/2017
 */
public class BattleShip implements Ship {

    int speed;
    Point location;
    String name;
    ShipType type;
    
    public BattleShip(String name) {
        this(ShipType.BattleShip, name);
    }
    
    private BattleShip( ShipType type, String name) {
       this.name = name;
       this.type = type;
       speed = type.getValue();
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSpeed() {
        return speed; // type.getValue();
    }    

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
       int totalMoves = horizontal + vertical;
       if(totalMoves > speed) throw new TooManyShipMovesException("Number of moves exceeded ship speed.");
    }
    
    @Override
    public void setLocation(Point location){
       this.location = location;
    }

    /*@Override
    public List<ShipType> getDestroyableShips() {
        return Arrays.asList(ShipType.Minesweeper);
    }

    @Override
    public List<ShipType> getPredatorShips() {
        return Arrays.asList(ShipType.Submarine);
    }*/

    @Override
    public ShipType getType() {
        return type;
    }
    
    
    
    @Override
    public Point getLocation(){
        return location;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Ship)) return false;
        
        if(obj == this) return true;
        
        Ship ship = (Ship) obj;
        return ship.getName().equals(this.getName())
                && ship.getSpeed() == this.getSpeed()
                && ship.getType() == this.getType();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.speed;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }
           
}
