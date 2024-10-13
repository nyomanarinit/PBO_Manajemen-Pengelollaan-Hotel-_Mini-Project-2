/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nyoma
 */
public class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, double price, boolean available) {
        super(roomNumber, price, available);
    }


    @Override
    public String getRoomType() {
        return "Suite";
    }
}

