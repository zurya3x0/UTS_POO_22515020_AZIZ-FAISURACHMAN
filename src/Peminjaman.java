import java.time.LocalDate;

public class Peminjaman {
    public static final String STATUS_DIPINJAM = "DIPINJAM";
    public static final String STATUS_DIKEMBALIKAN = "DIKEMBALIKAN";

    private String idPeminjaman;
    private Anggota anggota;
    private Buku buku;
    private String tanggalPinjam;
    private String status;

    public Peminjaman(String idPeminjaman, Anggota anggota, Buku buku) {
        setIdPeminjaman(idPeminjaman);
        setAnggota(anggota);
        setBuku(buku);
        this.tanggalPinjam = LocalDate.now().toString();
        this.status = "BARU";
    }

    public String getIdPeminjaman() { return idPeminjaman; }

    public void setIdPeminjaman(String idPeminjaman) {
        if (idPeminjaman == null || idPeminjaman.trim().isEmpty()) {
            throw new IllegalArgumentException("ID peminjaman tidak boleh kosong.");
        }
        this.idPeminjaman = idPeminjaman.trim().toUpperCase();
    }

    public Anggota getAnggota() { return anggota; }

    public void setAnggota(Anggota anggota) {
        if (anggota == null) throw new IllegalArgumentException("Anggota tidak boleh null.");
        this.anggota = anggota;
    }

    public Buku getBuku() { return buku; }

    public void setBuku(Buku buku) {
        if (buku == null) throw new IllegalArgumentException("Buku tidak boleh null.");
        this.buku = buku;
    }

    public String getTanggalPinjam() { return tanggalPinjam; }

    public String getStatus() { return status; }

    public boolean isAktif() { return STATUS_DIPINJAM.equals(status); }

    public void prosesPinjam() {
        if (!status.equals("BARU")) {
            throw new IllegalStateException("Transaksi sudah diproses.");
        }
        if (!buku.cekKetersediaan()) {
            throw new IllegalStateException("Stok buku \"" + buku.getJudul() + "\" habis.");
        }
        if (!anggota.dapatMeminjam()) {
            throw new IllegalStateException("Anggota " + anggota.getNama()
                    + " sudah meminjam " + Anggota.MAKS_PINJAMAN + " buku (batas maksimal).");
        }
        buku.kurangiStok(1);
        anggota.catatPeminjaman();
        status = STATUS_DIPINJAM;
    }

    public void prosesKembali() {
        if (!isAktif()) {
            throw new IllegalStateException("Transaksi " + idPeminjaman + " sudah dikembalikan.");
        }
        buku.tambahStok(1);
        anggota.catatPengembalian();
        status = STATUS_DIKEMBALIKAN;
    }

    public void tampilkanInfo() {
        System.out.printf("%-8s | %-18s | %-26s | %s | %s%n", idPeminjaman,
                anggota.getNama(), buku.getJudul(), tanggalPinjam, status);
    }
}
