
public class Anggota {
    public static final int MAKS_PINJAMAN = 3;

    private String idAnggota;
    private String nama;
    private String alamat;
    private String nomorTelepon;
    private int jumlahPinjaman;

    public Anggota() {
        this("A000", "Tanpa Nama", "-", "-");
    }

    public Anggota(String idAnggota, String nama, String alamat, String nomorTelepon) {
        setIdAnggota(idAnggota);
        setNama(nama);
        setAlamat(alamat);
        setNomorTelepon(nomorTelepon);
        this.jumlahPinjaman = 0;
    }

    public String getIdAnggota() { return idAnggota; }

    public void setIdAnggota(String idAnggota) {
        if (idAnggota == null || idAnggota.trim().isEmpty()) {
            throw new IllegalArgumentException("ID anggota tidak boleh kosong.");
        }
        this.idAnggota = idAnggota.trim().toUpperCase();
    }

    public String getNama() { return nama; }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        this.nama = nama.trim();
    }

    public String getAlamat() { return alamat; }

    public void setAlamat(String alamat) {
        if (alamat == null || alamat.trim().isEmpty()) {
            throw new IllegalArgumentException("Alamat tidak boleh kosong.");
        }
        this.alamat = alamat.trim();
    }

    public String getNomorTelepon() { return nomorTelepon; }

    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon == null || !nomorTelepon.trim().matches("\\+?\\d{6,15}")) {
            throw new IllegalArgumentException("Nomor telepon harus berupa 6-15 digit angka.");
        }
        this.nomorTelepon = nomorTelepon.trim();
    }

    public int getJumlahPinjaman() { return jumlahPinjaman; }

    public boolean dapatMeminjam() { return jumlahPinjaman < MAKS_PINJAMAN; }

    /** Menambah hitungan pinjaman; ditolak jika sudah mencapai batas. */
    public void catatPeminjaman() {
        if (!dapatMeminjam()) {
            throw new IllegalStateException("Anggota sudah meminjam " + MAKS_PINJAMAN + " buku (batas maksimal).");
        }
        jumlahPinjaman++;
    }

    public void catatPengembalian() {
        if (jumlahPinjaman <= 0) {
            throw new IllegalStateException("Anggota tidak memiliki pinjaman aktif.");
        }
        jumlahPinjaman--;
    }

    public void tampilkanInfo() {
        System.out.printf("%-6s | %-20s | %-20s | %-14s | Pinjaman: %d/%d%n",
                idAnggota, nama, alamat, nomorTelepon, jumlahPinjaman, MAKS_PINJAMAN);
    }
}
