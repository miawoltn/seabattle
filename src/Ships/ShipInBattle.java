/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ships;

import Board.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Class for extending the Ship functionality when engaging in a battle.
 * 
 * @author MuhammadAmin
 * @version 25/11/2017
 */
public class ShipInBattle extends ShipDecorator {
    private final String playerId;
    public ShipInBattle(Ship ship, String owner) {
        super(ship);
        playerId = owner;
    }

    /**
     * Gets the ship types a Ship can destroy during battle.
     * 
     * @return list of @see{ShipType}
     */
    @Override
    public List<ShipType> getDestroyableShips() {
        switch (getType()) {
            case BattleShip:
                 return Arrays.asList(ShipType.Destroyer, ShipType.Minesweeper);
            case Destroyer:
                return Arrays.asList(ShipType.Submarine, ShipType.Minesweeper);
            case Submarine:
                return Arrays.asList(ShipType.BattleShip,ShipType.Minesweeper); 
            case Minesweeper:
            case Mine:
                return new ArrayList<>(); 
        }
        return new ArrayList<>();
    }

    /**
     * Gets the ship types that can destroy this particular Ship.
     * 
     * @return list of @see{ShipType} that can destroy this ship.
     */
    @Override
    public List<ShipType> getPredatorShips() {
        switch (getType()) {
            case BattleShip:
                return Arrays.asList(ShipType.Submarine);
            case Destroyer:
                return Arrays.asList(ShipType.BattleShip);
            case Submarine:
                return Arrays.asList(ShipType.Destroyer);
            case Minesweeper:
                return Arrays.asList(ShipType.Destroyer, ShipType.BattleShip, ShipType.Submarine);
            case Mine:
                return new ArrayList<>(); 
        }
        return new ArrayList<>();
    }

    @Override
    public String getOwner() {
        return playerId;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setMove(int horizontal, int vertical) throws TooManyShipMovesException {
        super.setMove(horizontal, vertical);
    }

    @Override
    public Point getLocation() {
        return super.getLocation();
    }

    @Override
    public void setLocation(Point p) {
        super.setLocation(p);
    }

    @Override
    public ShipType getType() {
        return super.getType();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }   

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
