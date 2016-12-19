/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Ships.Mine;
import Ships.Minesweeper;
import Ships.ShipMaxSpeedExceededException;
import Ships.NotShipException;
import Ships.Ship;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Muhammad Amin
 */
public class Controller {   
    
    private final Board board;
    final Player player1, player2;
    final List<Ship> warShips;
    private Ship wShip;
    private int turn;
    final int playerOneHits;
    final int playerTwoHits;
    final int playerOneAttempts;
    final int playerTwoAttempts;
    private String outCome;
    private boolean canAttack;
        
    /**
     *
     * @param board
     * @param players
     */
    public Controller(Board board, Player [] players){
        this.board = board;
        this.player1 = players[0];
        this.player2 = players[1];
        warShips = new ArrayList<>();
        playerOneHits = playerTwoHits = 0;
        playerOneAttempts = playerTwoAttempts = turn = 0;
        wShip = null;
    }    
    
    /**
     * The startGame methods is used to start the game after the board, players and warships 
     * have been set;
     * 
     * The methods throws a ControllerNotSetException if the controller is not set.
     * 
     * @param controller
     * @throws ControllerNotSetException
     */
    public static void startGame(Controller controller) throws ControllerNotSetException{
        if(controller == null) throw new ControllerNotSetException("Controller is null.");     
        
         controller.begin();
    }
    
     private void begin() {   
       warShips.addAll(player1.WarShips);
       warShips.addAll(player2.WarShips);
       warShips.stream().forEach((ship) -> {
           Position(ship.getLocation(),ship);
        });
    } 
    
    /**
     *
     * @param loc
     * @return
     * @throws NotShipException
     */
    public Ship getShip(Point loc) throws NotShipException {
        Object s = this.board.getCell(loc.getX(), loc.getY());
        if(warShips.contains(s)) {
           switch(getTurn()) {
               case 0 :
                   if(!player1.WarShips.contains(s)) throw new NotShipException("Not a ship"); 
                   break;
               case 1: 
                   if(!player2.WarShips.contains(s)) throw new NotShipException("Not a ship"); 
                   break;                
           }
            return (Ship)s;
        }         

        throw new NotShipException("Not a ship");
       
    }
     
    /**
     * Moves the position of the specified Ship to the new location specified.
     * 
     * If the new specified location is outside the board range (WarGround) then
     * an OverBoardException is thrown and if the number of cells/moves the ship
     * has to do in order to reach the new location exceeds the ship's speed, a
     * ShipMaxSpeedExceededException in thrown.
     * 
     * @param ship
     * @param newLocation
     * @throws ShipMaxSpeedExceededException
     * @throws OverBoardException
     */
    public void moveShip(Ship ship, Point newLocation) throws ShipMaxSpeedExceededException, OverBoardException{          
         if(!withinBoard(newLocation)) throw new OverBoardException("Move is over board range");
         if(!validMove(ship,newLocation)) throw new ShipMaxSpeedExceededException("Ship speed not sufficient for the move");
         
       resolveLandingConflict(ship,newLocation);
       // ResolveConflict(ship,newLocation);
  }
    
     private void ResolveConflict(Ship ship, Point newLocation) {        
      Object current = board.getCell(newLocation.getX(), newLocation.getY());
      if(null != current) switch (current.toString()) {
            case "~":
                //ship.setLocation(newLocation);
                positionWarship(ship, newLocation);
                outCome = OutCome.safeLanding;
                break;
            case "Mine":
                if (!"Minesweeper".equals(ship.getClass().getSimpleName())) {
                   // warShips.remove(ship);
                    //Position(newLocation, "~");
                    removeWarShip(ship);
                    removeWarShip((Ship)current);
                    outCome = OutCome.mineLanding;
                }else {
                    //ship.setLocation(newLocation);
                    positionWarship(ship, newLocation);
                    outCome = OutCome.mineNeutralised;
                }   break;
            default:
                    wShip = (Ship)current;
                    removeWarShip(wShip); //warShips.remove(wShip);
                    removeWarShip(ship); //warShips.remove(ship);
                    outCome = OutCome.collision;      
                    
               
        }
    }
  
  private void resolveLandingConflict(Ship ship , Point newLocation) {
      Object current = board.getCell(newLocation.getX(), newLocation.getY());
      if(!isShip(current)) {
           positionWarship(ship, newLocation);
           outCome = OutCome.safeLanding;
           canAttack = true;
      }
      else {
          wShip = (Ship)current;
          if(wShip instanceof Mine) {
              if(ship instanceof Minesweeper) {
                  positionWarship(ship, newLocation);
                  outCome = OutCome.mineNeutralised;
                  canAttack = false;
              }
              else {             
                  removeWarShip(ship);
                  removeWarShip(wShip);
                  outCome = OutCome.mineLanding;
                  canAttack = false;
              }               
          }
          else {
              removeWarShip(wShip);
              removeWarShip(ship);
              outCome = OutCome.collision;      
              canAttack = false;
          }
      }
  }
  
  private void resolveWarshipsConflict(Ship ship) {
      if(canAttack) {
          
      }
  }
        
 
   
   private boolean validMove(Ship s, Point newLocation) {
       int row = Math.abs(newLocation.getX() - s.getLocation().getX());
       int col = Math.abs(newLocation.getY() - s.getLocation().getY());
       return s.getSpeed() >= (row+col);
    }
   
   private boolean withinBoard(Point location) {
       int row = location.getX();
       int col = location.getY();
        return (board.columns > col && col >= 0) && (0 <= row && row < board.rows);
    }
   
   private void removeWarShip(Ship warShip){
        Point loc = warShip.getLocation();
        warShips.remove(warShip);
        Position(loc,"~"); 
    }
    
    /**
     * Updates the position of the Warship and changes it's location on the 
     * board (WarGround).
     * 
     * The current position of the Warship is replaced with the default 
     * value of the for the board (WarGround) which is a tilde (~).
     * 
     * @param WarShip
     * @param newLocation
     */
    public void positionWarship(Ship WarShip, Point newLocation){
        Point currentLoc = WarShip.getLocation();
        WarShip.setLocation(newLocation);
        Position(newLocation,WarShip); 
        Position(currentLoc,"~");
    }
    
     /**
     * Puts the object in the specified location on the board (WarGround).
     *  
     * @param location
     * @param obj
     */
    private void Position(Point location, Object obj){
        int row = location.getX();
        int col = location.getY();
        board.setCell(row, col, obj);  
    }
    
    private boolean isShip(Object object) {
        try {
            Ship ship = (Ship)object;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    private Point[] getSurroundingShips(Ship currentShip){
        Point p = currentShip.getLocation();
        int row = p.getX();
        int col = p.getY();
        Point west = getLeftShip(row,col);
        Point northwest = getUpperLeftDiagonalShip(row,col);
        Point north = getInfrontShip(row, col);
        Point northeast = getUpperRightDiagonalShip(row,col);
        Point east = getRightShip(row,col);
        Point southeast = getLowerRightDiagonalShip(row,col);
        Point south = getBehindShip(row,col);
        Point southwest = getLowerLeftDiagonalShip(row,col);
       return new Point[]{
           west,northwest,north,northeast,east,southeast,south,southwest
       };
    }
    
    private Point getLeftShip(int row, int col){
        return new Point(row,--col);
    }    
            
    private Point getUpperLeftDiagonalShip(int row, int col){
        return new Point(--row,--col);
    }
    
    private Point getInfrontShip(int row, int col){
        return new Point(--row,col);
    }
    
     private Point getUpperRightDiagonalShip(int row, int col){
        return new Point(++row,--col);
    }

    private Point getRightShip(int row, int col){
        return new Point(row,++col);
    }
    
    private Point getLowerRightDiagonalShip(int row, int col){
        return new Point(++row,++col);
    }
    
    private Point getBehindShip(int row, int col){
        return new Point(++row,col);
    }
    
    private Point getLowerLeftDiagonalShip(int row, int col){
        return new Point(++row,--col);
    }  
    
    /**
     *
     * @return
     */
    public Object[][] getBoard(){
        return board.WarGround;     
   }       

    /**
     *
     * @return
     */
    public int getPlayerOneHits() {
        return playerOneHits;
    }
    
    /**
     *
     * @return
     */
    public int getPlayerTwoHits() {
        return playerTwoHits;
    }

    /**
     *
     * @return
     */
    public int getTurn(){
       return turn % 2;
    }
    
    /**
     *
     */
    public void setTurn(){
       ++turn;
    }

    /**
     *
     * @return
     */
    public String getOutCome() {
        return outCome;
    }

    
   
   
    
}
