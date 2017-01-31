/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battlegame;

import Board.Board;
import Board.Controller;
import Board.ControllerNotSetException;
import Board.OverBoardException;
import Board.Player;
import Board.Point;
import Board.Teritory;
import Ships.BattleShip;
import Ships.NotShipException;
import Ships.Ship;
import Ships.ShipFactory;
import Ships.ShipMaxSpeedExceededException;
import Ships.ShipType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammad Amin
 */
public class BattleGame {

    
    static Board battleField;
    static int row, col;
    static String Loc[];
    static String name;
    static Player playerOne, playerTwo;
    BattleShip battleShip;
    static Ship ship;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        // Set the players
        
               
        Println("=============== Plyer 1 ===============");
         Print("Enter your name: ");
         name = PromptWarShipSelection(); 
         playerOne = new Player(name,Teritory.West);
         for(ShipType shipType : ShipType.values()) {
           Print("Enter "+shipType.name()+" name: ");
           name = PromptWarShipSelection().substring(0,1);
           ship = ShipFactory.Build(shipType, name+playerOne.getPlayerid());
           Print("Enter "+shipType.name()+" position - formart -> [?-?]: ");
           name = PromptWarShipSelection();
           row = Integer.parseInt(name.substring(0,1));
           col = Integer.parseInt(name.substring(2));
           ship.setLocation(new Point(row,col));
           playerOne.AddWarShip(ship);
         }
         
         
         Println("=============== Plyer 2 ===============");
         Print("Enter your name: ");
         name = PromptWarShipSelection(); 
         playerTwo = new Player(name,Teritory.East);
         for(ShipType shipType : ShipType.values()) {
           Print("Enter "+shipType.name()+" name: ");
           name = PromptWarShipSelection().substring(0,1);
           ship = ShipFactory.Build(shipType, name+playerTwo.getPlayerid());
           Print("Enter "+shipType.name()+" position - formart -> [?-?]: ");
           name = PromptWarShipSelection();
           Loc = name.split("-");
           row = Integer.parseInt(Loc[0]);
           col = Integer.parseInt(Loc[1]);
           ship.setLocation(new Point(row,col));
           playerTwo.AddWarShip(ship);
         }
         
         //create the empty board
         createBoard();
         Controller controller = new Controller(battleField,new Player[]{playerOne, playerTwo});
        
        //start game        
         showBoard(controller);
        try {
            Controller.startGame(controller);
        } catch (ControllerNotSetException ex) {
            Println(ex.getMessage());
        }
          showBoard(controller);
          int p1Hits = 0; 
          int p2Hits = 0;
         do {
             p1Hits = controller.getPlayerOneHits();
             p2Hits = controller.getPlayerTwoHits();
             int turn = controller.getTurn();
             Println("========== Player "+(turn+1)+"'s turn =========");
             Print("Enter ship's position to move [row-col]: ");
             name = EasyIn.getString("");           
             Loc = name.split("-");
             row = Integer.parseInt(Loc[0]);
             col = Integer.parseInt(Loc[1]);
            try {
                ship = controller.getShip(new Point(row,col));
                if(!controller.isInFleet(ship)) continue;
            } catch (NotShipException ex) {
                Println(ex.getMessage());
                continue;
            }
             Print("Enter new position [row-col]: ");
             name = EasyIn.getString(" ");             
             Loc = name.split("-");
             row = Integer.parseInt(Loc[0]);
             col = Integer.parseInt(Loc[1]);
            try {
                controller.moveShip(ship, new Point(row,col));
            } catch (ShipMaxSpeedExceededException | OverBoardException ex) {
               Println(ex.getMessage());
               continue;
            }
             showBoard(controller);
             controller.setTurn();
             Println(controller.getOutCome());
             Println(Integer.toString(controller.enemyShips.size()));
             controller.enemyShips.stream().forEach((s) -> {Println(s.getName()+" "+s.getLocation().toString());});
             controller.enemyShips.clear();
             
         }while (p1Hits != 3 || p2Hits != 3);       
    }
    
    public static void createBoard() {
        battleField = new Board(6,11);
    }
    
   public static void showBoard(Controller controller){
       Object[][] field = controller.getBoard();
        Println(" ");
        for (int x = 0; x < field[0].length; x++) 
                System.out.print("   "+(x+1));
        Println("");
        for (int i = 0; i < field.length; i++) {
            System.out.print(i+1+" ");
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j].toString()+"   ");
            }
            Print("\n\n");
        }  
   }

   public static String PromptWarShipSelection(){
      String input = EasyIn.getString("\\w");
      return input;
   }
   
   public String newPosition(){
      String input = EasyIn.getString("^\\d-\\d$");
      return input;
   }
     
    public static void Println(String message){
       System.out.println(message);
   }
   
   public static void Print(String message){
       System.out.print(message);
   }
}
