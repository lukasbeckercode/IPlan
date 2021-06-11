package com.lukasbecker.iplan;

/**
 * a room hosts a course, has a name and a number
 * no two courses must appear in one room at the same time
 */
public class Room {
    private final int roomNr;
    private final String roomName;

    /**
     * Constructor
     * @param roomNr room number
     * @param roomName room name
     */
    public Room(int roomNr, String roomName) {
        this.roomNr = roomNr;
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomNr() {
        return roomNr;
    }
}
