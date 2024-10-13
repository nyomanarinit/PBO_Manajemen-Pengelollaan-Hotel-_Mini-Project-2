# PBO Manajemen Pengelollaan Hotel Mini Project 2
# NAMA  : Nyoman Arini Trirahayu
# NIM   : 2309116002
# KELAS : Sistem Informasi-A 23

## Studi Kasus
Studi kasus ini berfokus pada pengembangan sistem manajemen kamar hotel yang bertujuan untuk mempermudah pengelolaan informasi kamar bagi staf hotel. Sistem ini akan memungkinkan pengguna untuk melakukan operasi CRUD (Create, Read, Update, Delete) pada data kamar, serta mengelola status ketersediaan kamar secara efisien. Dengan menggunakan pemrograman berorientasi objek, sistem ini akan menerapkan konsep inheritance dan encapsulation untuk menciptakan struktur yang jelas dan terorganisir. Diharapkan, sistem ini dapat meningkatkan efisiensi operasional, mengurangi kesalahan dalam pemesanan, dan pada akhirnya meningkatkan pengalaman tamu di hotel.

## Source Code dan Penjelasan 
### class Room.java packages model
package model;
public abstract class Room {
    private int roomNumber;
    private double price;
    private boolean available;

    public Room(int roomNumber, double price, boolean available) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.available = available;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getRoomType();
}

*Abstraksi*: Kelas Room adalah kelas abstrak yang mendefinisikan atribut dan metode dasar untuk semua jenis kamar di hotel. Dengan menggunakan kelas abstrak, Anda dapat memastikan bahwa semua subclass (seperti StandardRoom, DeluxeRoom, dan SuiteRoom) akan memiliki atribut dan metode yang sama, tetapi dengan implementasi yang berbeda.

*Encapsulation*: Atribut roomNumber, price, dan available di kelas ini dideklarasikan sebagai private. Ini mencegah akses langsung dari luar kelas dan memungkinkan pengendalian akses melalui metode getter dan setter.
Metode getter yang disediakan (getRoomNumber(), getPrice(), dan isAvailable()) memungkinkan akses yang aman terhadap atribut tersebut.
Method Abstrak:

Metode getRoomType() adalah metode abstrak yang harus diimplementasikan oleh subclass. Ini memberikan fleksibilitas untuk menentukan jenis kamar yang berbeda pada masing-masing subclass, sekaligus memastikan bahwa setiap kamar harus memiliki cara untuk mengembalikan tipe kamar mereka.

### class StandardRoom.java packages model
package model;
public class StandardRoom extends Room {
    public StandardRoom(int roomNumber, double price, boolean available) {
        super(roomNumber, price, available);
    }

    @Override
    public String getRoomType() {
        return "Standard";
    }
}

### class DeluxeRoom.java packages model
package model;
public class DeluxeRoom extends Room {
    public DeluxeRoom(int roomNumber, double price, boolean available) {
        super(roomNumber, price, available);
    }

    @Override
    public String getRoomType() {
        return "Deluxe";
    }
}

### class SuiteRoom.java packages model
package model;
public class SuiteRoom extends Room {
    public SuiteRoom(int roomNumber, double price, boolean available) {
        super(roomNumber, price, available);
    }

    @Override
    public String getRoomType() {
        return "Suite";
    }
}

Subkelas: StandardRoom
Kelas StandardRoom adalah subkelas yang mewakili kamar standar. Biasanya, kamar ini memiliki fasilitas dasar dan harga sewa yang lebih terjangkau. Atribut dan metode dari kelas ini adalah: Mewarisi semua atribut dan metode dari kelas Room.
Menyediakan implementasi untuk getRoomType() yang mengembalikan nilai "Standard" untuk menunjukkan tipe kamar.

Subkelas: DeluxeRoom
Kelas DeluxeRoom adalah subkelas yang mewakili kamar deluxe. Kamar ini biasanya lebih luas dan dilengkapi dengan fasilitas tambahan dibandingkan kamar standar, dengan harga yang lebih tinggi. Atribut dan metode dari kelas ini adalah: Mewarisi semua atribut dan metode dari kelas Room.
Menyediakan implementasi untuk getRoomType() yang mengembalikan nilai "Deluxe".

Subkelas: SuiteRoom
Kelas SuiteRoom adalah subkelas yang mewakili kamar suite, yang biasanya merupakan tipe kamar paling mewah dengan ruang tamu terpisah dan berbagai fasilitas premium. Atribut dan metode dari kelas ini adalah: Mewarisi semua atribut dan metode dari kelas Room.
Menyediakan implementasi untuk getRoomType() yang mengembalikan nilai "Suite".

Ketiga kelas ini bekerja sama dalam sistem manajemen kamar hotel untuk menyediakan berbagai pilihan kamar sesuai dengan kebutuhan dan anggaran tamu. Dengan menggunakan pemrograman berorientasi objek, mereka dapat dengan mudah dikelola dan ditampilkan dalam aplikasi, memberikan fleksibilitas dalam menangani berbagai jenis kamar dan mempermudah pemeliharaan kode. Metode yang diwariskan dari kelas Room memastikan bahwa semua tipe kamar memiliki struktur dasar yang konsisten, sementara metode spesifik di setiap subkelas memungkinkan penyesuaian untuk kebutuhan masing-masing tipe kamar.

### class RoomManagement.java packages managamenet
package management;

import model.Room;
import java.util.ArrayList;
import java.util.List;
import model.DeluxeRoom;
import model.StandardRoom;
import model.SuiteRoom;


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

Berikut adalah penjelasan mengenai kelas `RoomManagement` dan penerapan konsep-konsep pemrograman berorientasi objek:

### Penjelasan Kelas `RoomManagement`

1. Encapsulation:
   - Kelas `RoomManagement` memiliki atribut `rooms`, yang merupakan daftar kamar (`List<Room>`). Atribut ini bersifat private, sehingga tidak bisa diakses langsung dari luar kelas.
   - Kelas ini menyediakan metode publik untuk mengelola kamar, seperti `tambahKamar()`, `hapusKamar()`, dan `cariKamar()`, yang memungkinkan interaksi dengan data kamar tanpa mengungkapkan detail implementasi internal.

2. Constructor:
   - Konstruktor `RoomManagement()` menginisialisasi daftar `rooms` dan memanggil metode `addDefaultRooms()` untuk menambahkan lima kamar default. Ini memastikan bahwa sistem sudah memiliki beberapa data awal untuk digunakan.

3. Method `addDefaultRooms()`:
   - Metode ini bertanggung jawab untuk menambahkan lima kamar dengan berbagai tipe ke dalam daftar `rooms`. Penggunaan berbagai tipe kamar (`StandardRoom`, `DeluxeRoom`, dan `SuiteRoom`) menunjukkan penerapan konsep pewarisan yang telah didefinisikan sebelumnya.

4. Method `tambahKamar(Room room)`:
   - Metode ini memungkinkan penambahan kamar baru ke dalam daftar `rooms`. Setelah menambahkan kamar, metode ini memberikan umpan balik ke pengguna dengan mencetak informasi tentang kamar yang baru ditambahkan.

5. Method `hapusKamar(int roomNumber)`:
   - Metode ini mencari kamar berdasarkan nomor dan menghapusnya dari daftar jika ditemukan. Menggunakan loop untuk iterasi daftar, metode ini menjamin bahwa setiap kamar diperiksa satu per satu.

6. Method `updateKamar(int roomNumber, Room updatedRoom)`:
   - Metode ini memungkinkan pengguna untuk memperbarui informasi kamar yang ada. Dengan memeriksa nomor kamar, metode ini menemukan kamar yang sesuai dan memperbarui data dengan objek `updatedRoom` yang baru.

7. Method `getAllRooms()`:
   - Metode ini mengembalikan seluruh daftar kamar. Meskipun `rooms` adalah atribut private, metode ini memungkinkan akses ke daftar kamar dengan cara yang aman.

8. Method `cariKamar(int roomNumber)`:
   - Metode ini mencari kamar berdasarkan nomor yang diberikan dan mengembalikan objek `Room` jika ditemukan. Jika tidak, ia mengembalikan null.

Kelas `RoomManagement` bertindak sebagai pengelola utama untuk semua kamar hotel. Dengan menggunakan prinsip-prinsip pemrograman berorientasi objek seperti enkapsulasi, pewarisan, dan implementasi metode CRUD, kelas ini menyediakan struktur yang rapi dan mudah digunakan untuk mengelola data kamar. Hal ini membantu menjaga kode tetap terorganisir dan memungkinkan pengembangan lebih lanjut di masa depan.

### Penjelasan Kelas RoomManagementInterface.java packages Management 
package management;

import model.Room;
import java.util.List;

public interface RoomManagementInterface {
    void tambahKamar(Room room);
    void hapusKamar(int roomNumber);
    void updateKamar(int roomNumber, Room updatedRoom);
    Room cariKamar(int roomNumber);
    List<Room> getAllRooms();
}

Interface (RoomManagementInterface) hanya mendefinisikan signature atau nama metode tanpa menyediakan implementasinya.
Ini semacam kontrak yang mengatakan bahwa kelas yang mengimplementasikan interface tersebut harus menyediakan logika untuk setiap metode yang didefinisikan. 
Terdapat beberapa metode yang dideklarasikan dalam interface ini, yang mencakup:
  - tambahKamar(Room room): Metode untuk menambahkan kamar baru.
  - hapusKamar(int roomNumber): Metode untuk menghapus kamar berdasarkan nomor kamar.
  - updateKamar(int roomNumber, Room updatedRoom): Metode untuk memperbarui informasi kamar yang sudah ada.- `getAllRooms()`: Metode untuk 
     mengambil daftar semua kamar.
  - cariKamar(int roomNumber): Metode untuk mencari kamar berdasarkan nomor kamar.
Implementation Requirement:
   - Kelas yang mengimplementasikan `RoomInterface` harus menyediakan implementasi konkret untuk semua metode yang dideklarasikan dalam interface ini.
   - Ini memastikan bahwa semua kelas yang mengelola kamar mengikuti kontrak yang sama, sehingga menjamin konsistensi dalam pengelolaan data kamar.


### Penjelasan Kelas main.java 
Berikut adalah penjelasan mengenai kelas `Main` dan penerapan konsep-konsep pemrograman berorientasi objek:

### Penjelasan Kelas `Main`

1. Penggunaan Kelas dan Objek:
   - Kelas `Main` berfungsi sebagai titik awal program. Dalam kelas ini, objek dari kelas `RoomManagement` dibuat, yang memungkinkan kita untuk mengelola daftar kamar hotel.
   - Dengan mendeklarasikan objek `RoomManagement`, kita dapat menggunakan metode yang terdapat dalam kelas tersebut untuk berinteraksi dengan data kamar.

2. Metode `main()`:
   - Metode `main()` adalah metode statis yang berfungsi sebagai titik masuk program. Ini adalah tempat di mana program mulai dieksekusi.
   - Di dalam metode ini, kita dapat menggunakan berbagai metode dari kelas `RoomManagement` untuk melakukan operasi seperti menambah, menghapus, dan memperbarui data kamar.

3. Contoh Interaksi:
   - Di dalam metode `main()`, kita dapat menampilkan menu untuk pengguna, meminta input dari pengguna, dan memanggil metode yang sesuai dari objek `RoomManagement`. 
   - Misalnya, kita bisa menggunakan `roomManagement.tambahKamar()` untuk menambah kamar baru berdasarkan input pengguna, atau `roomManagement.getAllRooms()` untuk menampilkan semua kamar yang ada.

4. Pengulangan dan Percabangan:
   - Kelas `Main` dapat menggunakan struktur pengulangan (loop) dan percabangan (if-else) untuk membuat menu interaktif. Ini memungkinkan pengguna untuk memilih tindakan yang diinginkan, seperti menambah, menghapus, atau mencari kamar.
   - Dengan cara ini, pengguna dapat berinteraksi dengan program secara dinamis.

Kelas `Main` adalah tempat di mana program dijalankan dan menghubungkan berbagai kelas dalam sistem. Melalui penggunaan objek dan metode, kelas ini memungkinkan pengguna untuk berinteraksi dengan sistem manajemen kamar hotel. Dengan menerapkan struktur menu, pengulangan, dan percabangan, kelas ini memberikan antarmuka yang ramah pengguna dan memudahkan pengelolaan data kamar. 

## Penjelasan Alur Program
1. Tampilan Awal Program
   
   ![image](https://github.com/user-attachments/assets/ad2eafec-34d7-46ef-bb2e-66b784898eb0)
    
    Pada awal program terdapat ucapan salam dan langsung ditampilkan bagian menu dari program hotel ini. ada 6 pilihan menu yaitu;
    1. Tambah Kamar
    2. Hapus Kamar
    3. Update Kamar
    4. Lihat Semua Kamar
    5. Cari Kamar
    6. Keluar
   staf dapat melakukan pilihan dengan menginputkan angka 1-6 yang tertera di menu.

2. Tampilan Menu Lihat Semua Kamar

   ![image](https://github.com/user-attachments/assets/a9343ef0-18b0-40f4-ac5d-de0fe460c13c)

    Pada tampilan Lihat Semua kamar maka staf di sini akan mendapatkan print output list kamar mulai dari nomer kamar, harga, tipe dan ketersediaan        kamar.

3. Tampilan Menu Tambah Kamar

   ![image](https://github.com/user-attachments/assets/42615477-41be-4ea9-be4e-bb4c966ac2ac)


   ![image](https://github.com/user-attachments/assets/a59f992b-9789-4d5a-9754-8e5c70bc4816)

   Pada tampilan menu tambahkamar di sini staf dapat menambahkan kamar dengan mengisi data yang sudah tersedia yaitu;
   Masukkan nomor kamar (0 untuk kembali): 106
   Masukkan harga kamar: 200000
   Tipe Kamar (1. Standard, 2. Deluxe, 3. Suite): 1
   Status tersedia (true/false): true

   maka akan mendapatkan hasil print output:
   Kamar berhasil ditambahkan. Nomor: 106, Tipe: Standard, Harga: 200000,00, Tersedia: Ya
   
4. Tampilan Menu Update Kamar
5. 
    *tampilan sebelum di update data*
   
   ![image](https://github.com/user-attachments/assets/2bc1e756-ae2a-4c1f-8a7a-f8f98414a031)


   *tampilan proses update data*
   
   ![image](https://github.com/user-attachments/assets/925a6c69-5945-49bf-aae8-d5631447aa16)


    *tampilan sesudah di update data*
   
   ![image](https://github.com/user-attachments/assets/def658d6-2639-4fc1-a040-7b30bc86e7c0)


    Pada tampilan ini staf dapat melakukan update data sewaktu waktu kapan kamar tersebut dapat berubah, staf akan diminta mengisi pertanyaan yaitu;
    Masukkan nomor kamar yang akan diperbarui (0 untuk kembali): 105
    Masukkan tipe kamar baru (Standard/Deluxe/Suite): Suite
    Masukkan harga baru: 400000
    Status tersedia (true/false): false

    tampilan print output setelah berhasil update
    Kamar berhasil diperbarui.


6. Tampilan Menu Cari Kamar

   ![image](https://github.com/user-attachments/assets/1aa48f87-ed89-43c2-8538-d6594fbebdfe)

   adanya fitur tambahan pencarian di sini dapat membantu staf untuk mrlakukan pencarian ketersediaan kamar dengan memasukan nomer kamar saja, sesuai     pada contoh gambar diatas.

7. Tampilan Akhir Program

   ![image](https://github.com/user-attachments/assets/421ffccb-eec6-4e4d-b4af-e12177bc727a)

   Pada tampilan akhir program di sini akan memeberhentikan program. dimana jika staf kamar hotel sudah seleasai melakukan aktivitas maka staf dapat memberhentikan dan keluar program dengan memilih opsi 6.Keluar.

   

   
















