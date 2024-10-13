/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package posttest2.managementhotel;

import management.RoomManagement;
import model.StandardRoom;
import model.DeluxeRoom;
import model.SuiteRoom;
import model.Room;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nyoma
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomManagement roomManagement = new RoomManagement();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Sistem Manajemen Kamar Hotel ===");
            System.out.println("1. Tambah Kamar");
            System.out.println("2. Hapus Kamar");
            System.out.println("3. Update Kamar");
            System.out.println("4. Lihat Semua Kamar");
            System.out.println("5. Cari Kamar");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    tambahKamar(scanner, roomManagement);
                    break;
                case 2:
                    hapusKamar(scanner, roomManagement);
                    break;
                case 3:
                    updateKamar(scanner, roomManagement);
                    break;
                case 4:
                    lihatSemuaKamar(roomManagement);
                    break;
                case 5:
                    cariKamar(scanner, roomManagement);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }

    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid, silakan masukkan angka: ");
            }
        }
    }

    private static void tambahKamar(Scanner scanner, RoomManagement roomManagement) {
        while (true) {
            System.out.print("Masukkan nomor kamar (0 untuk kembali): ");
            int roomNumber = getValidInteger(scanner);
            if (roomNumber == 0) {
                return; // Kembali ke menu utama
            }

            System.out.print("Masukkan harga kamar: ");
            double price = getValidDouble(scanner);
            System.out.print("Tipe Kamar (1. Standard, 2. Deluxe, 3. Suite): ");
            int roomTypeChoice = getValidInteger(scanner);
            System.out.print("Status tersedia (true/false): ");
            boolean available = getValidBoolean(scanner);
            Room room;

            if (roomTypeChoice == 1) {
                room = new StandardRoom(roomNumber, price, available);
            } else if (roomTypeChoice == 2) {
                room = new DeluxeRoom(roomNumber, price, available);
            } else if (roomTypeChoice == 3) {
                room = new SuiteRoom(roomNumber, price, available);
            } else {
                System.out.println("Pilihan tipe kamar tidak valid. Silakan coba lagi.");
                continue; // Mengulangi input jika tipe kamar tidak valid
            }

            roomManagement.tambahKamar(room);
            break; // Keluar dari perulangan setelah kamar ditambahkan
        }
    }

    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid, silakan masukkan angka desimal: ");
            }
        }
    }

    private static boolean getValidBoolean(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.print("Input tidak valid, silakan masukkan true atau false: ");
            }
        }
    }

    private static void hapusKamar(Scanner scanner, RoomManagement roomManagement) {
        while (true) {
            System.out.print("Masukkan nomor kamar yang akan dihapus (0 untuk kembali): ");
            int roomNumber = getValidInteger(scanner);
            if (roomNumber == 0) {
                return; // Kembali ke menu utama
            }

            if (roomManagement.hapusKamar(roomNumber)) {
                System.out.println("Kamar dengan nomor " + roomNumber + " berhasil dihapus.");
            } else {
                System.out.println("Kamar dengan nomor " + roomNumber + " tidak ditemukan.");
            }
            break; // Keluar dari perulangan setelah kamar dihapus
        }
    }

    private static void updateKamar(Scanner scanner, RoomManagement roomManagement) {
        while (true) {
            System.out.print("Masukkan nomor kamar yang akan diperbarui (0 untuk kembali): ");
            int roomNumber = getValidInteger(scanner);
            if (roomNumber == 0) {
                return; // Kembali ke menu utama
            }

            System.out.print("Masukkan tipe kamar baru (Standard/Deluxe/Suite): ");
            String newType = scanner.nextLine().trim();
            System.out.print("Masukkan harga baru: ");
            double newPrice = getValidDouble(scanner);
            System.out.print("Status tersedia (true/false): ");
            boolean available = getValidBoolean(scanner);

            Room updatedRoom;

            // Menentukan jenis kamar berdasarkan input pengguna
            if (newType.equalsIgnoreCase("Standard")) {
                updatedRoom = new StandardRoom(roomNumber, newPrice, available);
            } else if (newType.equalsIgnoreCase("Deluxe")) {
                updatedRoom = new DeluxeRoom(roomNumber, newPrice, available);
            } else if (newType.equalsIgnoreCase("Suite")) {
                updatedRoom = new SuiteRoom(roomNumber, newPrice, available);
            } else {
                System.out.println("Tipe kamar tidak valid!");
                continue; // Kembali ke awal perulangan untuk input baru
            }

            roomManagement.updateKamar(roomNumber, updatedRoom);
            System.out.println("Kamar berhasil diperbarui.");
            break; // Keluar dari perulangan setelah kamar diperbarui
        }
    }

    private static void lihatSemuaKamar(RoomManagement roomManagement) {
        List<Room> rooms = roomManagement.getAllRooms();
        System.out.println("\nDaftar Kamar:");
        for (Room room : rooms) {
            System.out.printf("Nomor Kamar: %d, Tipe: %s, Harga: %.2f, Tersedia: %s%n",
                    room.getRoomNumber(), room.getRoomType(), room.getPrice(), room.isAvailable() ? "Ya" : "Tidak");
        }
    }

    private static void cariKamar(Scanner scanner, RoomManagement roomManagement) {
        while (true) {
            System.out.print("Masukkan nomor kamar yang dicari (0 untuk kembali): ");
            int roomNumber = getValidInteger(scanner);
            if (roomNumber == 0) {
                return; // Kembali ke menu utama
            }

            Room room = roomManagement.cariKamar(roomNumber);
            if (room != null) {
                System.out.printf("Kamar ditemukan: Nomor Kamar: %d, Tipe: %s, Harga: %.2f, Tersedia: %s%n",
                        room.getRoomNumber(), room.getRoomType(), room.getPrice(), room.isAvailable() ? "Ya" : "Tidak");
            } else {
                System.out.println("Kamar dengan nomor " + roomNumber + " tidak ditemukan.");
            }
            break; // Keluar dari perulangan setelah mencari kamar
        }
    }
}
