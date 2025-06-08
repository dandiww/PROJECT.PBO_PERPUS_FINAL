package model;

import java.time.LocalDate;

public class Borrowing {
    private int idPeminjaman;           
    private String userId;
    private String bookId;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalJatuhTempo;
    private int jumlah;
    private String status;
    private boolean aksesHabis;

    // Constructor lengkap
    public Borrowing(int idPeminjaman, String userId, String bookId, LocalDate tanggalPinjam,
                     LocalDate tanggalJatuhTempo, int jumlah, String status, boolean aksesHabis) {
        this.idPeminjaman = idPeminjaman;
        this.userId = userId;
        this.bookId = bookId;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalJatuhTempo = tanggalJatuhTempo;
        this.jumlah = jumlah;
        this.status = status;
        this.aksesHabis = aksesHabis;
    }

    // Getter methods
    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalJatuhTempo() {
        return tanggalJatuhTempo;
    }

    public int getJumlah() {
        return jumlah;
    }

    public String getStatus() {
        return status;
    }

    public boolean getAksesHabis() {
        return aksesHabis;
    }

    // Optional helper method untuk cek expired
    public boolean isExpired() {
        return tanggalJatuhTempo.isBefore(LocalDate.now());
    }
}
