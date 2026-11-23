import java.util.ArrayList;

public class Perpustakaan {
 
    public static final int KAPASITAS_BUKU = 10;

    private final SortedList daftarBuku = new SortedList(KAPASITAS_BUKU);
    private final ArrayList<Anggota> daftarAnggota = new ArrayList<>();
    private final ArrayList<Peminjaman> daftarPeminjaman = new ArrayList<>();
    private final ArrayList<Kategori> daftarKategori = new ArrayList<>();
    private int counterTransaksi = 0;

    public SortedList getDaftarBuku() { return daftarBuku; }


    public Kategori cariAtauBuatKategori(String nama) {
        for (Kategori k : daftarKategori) {
            if (k.getNamaKategori().equalsIgnoreCase(nama.trim())) return k;
        }
        Kategori baru = new Kategori(String.format("K%02d", daftarKategori.size() + 1), nama, "", true);
        daftarKategori.add(baru);
        return baru;
    }

    public void tambahBuku(Buku buku) { daftarBuku.insert(buku); }

    public Buku hapusBuku(String kode) {
        for (Peminjaman p : daftarPeminjaman) {
            if (p.isAktif() && p.getBuku().getKodeBuku().equalsIgnoreCase(kode.trim())) {
                throw new IllegalStateException("Buku masih dipinjam, tidak dapat dihapus.");
            }
        }
        return daftarBuku.remove(kode);
    }

    public void tambahAnggota(Anggota a) {
        if (cariAnggota(a.getIdAnggota()) != null) {
            throw new IllegalArgumentException("ID anggota " + a.getIdAnggota() + " sudah terdaftar.");
        }
        daftarAnggota.add(a);
    }

    public Buku cariBuku(String kode) { return daftarBuku.search(kode); }

    public Anggota cariAnggota(String id) {
        for (Anggota a : daftarAnggota) {
            if (a.getIdAnggota().equalsIgnoreCase(id.trim())) return a;
        }
        return null;
    }

    public Peminjaman cariPeminjaman(String id) {
        for (Peminjaman p : daftarPeminjaman) {
            if (p.getIdPeminjaman().equalsIgnoreCase(id.trim())) return p;
        }
        return null;
    }

    public Peminjaman buatPeminjaman(String idAnggota, String kodeBuku) {
        Anggota a = cariAnggota(idAnggota);
        if (a == null) throw new IllegalArgumentException("Anggota " + idAnggota + " tidak ditemukan.");
        Buku b = cariBuku(kodeBuku);
        if (b == null) throw new IllegalArgumentException("Buku " + kodeBuku + " tidak ditemukan.");
        Peminjaman p = new Peminjaman(String.format("TRX%03d", counterTransaksi + 1), a, b);
        p.prosesPinjam();              // melempar exception bila gagal -> tidak tersimpan
        counterTransaksi++;
        daftarPeminjaman.add(p);
        return p;
    }

    public Peminjaman prosesPengembalian(String idTransaksi) {
        Peminjaman p = cariPeminjaman(idTransaksi);
        if (p == null) throw new IllegalArgumentException("Transaksi " + idTransaksi + " tidak ditemukan.");
        p.prosesKembali();
        return p;
    }

    public void tampilkanSemuaAnggota() {
        if (daftarAnggota.isEmpty()) { System.out.println("(Belum ada anggota)"); return; }
        for (Anggota a : daftarAnggota) a.tampilkanInfo();
    }

    public void tampilkanSemuaTransaksi() {
        if (daftarPeminjaman.isEmpty()) { System.out.println("(Belum ada transaksi)"); return; }
        for (Peminjaman p : daftarPeminjaman) p.tampilkanInfo();
    }

    public void tampilkanLaporan() {
        int aktif = 0;
        for (Peminjaman p : daftarPeminjaman) if (p.isAktif()) aktif++;
        System.out.println("Jumlah buku (judul)    : " + daftarBuku.size() + " / " + daftarBuku.getKapasitas());
        System.out.println("Jumlah anggota         : " + daftarAnggota.size());
        System.out.println("Jumlah kategori        : " + daftarKategori.size());
        System.out.println("Jumlah transaksi       : " + daftarPeminjaman.size());
        System.out.println("Transaksi masih aktif  : " + aktif);
    }
}
