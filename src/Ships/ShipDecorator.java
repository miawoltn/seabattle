/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;
 
import Board.Point;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Muhammad Amin
 */
public abstract class ShipDecorator implements Ship { 
    protected final Ship decoratedShip;
    
    public ShipDecorator(Ship ship) {
        decoratedShip = ship;
    }

    @Override
    public String getName() {
        return decoratedShip.getName();
    }

    @Override
    public int getSpeed() {
        return decoratedShip.getSpeed();
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
        decoratedShip.setMove(horizontal, vertical);
    }

    @Override
    public Point getLocation() {
        return decoratedShip.getLocation();
    }

    @Override
    public void setLocation(Point p) {
        decoratedShip.setLocation(p);
    }

    @Override
    public ShipType getType() {
        return decoratedShip.getType();
    }

    @Override
    public String toString() {
        return decoratedShip.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return decoratedShip.equals(obj);
    }

    @Override
    public int hashCode() {
       return decoratedShip.hashCode();
    }
    
    
        
    public abstract List<ShipType> getDestroyableShips(); 
    public abstract List<ShipType> getPredatorShips();
    public abstract String getOwner(); 
}
