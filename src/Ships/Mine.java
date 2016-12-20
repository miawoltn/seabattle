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
public class Mine implements Ship {
    
    private Point location;
    private String name;
    
    @Override
    public String getName() {
        return "*";
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
         
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
    public String toString() {
        return name;
    }
    
    
}
