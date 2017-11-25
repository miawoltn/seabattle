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
 *
 * @author Muhammad Amin
 */
public class Minesweeper implements Ship {
    
    private final int speed;
    private Point location;
    private final String name;
    private final ShipType type;
    
    public Minesweeper(String name) {
        this(ShipType.Minesweeper, name);
    }
    
    private Minesweeper(ShipType type, String name) {
        this.type = type;
        this.name = name;
        this.speed = type.getValue();
    }

    @Override
    public String getName() {
       return name;
    }

    @Override
    public int getSpeed() {
        return speed; 
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
        int totalMoves = horizontal + vertical;
       if(totalMoves > speed) throw new TooManyShipMovesException("Number of moves exceeded ship speed.");
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point p) {
        this.location = p;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public ShipType getType() {
      return ShipType.Minesweeper;
    }

    /*@Override
    public List<ShipType> getDestroyableShips() {
        return new ArrayList<>();
    }

    @Override
    public List<ShipType> getPredatorShips() {
       return Arrays.asList(ShipType.BattleShip);
    }*/
    
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
        hash = 83 * hash + this.speed;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.type);
        return hash;
    }
}
