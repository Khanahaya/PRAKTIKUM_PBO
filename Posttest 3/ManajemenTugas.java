import java.util.ArrayList;
import java.util.Scanner;

// Parent class
class Tugas {
    private int id;
    protected String judul;
    protected String deskripsi;
    protected String status;

    public Tugas(int id, String judul, String deskripsi, String status) {
        this.id = id;
        this.setJudul(judul);
        this.setDeskripsi(deskripsi);
        this.setStatus(status);
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        if (!judul.trim().isEmpty()) {
            this.judul = judul;
        } else {
            System.out.println("Judul tidak boleh kosong!");
        }
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        if (!deskripsi.trim().isEmpty()) {
            this.deskripsi = deskripsi;
        } else {
            System.out.println("Deskripsi tidak boleh kosong!");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("Menunggu") || 
            status.equalsIgnoreCase("Dalam Proses") || 
            status.equalsIgnoreCase("Selesai")) {
            this.status = status;
        } else {
            System.out.println("Status tidak valid! Pilih antara Menunggu, Dalam Proses, atau Selesai.");
        }
    }

    @Override
    public String toString() {
        return "ID Tugas: " + id + " | Judul: " + judul + " | Deskripsi: " + deskripsi + " | Status: " + status;
    }
}

// Subclass 1: Tugas dengan prioritas
class TugasPrioritas extends Tugas {
    private String prioritas; // Rendah, Sedang, Tinggi

    public TugasPrioritas(int id, String judul, String deskripsi, String status, String prioritas) {
        super(id, judul, deskripsi, status);
        this.setPrioritas(prioritas);
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        if (prioritas.equalsIgnoreCase("Rendah") || prioritas.equalsIgnoreCase("Sedang") || prioritas.equalsIgnoreCase("Tinggi")) {
            this.prioritas = prioritas;
        } else {
            System.out.println("Prioritas tidak valid! Pilih antara Rendah, Sedang, atau Tinggi.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Prioritas: " + prioritas;
    }
}

// Subclass 2: Tugas dengan tenggat waktu
class TugasBerjangka extends Tugas {
    private String tenggatWaktu;

    public TugasBerjangka(int id, String judul, String deskripsi, String status, String tenggatWaktu) {
        super(id, judul, deskripsi, status);
        this.tenggatWaktu = tenggatWaktu;
    }

    public String getTenggatWaktu() {
        return tenggatWaktu;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tenggat Waktu: " + tenggatWaktu;
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
            System.out.println("3. Keluar");
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
                    System.out.println("Keluar...");
                    return;
                default:
                    System.out.println("Opsi tidak valid! Coba lagi.");
            }
        }
    }

    private static void tambahTugas() {
        System.out.println("Pilih jenis tugas:");
        System.out.println("1. Tugas Biasa");
        System.out.println("2. Tugas dengan Prioritas");
        System.out.println("3. Tugas dengan Tenggat Waktu");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan Judul Tugas: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan Deskripsi Tugas: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Masukkan Status Tugas (Menunggu/Dalam Proses/Selesai): ");
        String status = scanner.nextLine();

        if (jenis == 1) {
            daftarTugas.add(new Tugas(idBerikutnya++, judul, deskripsi, status));
        } else if (jenis == 2) {
            System.out.print("Masukkan Prioritas (Rendah/Sedang/Tinggi): ");
            String prioritas = scanner.nextLine();
            daftarTugas.add(new TugasPrioritas(idBerikutnya++, judul, deskripsi, status, prioritas));
        } else if (jenis == 3) {
            System.out.print("Masukkan Tenggat Waktu (YYYY-MM-DD): ");
            String tenggat = scanner.nextLine();
            daftarTugas.add(new TugasBerjangka(idBerikutnya++, judul, deskripsi, status, tenggat));
        } else {
            System.out.println("Jenis tugas tidak valid!");
        }
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
}
