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
    public List<Ship> enemyShips;
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
        enemyShips = new ArrayList<>();
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
        if(this.warShips.contains(s)) {
              return (Ship)s;
           }
            //return (Ship)s;         

        throw new NotShipException("Not a ship");
       
    }
    
    public boolean isInFleet(Ship ship) throws NotShipException { //determines if the selected ship is the currents player's
           switch(this.getTurn()) {
               case 0:
                   if(player1.WarShips.contains(ship)) //throw new NotShipException("Not a ship"); 
                       return true;
                   break;
               case 1: 
                   if(player2.WarShips.contains(ship)) //throw new NotShipException("Not a ship"); 
                       return true;    
                   break;
           }        
       return false;
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
       
         resolveConflict(ship,newLocation);
  }
    
     private void resolveConflict(Ship ship, Point newLocation) {      
         resolveLandingConflict(ship, newLocation);
         resolveWarshipsConflict(ship);
     /* Object current = board.getCell(newLocation.getX(), newLocation.getY());
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
                    
               
        }*/
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
                  canAttack = true;
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
      enemyShips.clear();
      if(canAttack) {
         enemyShips = this.getSurroundingShips(ship); // enemyShips = filterEnemyWarship(enemyShips);
         
        /*  switch(ship.getClass().getSimpleName()) {
                 case "Destroyer":
                     for(Ship s : enemyShips) {
                         switch(s.getClass().getSimpleName()) {
                             case "Submarine":
                             case "Minesweeper":
                             case "Destroyer":
                                 this.removeWarShip(s);
                                 break;
                         }
                     }
                     break;
                 case "BattleShip":
                     for(Ship s : enemyShips) {
                         switch(s.getClass().getSimpleName()) {
                             case "Destroyer":
                             case "Minesweeper":
                             case "BattleShip":
                                 this.removeWarShip(s);
                                 break;
                         }
                     }
                     break;
                case "Submarine":
                    for(Ship s : enemyShips) {
                         switch(s.getClass().getSimpleName()) {
                             case "BattleShip":
                             case "Minesweeper":
                             case "Submarine":
                                 this.removeWarShip(s);
                                 break;
                         }
                     }
                     break;
                 case "Minesweeper":
                      for(Ship s : enemyShips) {
                         switch(s.getClass().getSimpleName()) {
                             case "Minesweeper":
                                 this.removeWarShip(s);
                                 break;
                         }
                     }
                     break;                
                 default:
             }
         //enemyShips.stream().forEach((s) -> this.removeWarShip(s));
         //enemyShips.clear();*/
      }
  }
        
  private List<Ship> filterEnemyWarship(List<Ship> Ships) {      
      List<Ship> _ship = new ArrayList<>();
       if(getTurn() == 0) {
            Ships.stream().forEach((eShip) -> {
              if(player1.WarShips.contains(eShip))
                      _ship.remove(eShip);
             });                 
        }
        else {
           Ships.stream().forEach((eShip) -> {
             if(player2.WarShips.contains(eShip))
                      _ship.remove(eShip);
          });      
        }           
          return _ship;
  }
   
  //checking if the ship's speed can reach the target position
   private boolean validMove(Ship s, Point newLocation) {
       int row = Math.abs(newLocation.getX() - s.getLocation().getX());
       int col = Math.abs(newLocation.getY() - s.getLocation().getY());
       return s.getSpeed() >= (row+col);
    }
   
   /**
    * 
    * making sure the specified location is within the playing ground
    * 
    */
   private boolean withinBoard(Point location) {
       int row = location.getX();
       int col = location.getY();
        return (board.columns > col && col >= 0) && (0 <= row && row < board.rows);
    }
   
   
   /**
    * 
    *removes the warship from the list of ships on the board and replacing its position with the default object
    *
    */
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
    
    /**
     *
     * Checks if the specified object on the board  is a ship    
     * 
     * 
     * @param object
     * 
     * @return 
     */
    private boolean isShip(Object object) {
        try {
            Ship ship = (Ship)object;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    private List<Ship> getSurroundingShips(Ship currentShip){
        List<Ship> ships = new ArrayList<>();
        Point points[] = getSurroundingPoints(currentShip.getLocation());
        for(Point point : points) {
            try {
                if(withinBoard(point)) {
                     wShip = getShip(point);
                    //if(!this.isInFleet(wShip))
                         ships.add(wShip/*getShip(point)*/);
                         System.out.println("Debug: "+wShip.getName());
                }               
            } catch (NotShipException ex) {
               
            }
        }                 
        return ships;
    }
    
    private Point[] getSurroundingPoints(Point point){
        int row = point.getX() + 1;
        int col = point.getY() + 1;
        Point west = getWestPoint(row,col);
        Point northwest = getNorthWestPoint(row,col);
        Point north = getNorthPoint(row, col);
        Point northeast = getNortheastPoint(row,col);
        Point east = getEastPoint(row,col);
        Point southeast = getSoutheastPoint(row,col);
        Point south = getSouthPoint(row,col);
        Point southwest = getSouthwestPoint(row,col);
       return new Point[] {
           west,northwest,north,northeast,east,southeast,south,southwest
       };
    }
    
    private Point getWestPoint(int row, int col){
        return new Point(row,--col);
    }    
            
    private Point getNorthWestPoint(int row, int col){
        return new Point(--row,--col);
    }
    
    private Point getNorthPoint(int row, int col){
        return new Point(--row,col);
    }
    
     private Point getNortheastPoint(int row, int col){
        return new Point(--row,++col);
    }

    private Point getEastPoint(int row, int col){
        return new Point(row,++col);
    }
    
    private Point getSoutheastPoint(int row, int col){
        return new Point(++row,++col);
    }
    
    private Point getSouthPoint(int row, int col){
        return new Point(++row,col);
    }
    
    private Point getSouthwestPoint(int row, int col){
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
