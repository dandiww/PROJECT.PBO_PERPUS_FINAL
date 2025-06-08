package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.UserController;
import model.User;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;
    private JButton loginButton;

    public LoginView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login - Booktopia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), getHeight(), new Color(0, 51, 102)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(700, 800));

        JLabel iconLabel = new JLabel("📚");
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 80));
        iconLabel.setForeground(Color.WHITE);

        JLabel textLabel = new JLabel("Booktopia");
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        textLabel.setForeground(Color.WHITE);

        JPanel branding = new JPanel(new GridBagLayout());
        branding.setOpaque(false);
        GridBagConstraints bgbc = new GridBagConstraints();
        bgbc.gridy = 0;
        branding.add(iconLabel, bgbc);
        bgbc.gridy = 2;
        branding.add(textLabel, bgbc);

        leftPanel.add(branding);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(245, 245, 245));
        rightPanel.setPreferredSize(new Dimension(700, 800));
        GridBagConstraints rbc = new GridBagConstraints();
        rbc.insets = new Insets(15, 15, 15, 15);
        rbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Selamat Datang Kembali!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(33, 37, 41));
        rbc.gridx = 0;
        rbc.gridy = 0;
        rbc.gridwidth = 2;
        rbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(welcomeLabel, rbc);

        rbc.gridwidth = 1;
        rbc.anchor = GridBagConstraints.LINE_END;
        rbc.gridy++;
        JLabel usernameLabel = new JLabel("Username:");
        rightPanel.add(usernameLabel, rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        usernameField = new JTextField(20);
        rightPanel.add(usernameField, rbc);

        rbc.gridx = 0;
        rbc.gridy++;
        rbc.anchor = GridBagConstraints.LINE_END;
        JLabel passwordLabel = new JLabel("Password:");
        rightPanel.add(passwordLabel, rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(20);
        rightPanel.add(passwordField, rbc);

        rbc.gridx = 0;
        rbc.gridy++;
        rbc.anchor = GridBagConstraints.LINE_END;
        JLabel roleLabel = new JLabel("Role:");
        rightPanel.add(roleLabel, rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        roleBox = new JComboBox<>(new String[]{"Admin", "User"});
        roleBox.setBackground(Color.WHITE);
        rightPanel.add(roleBox, rbc);

        rbc.gridx = 0;
        rbc.gridy++;
        rbc.gridwidth = 2;
        rbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Masuk");
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setBackground(new Color(0, 153, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        rightPanel.add(loginButton, rbc);

        rbc.gridy++;
        JButton registerButton = new JButton("Belum punya akun? Daftar di sini");
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        registerButton.setForeground(new Color(0, 102, 204));
        registerButton.setBackground(new Color(245, 245, 245));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rightPanel.add(registerButton, rbc);

        gbc.gridx = 0;
        mainPanel.add(leftPanel, gbc);
        gbc.gridx = 1;
        mainPanel.add(rightPanel, gbc);

        add(mainPanel);

        loginButton.addActionListener(this::performLogin);

        registerButton.addActionListener(e -> {
            dispose(); 
            new RegisterView().setVisible(true); 
        });

        setVisible(true);
    }

    private void performLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = ((String) roleBox.getSelectedItem()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password tidak boleh kosong",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserController userController = new UserController();
        User user = userController.login(username, password, role);

        if (user != null) {
            dispose();
            if (role.equalsIgnoreCase("admin")) {
                System.out.println("Login sebagai Admin berhasil.");
                new AdminDashboard().setVisible(true);
            } else {
                System.out.println("Login sebagai User berhasil: " + user.getUsername());
                new UserDashboard(user).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username, password, atau role salah",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginView::new);
    }
}
