import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);
    private static final Perpustakaan PERPUS = new Perpustakaan();

    public static void main(String[] args) {
        boolean jalan = true;
        while (jalan) {
            tampilMenu();
            int pilih = bacaInt("Pilih menu: ");
            try {
                switch (pilih) {
                    case 1 -> tambahBuku();
                    case 2 -> { System.out.println("--- Daftar Buku ---"); PERPUS.getDaftarBuku().tampilkan(); }
                    case 3 -> tambahAnggota();
                    case 4 -> { System.out.println("--- Daftar Anggota ---"); PERPUS.tampilkanSemuaAnggota(); }
                    case 5 -> pinjam();
                    case 6 -> kembali();
                    case 7 -> cariBuku();
                    case 8 -> menuSortedList();
                    case 9 -> { System.out.println("--- Laporan ---"); PERPUS.tampilkanLaporan();
                                System.out.println("--- Transaksi ---"); PERPUS.tampilkanSemuaTransaksi(); }
                    case 0 -> { System.out.println("Terima kasih. Program selesai."); jalan = false; }
                    default -> System.out.println("[ERROR] Menu tidak tersedia.");
                }
            } catch (RuntimeException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private static void tampilMenu() {
        System.out.println("[       PERPUSTAKAAN SEDERHANA     ]");
        System.out.println("====================================");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tampilkan Semua Buku");
        System.out.println("3. Tambah Anggota");
        System.out.println("4. Tampilkan Semua Anggota");
        System.out.println("5. Pinjam Buku");
        System.out.println("6. Kembalikan Buku");
        System.out.println("7. Cari Buku");
        System.out.println("8. Operasi Sorted List");
        System.out.println("9. Laporan Perpustakaan");
        System.out.println("0. Keluar");
        System.out.println("====================================");
    }

    private static void tambahBuku() {
        String kode = bacaTeks("Kode buku   : ");
        String judul = bacaTeks("Judul       : ");
        String penulis = bacaTeks("Penulis     : ");
        int stok = bacaInt("Stok        : ");
        double harga = bacaDouble("Harga       : ");
        String kat = bacaTeks("Kategori    : ");
        Buku b = new Buku(kode, judul, penulis, stok, harga, PERPUS.cariAtauBuatKategori(kat));
        PERPUS.tambahBuku(b);
        System.out.println("[OK] Buku berhasil ditambahkan.");
    }

    private static void tambahAnggota() {
        String id = bacaTeks("ID anggota  : ");
        String nama = bacaTeks("Nama        : ");
        String alamat = bacaTeks("Alamat      : ");
        String telp = bacaTeks("No. telepon : ");
        PERPUS.tambahAnggota(new Anggota(id, nama, alamat, telp));
        System.out.println("[OK] Anggota berhasil didaftarkan.");
    }

    private static void pinjam() {
        String id = bacaTeks("ID anggota : ");
        String kode = bacaTeks("Kode buku  : ");
        Peminjaman p = PERPUS.buatPeminjaman(id, kode);
        System.out.println("[OK] Peminjaman berhasil. ID transaksi: " + p.getIdPeminjaman());
        p.tampilkanInfo();
    }

    private static void kembali() {
        String id = bacaTeks("ID transaksi : ");
        Peminjaman p = PERPUS.prosesPengembalian(id);
        System.out.println("[OK] Pengembalian berhasil.");
        p.tampilkanInfo();
    }

    private static void cariBuku() {
        String kode = bacaTeks("Kode buku : ");
        Buku b = PERPUS.cariBuku(kode);
        if (b == null) System.out.println("[INFO] Buku tidak ditemukan.");
        else b.tampilkanInfo();
    }

    private static void menuSortedList() {
        SortedList sl = PERPUS.getDaftarBuku();
        System.out.println("--- Operasi Sorted List (kapasitas " + sl.getKapasitas() + ") ---");
        System.out.println("1. Insert  2. Remove  3. Search  4. Get(index)  5. Tampilkan  6. Size");
        switch (bacaInt("Pilih operasi: ")) {
            case 1 -> tambahBuku();
            case 2 -> { Buku b = PERPUS.hapusBuku(bacaTeks("Kode buku : "));
                        System.out.println("[OK] Dihapus: " + b.getJudul()); }
            case 3 -> cariBuku();
            case 4 -> sl.get(bacaInt("Indeks : ")).tampilkanInfo();
            case 5 -> sl.tampilkan();
            case 6 -> System.out.println("Ukuran: " + sl.size() + " dari " + sl.getKapasitas());
            default -> System.out.println("[ERROR] Operasi tidak tersedia.");
        }
    }

    private static String bacaTeks(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!SC.hasNextLine()) { System.out.println("\nInput berakhir."); System.exit(0); }
            String s = SC.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("[ERROR] Input tidak boleh kosong.");
        }
    }

    private static int bacaInt(String prompt) {
        while (true) {
            String s = bacaTeks(prompt);
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("[ERROR] Masukkan angka bulat yang valid."); }
        }
    }

    private static double bacaDouble(String prompt) {
        while (true) {
            String s = bacaTeks(prompt);
            try { return Double.parseDouble(s); }
            catch (NumberFormatException e) { System.out.println("[ERROR] Masukkan angka yang valid."); }
        }
    }
}
