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
import Board.PlayerNotSetException;
import Board.Point;
import Board.Territory;
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
    static Controller controller;
    /**
     * @param args the command line arguments 
     */
    public static void main(String[] args){ 
        
         //create the empty board
         createBoard();
         controller = new Controller(battleField);
        
        
        // Set the players               
        Println("=============== Player 1 ===============");
         Print("Enter your name: ");
         name = getInput(); 
         playerOne = new Player(name,Territory.West);
         setupPlayer(playerOne);
         
         
         
         Println("=============== Player 2 ===============");
         Print("Enter your name: ");
         name = getInput(); 
         playerTwo = new Player(name,Territory.East);
         setupPlayer(playerTwo);
         
        controller.setPlayers(new Player[]{playerOne,playerTwo});
        
        //start game        
         //showBoard(controller, true);
        try {
            Controller.startGame(controller);
        } catch (ControllerNotSetException | PlayerNotSetException | OverBoardException ex) {
            Println(ex.getMessage());
            return;
        }
          showBoard(controller);
          int p1Hits = 0; 
          int p2Hits = 0;
         do {
             showBoard(controller,false); 
             p1Hits = controller.getPlayerOneHits();
             p2Hits = controller.getPlayerTwoHits();
             int turn = controller.getTurn();
             Println("========== Player "+(turn+1)+"'s turn =========");
             Print("Enter ship's position to move [row-col]: ");
             name = getInput(); //EasyIn.getString();           
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
             name = getInput(); //EasyIn.getString().trim();             
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
             
         }while (!controller.gameOver());   
         
         // TODO: display the winner
    }
    
    public static void setupPlayer(Player player) { 
        for(ShipType shipType : ShipType.values()) {
           Print("Enter "+shipType.name()+" name: ");
           name = getInput().substring(0,2);
           ship = ShipFactory.Build(shipType, name+player.getPlayerid());
           Print("Enter "+shipType.name()+" position - formart -> [?-?]: ");
           name = newPosition();
           while(name.indexOf('-') == -1){
             name = newPosition();
           }
             row = Integer.parseInt(name.substring(0,1));
             if(player.getTerritory() == Territory.East)
                 col = (Integer.parseInt(name.substring(2)) + 5);
             else
                col = Integer.parseInt(name.substring(2));
           
             ship.setLocation(new Point(row,col));
             player.AddWarShip(ship);
        }
    }
    
    public static void createBoard() {
        battleField = new Board(6,11);
    }
    
   public static void showBoard(Controller controller) {
       showBoard(controller, true);
   }
   public static void showBoard(Controller controller, boolean all){
       //for(int i = 0; i < 500; i++)
       //    Println("\n");
       
       Object[][] field = all ? controller.getBoard():controller.getCurrentPlayerBoard();
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

   public static String getInput(){
      String input = EasyIn.getString();
      return input.trim();
   }
   
   public static String newPosition(){
      String input = EasyIn.getString();
      return input;
   }
     
    public static void Println(String message){
       System.out.println(message);
   }
   
   public static void Print(String message){
       System.out.print(message);
   }
}
