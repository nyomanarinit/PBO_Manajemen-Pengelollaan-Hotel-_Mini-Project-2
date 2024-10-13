/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import model.Room;

import java.util.ArrayList;
import java.util.List;
import model.DeluxeRoom;
import model.StandardRoom;
import model.SuiteRoom;

/**
 *
 * @author nyoma
 */
public class RoomManagement {
    private List<Room> rooms;

    public RoomManagement() {
        rooms = new ArrayList<>();
        // Menambahkan 5 kamar default
        addDefaultRooms();
    }

    private void addDefaultRooms() {
        rooms.add(new StandardRoom(101, 180000, true));
        rooms.add(new DeluxeRoom(102, 250000, true));
        rooms.add(new SuiteRoom(103, 400000, false));
        rooms.add(new StandardRoom(104, 200000, true));
        rooms.add(new DeluxeRoom(105, 300000, true));
    }

    public void tambahKamar(Room room) {
        rooms.add(room);
        System.out.printf("Kamar berhasil ditambahkan. Nomor: %d, Tipe: %s, Harga: %.2f, Tersedia: %s%n",
                room.getRoomNumber(), room.getRoomType(), room.getPrice(), room.isAvailable() ? "Ya" : "Tidak");
    }

    public boolean hapusKamar(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                rooms.remove(room);
                return true; // Kamar berhasil dihapus
            }
        }
        return false; // Kamar tidak ditemukan
    }

    public void updateKamar(int roomNumber, Room updatedRoom) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber() == roomNumber) {
                rooms.set(i, updatedRoom);
                break;
            }
        }
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public Room cariKamar(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null; // Kamar tidak ditemukan
    }
}
