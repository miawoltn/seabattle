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

/**
 *
 * @author Muhammad Amin
 */
public class BattleShip extends ShipDecorator{

    int speed;
    Point location;
    String name;
    ShipType type;
    
    public BattleShip(String name, ShipType type) {
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

    @Override
    public List<ShipType> getDestroyableShips() {
        return Arrays.asList(ShipType.Minesweeper);
    }

    @Override
    public List<ShipType> getPredatorShips() {
        return new ArrayList<>(); // Arrays.asList(ShipType.BattleShip);
    }

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
    
    
        
}
