package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Book;
import utils.DBConnection;

public class BookController {

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM buku";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("pengarang"),
                    rs.getString("id_penerbit"),
                    rs.getInt("tahun_terbit"),
                    rs.getString("sinopsis"),
                    rs.getString("foto_path"),
                    rs.getInt("stok")
                );
                book.setFilePath(rs.getString("file_path")); 
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static Book getBookById(String idBuku) {
        String sql = "SELECT * FROM buku WHERE id_buku = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idBuku);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Book book = new Book(
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("pengarang"),
                    rs.getString("id_penerbit"),
                    rs.getInt("tahun_terbit"),
                    rs.getString("sinopsis"),
                    rs.getString("foto_path"),
                    rs.getInt("stok")
                );
                book.setFilePath(rs.getString("file_path")); 
                return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean insertBook(Book book) {
        String sql = "INSERT INTO buku (id_buku, judul, pengarang, tahun_terbit, id_penerbit, sinopsis, foto_path, file_path, stok) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getIdBuku());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setInt(4, book.getTahunTerbit());
            stmt.setString(5, book.getPublisher());
            stmt.setString(6, book.getSynopsis());
            stmt.setString(7, book.getImagePath());
            stmt.setString(8, book.getFilePath()); 
            stmt.setInt(9, book.getStok());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBook(Book book) {
        String sql = "UPDATE buku SET judul = ?, pengarang = ?, tahun_terbit = ?, id_penerbit = ?, sinopsis = ?, foto_path = ?, file_path = ?, stok = ? WHERE id_buku = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getTahunTerbit());
            stmt.setString(4, book.getPublisher());
            stmt.setString(5, book.getSynopsis());
            stmt.setString(6, book.getImagePath());
            stmt.setString(7, book.getFilePath());
            stmt.setInt(8, book.getStok());
            stmt.setString(9, book.getIdBuku());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBook(String idBuku) {
        String sql = "DELETE FROM buku WHERE id_buku = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idBuku);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getLastIdBuku() {
        String sql = "SELECT id_buku FROM buku WHERE id_buku LIKE 'BK%' ORDER BY id_buku DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("id_buku");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
