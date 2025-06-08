package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Borrowing;
import utils.DBConnection;
import utils.DateUtils;

public class BorrowingController {

    public static boolean pinjamBuku(String userId, String bookId) {
        String sql = "INSERT INTO peminjaman (id_user, id_buku, tanggal_pinjam, tanggal_jatuh_tempo, jumlah, status, akses_habis) VALUES (?, ?, ?, ?, ?, ?, ?)";
        LocalDate start = DateUtils.today();
        LocalDate due = DateUtils.daysFromNow(7);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setString(2, bookId);
            stmt.setDate(3, Date.valueOf(start));
            stmt.setDate(4, Date.valueOf(due));
            stmt.setInt(5, 1);
            stmt.setString(6, "Dipinjam");
            stmt.setInt(7, 0);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Borrowing> getRiwayat(String userId) {
        List<Borrowing> list = new ArrayList<>();
        String sql;

        if (userId == null || userId.isEmpty()) {
            sql = "SELECT * FROM peminjaman";
        } else {
            sql = "SELECT * FROM peminjaman WHERE id_user = ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (userId != null && !userId.isEmpty()) {
                stmt.setString(1, userId);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Borrowing b = new Borrowing(
                        rs.getInt("id_peminjaman"),
                        rs.getString("id_user"),
                        rs.getString("id_buku"),
                        rs.getDate("tanggal_pinjam").toLocalDate(),
                        rs.getDate("tanggal_jatuh_tempo").toLocalDate(),
                        rs.getInt("jumlah"),
                        rs.getString("status"),
                        rs.getBoolean("akses_habis")
                );
                list.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Borrowing> getAllBorrowings() {
        return getRiwayat("");
    }

    public static boolean deleteBorrowing(int idPeminjaman) {
        String sql = "DELETE FROM peminjaman WHERE id_peminjaman = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPeminjaman);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
