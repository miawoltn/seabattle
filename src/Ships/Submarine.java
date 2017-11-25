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
 * @author MuhammadAmin
 */
public class Submarine implements Ship{
    
    private final int speed;
    private Point location;
    private final String name;
    private final ShipType type;
    
    public Submarine(String name) {
        this(ShipType.Submarine, name);
    }
    
    private Submarine(ShipType type, String name) {
        this.type = type;
        this.name = name;
        this.speed = type.getValue();
    }

    /*@Override
    public List<ShipType> getDestroyableShips() {
        return Arrays.asList(ShipType.BattleShip,ShipType.Minesweeper);
    }

    @Override
    public List<ShipType> getPredatorShips() {
        return Arrays.asList(ShipType.Destroyer);
    }*/

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
    public ShipType getType() {
        return type;
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
        hash = 43 * hash + this.speed;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.type);
        return hash;
    }
}
