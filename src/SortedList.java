public class SortedList {
    private Buku[] data;
    private int ukuran;
    private final int kapasitas;

    public SortedList(int kapasitas) {
        if (kapasitas <= 0) {
            throw new IllegalArgumentException("Kapasitas harus lebih dari 0.");
        }
        this.kapasitas = kapasitas;
        this.data = new Buku[kapasitas];
        this.ukuran = 0;
    }
    public int size() { return ukuran; }

    public int getKapasitas() { return kapasitas; }

    public boolean isEmpty() { return ukuran == 0; }

    public boolean isFull() { return ukuran == kapasitas; }

    public void insert(Buku buku) {
        if (buku == null) throw new IllegalArgumentException("Data tidak boleh null.");
        if (isFull()) throw new IllegalStateException("Sorted List penuh (kapasitas " + kapasitas + ").");
        if (search(buku.getKodeBuku()) != null) {
            throw new IllegalArgumentException("Kode buku " + buku.getKodeBuku() + " sudah ada.");
        }
        int pos = ukuran;
        while (pos > 0 && data[pos - 1].getKodeBuku().compareTo(buku.getKodeBuku()) > 0) {
            data[pos] = data[pos - 1];
            pos--;
        }
        data[pos] = buku;
        ukuran++;
    }

    public Buku remove(String kunci) {
        if (isEmpty()) throw new IllegalStateException("Sorted List kosong.");
        int idx = cariIndex(kunci);
        if (idx < 0) throw new java.util.NoSuchElementException("Kode buku " + kunci + " tidak ditemukan.");
        Buku hapus = data[idx];
        for (int i = idx; i < ukuran - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--ukuran] = null;
        return hapus;
    }

    public Buku search(String kunci) {
        int idx = cariIndex(kunci);
        return idx < 0 ? null : data[idx];
    }

    public Buku get(int index) {
        if (index < 0 || index >= ukuran) {
            throw new IndexOutOfBoundsException("Indeks " + index + " tidak valid (ukuran: " + ukuran + ").");
        }
        return data[index];
    }

    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("(Sorted List kosong)");
            return;
        }
        for (int i = 0; i < ukuran; i++) {
            System.out.printf("[%d] ", i);
            data[i].tampilkanInfo();
        }
    }

    private int cariIndex(String kunci) {
        if (kunci == null) return -1;
        String k = kunci.trim().toUpperCase();
        int low = 0, high = ukuran - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = data[mid].getKodeBuku().compareTo(k);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1; else high = mid - 1;
        }
        return -1;
    }
}
