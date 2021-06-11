package com.lukasbecker.iplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * a room hosts a course, has a name and a number
 * no two courses must appear in one room at the same time
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

    public Room() {

    }

    @Column
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Id
    @Column(unique = true)
    public int getRoomNr() {
        return roomNr;
    }
    public void setRoomNr(int roomNr){
        this.roomNr = roomNr;
    }
}
