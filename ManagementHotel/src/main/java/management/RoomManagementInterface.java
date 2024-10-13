/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import model.Room;
import java.util.List;

/**
 *
 * @author nyoma
 */


public interface RoomManagementInterface {
    void tambahKamar(Room room);
    void hapusKamar(int roomNumber);
    void updateKamar(int roomNumber, Room updatedRoom);
    Room cariKamar(int roomNumber);
    List<Room> getAllRooms();
}
