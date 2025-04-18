import java.util.ArrayList;
import java.util.Scanner;

class Tugas {
    private int id;
    private String judul;
    private String deskripsi;
    private String status;

    public Tugas(int id, String judul, String deskripsi, String status) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID Tugas: " + id + " | Judul: " + judul + " | Deskripsi: " + deskripsi + " | Status: " + status;
    }
}

public class ManajemenTugas {
    private static ArrayList<Tugas> daftarTugas = new ArrayList<>();
    private static int idBerikutnya = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nSistem Manajemen Tugas Pribadi");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Tugas");
            System.out.println("3. Perbarui Tugas");
            System.out.println("4. Hapus Tugas");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahTugas();
                    break;
                case 2:
                    lihatTugas();
                    break;
                case 3:
                    perbaruiTugas();
                    break;
                case 4:
                    hapusTugas();
                    break;
                case 5:
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Opsi tidak valid! Coba lagi.");
            }
        }
    }

    private static void tambahTugas() {
        System.out.print("Masukkan Judul Tugas: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan Deskripsi Tugas: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Masukkan Status Tugas (Menunggu/Dalam Proses/Selesai): ");
        String status = scanner.nextLine();

        daftarTugas.add(new Tugas(idBerikutnya++, judul, deskripsi, status));
        System.out.println("Tugas berhasil ditambahkan!");
    }

    private static void lihatTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Tidak ada tugas yang tersedia.");
        } else {
            for (Tugas tugas : daftarTugas) {
                System.out.println(tugas);
            }
        }
    }

    private static void perbaruiTugas() {
        System.out.print("Masukkan ID Tugas yang ingin diperbarui: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Tugas tugas : daftarTugas) {
            if (tugas.getId() == id) {
                System.out.print("Masukkan Judul Baru: ");
                tugas.setJudul(scanner.nextLine());
                System.out.print("Masukkan Deskripsi Baru: ");
                tugas.setDeskripsi(scanner.nextLine());
                System.out.print("Masukkan Status Baru: ");
                tugas.setStatus(scanner.nextLine());
                System.out.println("Tugas berhasil diperbarui!");
                return;
            }
        }
        System.out.println("Tugas tidak ditemukan.");
    }

    private static void hapusTugas() {
        System.out.print("Masukkan ID Tugas yang ingin dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        for (Tugas tugas : daftarTugas) {
            if (tugas.getId() == id) {
                daftarTugas.remove(tugas);
                System.out.println("Tugas berhasil dihapus!");
                return;
            }
        }
        System.out.println("Tugas tidak ditemukan.");
    }
}
