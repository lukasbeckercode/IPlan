package com.lukasbecker.iplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Room <br />
 * a room hosts a course, has a name and a number
 * no two courses must appear in one room at the same time
 * @author Lukas Becker
 * Last Change: 15/06/2021, 10:44
 */

@Entity
public class Room implements Serializable {
    private int roomNr;
    private String roomName;

    /**
     * Constructor
     * @param roomNr room number
     * @param roomName room name
     */
    public Room(int roomNr, String roomName) {
        this.roomNr = roomNr;
        this.roomName = roomName;
    }

    /**
     * default constructor
     */
    public Room() {

    }

    /**
     * getter for room name
     * @return the name of a room
     */
    @Column
    public String getRoomName() {
        return roomName;
    }

    /**
     * setter for room name
     * @param roomName the name of a room
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * getter for room number
     * @return the rooms number
     */
    @Id
    @Column(unique = true)
    public int getRoomNr() {
        return roomNr;
    }

    /**
     * setter for room number
     * @param roomNr the room number to set
     */
    public void setRoomNr(int roomNr){
        this.roomNr = roomNr;
    }
}
