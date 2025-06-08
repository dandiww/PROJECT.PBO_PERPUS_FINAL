package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controller.BookController;
import model.Book;
import model.User;

public class UserDashboard extends javax.swing.JFrame {
    private User user;
    private JPanel contentPanel;
    private JPanel booksGridPanel;
    private JScrollPane bookScrollPane;
    private RiwayatPeminjamanPanel riwayatPanel;
    private JTextField searchField;
    private JButton activeButton = null;

    public UserDashboard(User user) {
        this.user = user;
        setTitle("Booktopia | Selamat datang, " + user.getUsername());
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // SIDEBAR
        JPanel sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 102, 204),
                        0, getHeight(), new Color(60, 110, 170));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(220, getHeight()));
        sidebar.setOpaque(false);

        JLabel logoLabel = new JLabel("Booktopia", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Rockwell", Font.BOLD, 26));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(new EmptyBorder(30, 10, 30, 10));
        sidebar.add(logoLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JButton homeBtn = new JButton("Beranda");
        JButton pinjamanBtn = new JButton("Pinjaman");
        JButton logoutBtn = new JButton("Logout");

        homeBtn.addActionListener(e -> {
            searchField.setText("");
            showBooksGrid();
            setActiveButton(homeBtn);
        });

        pinjamanBtn.addActionListener(e -> {
            showRiwayatPanel();
            setActiveButton(pinjamanBtn);
        });

        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Apakah Anda yakin ingin logout?",
                    "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginView();
            }
        });

        for (JButton btn : new JButton[]{homeBtn, pinjamanBtn, logoutBtn}) {
            styleSidebarButton(btn);
            buttonPanel.add(btn);
            buttonPanel.add(Box.createVerticalStrut(15));
        }

        sidebar.add(buttonPanel, BorderLayout.CENTER);
        add(sidebar, BorderLayout.WEST);

        // TOP PANEL
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        topPanel.setBackground(new Color(0, 102, 204));
        topPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

        searchField = new JTextField(25);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField.setPreferredSize(new Dimension(200, 28));

        JButton searchBtn = new JButton("Cari");
        searchBtn.setBackground(new Color(36, 52, 92));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBtn.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        searchBtn.setOpaque(true);
        searchBtn.addActionListener(e -> showBooksGrid());

        topPanel.add(searchField);
        topPanel.add(searchBtn);
        add(topPanel, BorderLayout.NORTH);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(245, 245, 245));

        booksGridPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        booksGridPanel.setBackground(new Color(245, 245, 245));

        bookScrollPane = new JScrollPane(booksGridPanel);
        bookScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        bookScrollPane.getViewport().setBackground(new Color(245, 245, 245));

        contentPanel.add(bookScrollPane, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        riwayatPanel = new RiwayatPeminjamanPanel(user);

        setActiveButton(homeBtn);
        showBooksGrid();
        setVisible(true);
    }

    private void showBooksGrid() {
        contentPanel.removeAll();
        booksGridPanel.removeAll();

        String keyword = searchField.getText().trim().toLowerCase();
        boolean adaBuku = false;

        for (Book b : BookController.getAllBooks()) {
            if (b.getStok() > 0 && (keyword.isEmpty()
                    || b.getTitle().toLowerCase().contains(keyword)
                    || b.getAuthor().toLowerCase().contains(keyword))) {
                booksGridPanel.add(new BookCard(b, user.getIdUser(), this));
                adaBuku = true;
            }
        }

        if (!adaBuku) {
            booksGridPanel.setLayout(new BorderLayout());
            JLabel kosongLabel = new JLabel("📭 Ups! Kami tidak menemukan buku yang cocok.");
            kosongLabel.setHorizontalAlignment(SwingConstants.CENTER);
            kosongLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            booksGridPanel.add(kosongLabel, BorderLayout.CENTER);
        } else {
            booksGridPanel.setLayout(new GridLayout(0, 2, 20, 20));
        }

        contentPanel.add(bookScrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        SwingUtilities.invokeLater(() -> bookScrollPane.getVerticalScrollBar().setValue(0));
    }

    private void showRiwayatPanel() {
        contentPanel.removeAll();

        JScrollPane scrollPane = new JScrollPane(riwayatPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245));

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void refreshBooks() {
        if (activeButton != null && activeButton.getText().contains("Beranda")) {
            showBooksGrid();
        }
    }

    private void styleSidebarButton(JButton button) {
        Color normalColor = new Color(36, 52, 92);
        Color hoverColor = new Color(0, 153, 0);
        Color textColor = Color.WHITE;

        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(normalColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != activeButton) {
                    button.setBackground(hoverColor);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != activeButton) {
                    button.setBackground(normalColor);
                }
            }
        });
    }

    private void setActiveButton(JButton button) {
        Color normalColor = new Color(36, 52, 92);
        Color activeColor = new Color(0, 153, 0);

        if (activeButton != null) {
            activeButton.setBackground(normalColor);
        }
        activeButton = button;
        activeButton.setBackground(activeColor);
    }
}