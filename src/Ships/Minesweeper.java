/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

import Board.Point;

/**
 *
 * @author Muhammad Amin
 */
public class Minesweeper implements Ship {
    
    final static int speed = 3;
    Point location;
    String name;
    
    public Minesweeper(String name) {
        
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
    
    
}
