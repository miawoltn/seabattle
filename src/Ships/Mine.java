/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

import Board.Point;
import java.util.Objects;

/**
 *
 * @author Muhammad Amin
 */
public class Mine implements Ship {
    
    private final String name;
    private final ShipType type;
    private Point location;
    private final int speed;
    
    public Mine(String name) {
        this(ShipType.Mine, name);
    }
    
    private Mine(ShipType type, String name) {
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
        return 0;
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
         // mines are immovable
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point p) {
        if(location == null) {
            location = p;
        }
    }

    @Override
    public ShipType getType() {
        return ShipType.Mine;
    }
    
    @Override
    public String toString() {
        return "*";
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
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + this.speed;
        return hash;
    }
}
