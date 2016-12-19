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
public interface Ship {
    public String getName();
    public int getSpeed();
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException; 
    public Point getLocation();
    public void setLocation(Point p);
    
}
