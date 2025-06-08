package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utils.DBConnection;

public class UserController {

    public static User login(String username, String password, String role) {
        String query = "SELECT id_user, username, role FROM users WHERE username = ? AND password = ? AND role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id_user");
                    String uname = rs.getString("username");
                    String r = rs.getString("role");

                    return new User(id, uname, r);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error saat login: " + e.getMessage());
            e.printStackTrace();
        }

        return null; 
    }

//methode
    private String generateNewUserId(Connection conn) throws SQLException {
        String sql = "SELECT id_user FROM users ORDER BY id_user DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("id_user"); 
                int num = Integer.parseInt(lastId.substring(3)); 
                num++;
                return String.format("USR%03d", num);
            } else {
                return "USR001";
            }
        }
    }
    public boolean register(User user) {
        String sql = "INSERT INTO users (id_user, nama, username, password, alamat, jenis_kelamin, tgl_lahir, no_hp, role) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            // Generate id_user baru
            String newId = generateNewUserId(conn);
            user.setIdUser(newId);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getIdUser());
                stmt.setString(2, user.getNama());
                stmt.setString(3, user.getUsername());
                stmt.setString(4, user.getPassword());
                stmt.setString(5, user.getAlamat());
                stmt.setString(6, user.getJenisKelamin());
                stmt.setDate(7, user.getTglLahir());
                stmt.setString(8, user.getNoHp());
                stmt.setString(9, user.getRole());

                int result = stmt.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            System.err.println("Database error saat register: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
