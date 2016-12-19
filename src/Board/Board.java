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
public class Board {
    final int rows, columns;
    Object[][] WarGround;
    public Board(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        WarGround = new Object[rows][columns];     
        InitializeWarGround();
    }    
    
    public int getRows(){
        return rows;
    }
    
    public int getColumns(){
        return columns;
    }
   
    public Object getCell(int row, int col) {
        return WarGround[row][col];
    }
    
    public void setCell(int row, int col, Object value) {
        try {
            WarGround[row][col] = value;
        } catch(Exception e) {
            
        }
        
    }
    
    private void InitializeWarGround(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                WarGround[i][j] = ("~");
            }
        }
    }
    
   
}
