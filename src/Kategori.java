public class Kategori {
    private String idKategori;
    private String namaKategori;
    private String deskripsi;
    private boolean statusAktif;

    public Kategori() {
        this("K00", "Umum", "Kategori umum", true);
    }

    public Kategori(String idKategori, String namaKategori, String deskripsi, boolean statusAktif) {
        setIdKategori(idKategori);
        setNamaKategori(namaKategori);
        setDeskripsi(deskripsi);
        this.statusAktif = statusAktif;
    }

    public String getIdKategori() { return idKategori; }

    public void setIdKategori(String idKategori) {
        if (idKategori == null || idKategori.trim().isEmpty()) {
            throw new IllegalArgumentException("ID kategori tidak boleh kosong.");
        }
        this.idKategori = idKategori.trim();
    }

    public String getNamaKategori() { return namaKategori; }

    public void setNamaKategori(String namaKategori) {
        if (namaKategori == null || namaKategori.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kategori tidak boleh kosong.");
        }
        this.namaKategori = namaKategori.trim();
    }

    public String getDeskripsi() { return deskripsi; }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = (deskripsi == null) ? "" : deskripsi.trim();
    }

    public boolean isStatusAktif() { return statusAktif; }

    public void setStatusAktif(boolean statusAktif) { this.statusAktif = statusAktif; }

    public void ubahNama(String namaBaru) { setNamaKategori(namaBaru); }

    public void aktifkan() { this.statusAktif = true; }

    public void nonaktifkan() { this.statusAktif = false; }

    public void tampilkanInfo() {
        System.out.println("Kategori: " + idKategori + " | " + namaKategori
                + " | " + (statusAktif ? "Aktif" : "Nonaktif"));
    }

    @Override
    public String toString() { return namaKategori; }
}