package view;

import controller.UserController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegisterView extends JFrame {
    private JTextField namaField, usernameField, alamatField, noHpField, tglLahirField;
    private JPasswordField passwordField;
    private JComboBox<String> genderBox;
    private JComboBox<String> roleBox;
    private JButton registerButton, loginButton;

    public RegisterView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Register - Booktopia");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 1;

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
        rbc.insets = new Insets(10, 10, 10, 10);
        rbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Buat Akun Baru");
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
        rbc.gridx = 0;
        rightPanel.add(new JLabel("Nama Lengkap:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        namaField = new JTextField(20);
        rightPanel.add(namaField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Username:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        usernameField = new JTextField(20);
        rightPanel.add(usernameField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Password:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(20);
        rightPanel.add(passwordField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Alamat:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        alamatField = new JTextField(20);
        rightPanel.add(alamatField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Jenis Kelamin:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        genderBox = new JComboBox<>(new String[]{"Laki-laki", "Perempuan"});
        genderBox.setBackground(Color.WHITE);
        rightPanel.add(genderBox, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Tanggal Lahir (yyyy-MM-dd):"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        tglLahirField = new JTextField(20);
        rightPanel.add(tglLahirField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Nomor HP:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        noHpField = new JTextField(20);
        rightPanel.add(noHpField, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.anchor = GridBagConstraints.LINE_END;
        rightPanel.add(new JLabel("Role:"), rbc);
        rbc.gridx = 1;
        rbc.anchor = GridBagConstraints.LINE_START;
        roleBox = new JComboBox<>(new String[]{"user"});
        roleBox.setEnabled(false);
        roleBox.setBackground(Color.LIGHT_GRAY);
        rightPanel.add(roleBox, rbc);

        rbc.gridy++;
        rbc.gridx = 0;
        rbc.gridwidth = 2;
        rbc.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("Daftar");
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.setBackground(new Color(0, 153, 76));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setFocusPainted(false);
        rightPanel.add(registerButton, rbc);

        rbc.gridy++;
        JButton loginButton = new JButton("Sudah punya akun? Masuk di sini");
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        loginButton.setForeground(new Color(0, 102, 204));
        loginButton.setBackground(new Color(245, 245, 245));
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightPanel.add(loginButton, rbc);

        gbcMain.gridx = 0;
        mainPanel.add(leftPanel, gbcMain);
        gbcMain.gridx = 1;
        mainPanel.add(rightPanel, gbcMain);

        add(mainPanel);

        registerButton.addActionListener(this::performRegister);

        loginButton.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });

        setVisible(true);
    }

    private void performRegister(ActionEvent e) {
        try {
            String nama = namaField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String alamat = alamatField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String tglLahirStr = tglLahirField.getText().trim();
            String noHp = noHpField.getText().trim();
            String role = (String) roleBox.getSelectedItem();

            if (nama.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama, Username, dan Password wajib diisi.");
                return;
            }

            LocalDate tglLahir;
            try {
                tglLahir = LocalDate.parse(tglLahirStr);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Format tanggal lahir salah. Gunakan yyyy-MM-dd.");
                return;
            }

            User user = new User();
            user.setNama(nama);
            user.setUsername(username);
            user.setPassword(password);
            user.setAlamat(alamat);
            user.setJenisKelamin(gender);
            user.setTglLahir(Date.valueOf(tglLahir));
            user.setNoHp(noHp);
            user.setRole(role);

            boolean success = new UserController().register(user);
            if (success) {
                JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.");
                new LoginView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registrasi gagal. Username mungkin sudah terdaftar.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Terjadi error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
