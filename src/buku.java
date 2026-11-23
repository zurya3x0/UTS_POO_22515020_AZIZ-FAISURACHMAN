public class Buku {
    private String kodeBuku;
    private String judul;
    private String penulis;
    private int stok;
    private double harga;
    private Peminjaman kategori;

    public Buku() {
        this("B000", "Tanpa Judul", "Tanpa Penulis", 0, 0, null);
    }

    public Buku(String kodeBuku, String judul, String penulis, int stok, double harga, Peminjaman kategori) {
        setKodeBuku(kodeBuku);
        setJudul(judul);
        setPenulis(penulis);
        setStok(stok);
        setHarga(harga);
        this.kategori = kategori;
    }

    public String getKodeBuku() { return kodeBuku; }

    public void setKodeBuku(String kodeBuku) {
        if (kodeBuku == null || kodeBuku.trim().isEmpty()) {
            throw new IllegalArgumentException("Kode buku tidak boleh kosong.");
        }
        this.kodeBuku = kodeBuku.trim().toUpperCase();
    }

    public String getJudul() { return judul; }

    public void setJudul(String judul) {
        if (judul == null || judul.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong.");
        }
        this.judul = judul.trim();
    }

    public String getPenulis() { return penulis; }

    public void setPenulis(String penulis) {
        if (penulis == null || penulis.trim().isEmpty()) {
            throw new IllegalArgumentException("Penulis tidak boleh kosong.");
        }
        this.penulis = penulis.trim();
    }

    public int getStok() { return stok; }

    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif.");
        }
        this.stok = stok;
    }

    public double getHarga() { return harga; }

    public void setHarga(double harga) {
        if (harga < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif.");
        }
        this.harga = harga;
    }

    public Peminjaman getKategori() { return kategori; }

    public void setKategori(Peminjaman kategori) { this.kategori = kategori; }

    public void tambahStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penambahan stok harus lebih dari 0.");
        }
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah pengurangan stok harus lebih dari 0.");
        }
        if (jumlah > stok) {
            throw new IllegalStateException("Stok tidak mencukupi (stok saat ini: " + stok + ").");
        }
        this.stok -= jumlah;
    }

    public boolean cekKetersediaan() { return stok > 0; }


    public void tampilkanInfo() {
        System.out.printf("%-8s | %-28s | %-18s | Stok: %-3d | Rp%,.0f | %s%n",
                kodeBuku, judul, penulis, stok, harga,
                kategori == null ? "-" : kategori.getNamaKategori());
    }
}
