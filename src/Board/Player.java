/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Ships.Ship;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Muhammad Amin
 */
public class Player {

    String PlayerId;
    String PlayerName;
    List<Ship> WarShips;
    Territory territory;

    public Player(String playerName,Territory teritory) {        
        Random r = new Random(100);
       //a r.nextInt();

        PlayerId = playerName.substring(0,1)+(( r.nextInt()%9)+1);        
        PlayerName = playerName;
        WarShips = new ArrayList<>();
        this.territory = teritory;
    }

    public String getPlayerid() {
      return PlayerId;
    }

    public String getPlayerName() {
       return PlayerName;
    }
    
    public void AddWarShip(Ship ship){
        WarShips.add(ship);
    }
    
    public void AddWarShips(Ship[] ships){
        for(Ship s : ships){
            AddWarShip(s);
        }
    }
    
    public List<Ship> getWarShips(){
        return WarShips;
    }
    
    public Territory getTerritory() {
        return territory;
    }

}

