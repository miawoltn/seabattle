/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

import Board.Point;  
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MuhammadAmin
 */
public class Destroyer extends ShipDecorator{

    private final static int SPEED = 6;
    private Point location;
    private final String name;
    
    public Destroyer(String name) {
        this.name = name;
    }
    
    @Override
    public List<ShipType> getDestroyableShips() {
        return Arrays.asList(ShipType.Minesweeper);
    }

    @Override
    public List<ShipType> getPredatorShips() {
        return Arrays.asList(ShipType.BattleShip);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
        int totalMoves = horizontal + vertical;
       if(totalMoves > SPEED) throw new TooManyShipMovesException("Number of moves exceeded ship speed.");
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
        return ShipType.Destroyer;
    }
    
}
