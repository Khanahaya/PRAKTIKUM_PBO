import java.util.ArrayList;
import java.util.Scanner;

// Interface
interface TampilkanDetail {
    void tampilkanRingkasan();
    void tampilkanDetailLengkap();
}

// Abstract Class (Parent)
abstract class Tugas {
    private final int id; // final attribute
    protected String judul;
    protected String deskripsi;
    protected String status;

    public Tugas(int id, String judul, String deskripsi, String status) {
        this.id = id;
        this.setJudul(judul);
        this.setDeskripsi(deskripsi);
        this.setStatus(status);
    }

    public final int getId() { // final method
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

    // Abstract Method
    public abstract String getJenisTugas();

    @Override
    public String toString() {
        return "ID Tugas: " + id + " | Judul: " + judul + " | Deskripsi: " + deskripsi + " | Status: " + status;
    }
}

// Subclass 1 - Implementasi Interface
class TugasPrioritas extends Tugas implements TampilkanDetail {
    private String prioritas;

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
    public String getJenisTugas() {
        return "Tugas dengan Prioritas";
    }

    @Override
    public String toString() {
        return super.toString() + " | Prioritas: " + prioritas;
    }

    @Override
    public void tampilkanRingkasan() {
        System.out.println("[Ringkasan] " + judul + " - " + prioritas);
    }

    @Override
    public void tampilkanDetailLengkap() {
        System.out.println("[Detail Lengkap]");
        System.out.println("ID: " + getId());
        System.out.println("Judul: " + judul);
        System.out.println("Deskripsi: " + deskripsi);
        System.out.println("Status: " + status);
        System.out.println("Prioritas: " + prioritas);
    }
}

// Subclass 2
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
    public String getJenisTugas() {
        return "Tugas Berjangka";
    }

    @Override
    public String toString() {
        return super.toString() + " | Tenggat Waktu: " + tenggatWaktu;
    }
}

// Subclass Biasa
class TugasBiasa extends Tugas {
    public TugasBiasa(int id, String judul, String deskripsi, String status) {
        super(id, judul, deskripsi, status);
    }

    @Override
    public String getJenisTugas() {
        return "Tugas Biasa";
    }
}

// Final Class untuk Main Program
public final class ManajemenTugas {
    private static ArrayList<Tugas> daftarTugas = new ArrayList<>();
    private static int idBerikutnya = 1;
    private static Scanner scanner = new Scanner(System.in);

    // Static variable tambahan
    private static String versiAplikasi = "1.0.0";

    // Static method tambahan
    public static void tampilkanInfoAplikasi() {
        System.out.println("=== Sistem Manajemen Tugas Pribadi ===");
        System.out.println("Versi Aplikasi: " + versiAplikasi);
    }

    public static void main(String[] args) {
        tampilkanInfoAplikasi(); // Menampilkan info aplikasi

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Tugas");
            System.out.println("3. Tambah Tugas (Overloading Demo)");
            System.out.println("4. Keluar");
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
                    demoOverloading();
                    break;
                case 4:
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
            daftarTugas.add(new TugasBiasa(idBerikutnya++, judul, deskripsi, status));
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
            return;
        }
        System.out.println("Tugas berhasil ditambahkan!");
    }

    private static void lihatTugas() {
        if (daftarTugas.isEmpty()) {
            System.out.println("Tidak ada tugas yang tersedia.");
        } else {
            for (Tugas tugas : daftarTugas) {
                System.out.println(tugas + " | Jenis: " + tugas.getJenisTugas());

                if (tugas instanceof TampilkanDetail) {
                    ((TampilkanDetail) tugas).tampilkanRingkasan(); // contoh interface
                }
            }
        }
    }

    public static void tambahTugas(String judul, String deskripsi, String status) {
        daftarTugas.add(new TugasBiasa(idBerikutnya++, judul, deskripsi, status));
        System.out.println("Tugas biasa berhasil ditambahkan (melalui overloading).");
    }

    public static void tambahTugas(String judul, String deskripsi, String status, String prioritas) {
        daftarTugas.add(new TugasPrioritas(idBerikutnya++, judul, deskripsi, status, prioritas));
        System.out.println("Tugas prioritas berhasil ditambahkan (melalui overloading).");
    }

    public static void tambahTugas(String judul, String deskripsi, String status, String tenggatWaktu, boolean isBerjangka) {
        if (isBerjangka) {
            daftarTugas.add(new TugasBerjangka(idBerikutnya++, judul, deskripsi, status, tenggatWaktu));
            System.out.println("Tugas berjangka berhasil ditambahkan (melalui overloading).");
        }
    }

    private static void demoOverloading() {
        tambahTugas("Belajar Polymorphism", "Pelajari konsep overloading dan overriding", "Menunggu");
        tambahTugas("Kerjakan Tugas OOP", "Selesaikan soal polymorphism", "Dalam Proses", "Tinggi");
        tambahTugas("Kumpulkan Tugas", "Upload ke sistem", "Selesai", "2025-04-15", true);
    }
}
