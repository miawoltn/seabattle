/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

/**
 *
 * @author Muhammad Amin
 */
public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y){
        this.x = --x;
        this.y = --y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", (x+1),(y+1));// "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
