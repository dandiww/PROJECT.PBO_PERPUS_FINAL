package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import controller.BookController;
import controller.BorrowingController;
import model.Book;
import model.Borrowing;

public class AdminDashboard extends JFrame {
    private JPanel sidebarPanel;
    private JPanel mainPanel;
    private BookListPanel bookListPanel;
    private BorrowingListPanel borrowingListPanel;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        sidebarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(180, 180, 200),
                        0, getHeight(), new Color(130, 100, 180));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebarPanel.setPreferredSize(new Dimension(250, getHeight()));
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        JLabel greetingLabel = new JLabel("Hallo Admin");
        greetingLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JButton tambahBookButton = createSidebarButton("Tambah Buku");
        tambahBookButton.addActionListener(e -> tambahBukuAction());

        JButton logoutButton = createSidebarButton("Logout");
        logoutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Yakin ingin logout?", "Konfirmasi Logout",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginView().setVisible(true);
            }
        });

        JButton editBookButton = createSidebarButton("Edit Buku");
        editBookButton.addActionListener(e -> editBookAction());

        JButton deleteBookButton = createSidebarButton("Hapus Buku");
        deleteBookButton.addActionListener(e -> deleteBookAction());

        JButton deleteBorrowingButton = createSidebarButton("Hapus Peminjaman");
        deleteBorrowingButton.addActionListener(e -> deleteBorrowingAction());

        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(tambahBookButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(editBookButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(deleteBookButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(deleteBorrowingButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(logoutButton);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        refreshMainPanel();
        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(180, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBackground(new Color(50, 50, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }

    private void tambahBukuAction() {
        TambahBukuForm formPanel = new TambahBukuForm(this);
        showFormInMainPanel(formPanel);
    }

    private void editBookAction() {
        EditBookHandler.editBook(this, bookListPanel);
    }

    private void deleteBookAction() {
        Book selected = bookListPanel.getSelectedBook();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus buku ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (BookController.deleteBook(selected.getIdBuku())) {
                JOptionPane.showMessageDialog(this, "Buku berhasil dihapus.");
                bookListPanel.reloadData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus buku.");
            }
        }
    }

    private void deleteBorrowingAction() {
        Borrowing selected = borrowingListPanel.getSelectedBorrowing();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Pilih data peminjaman yang ingin dihapus.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus peminjaman ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (BorrowingController.deleteBorrowing(selected.getIdPeminjaman())) {
                JOptionPane.showMessageDialog(this, "Data peminjaman berhasil dihapus.");
                borrowingListPanel.reloadData();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus peminjaman.");
            }
        }
    }

    public void refreshMainPanel() {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        bookListPanel = new BookListPanel();
        borrowingListPanel = new BorrowingListPanel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.6;
        mainPanel.add(new JScrollPane(bookListPanel), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.4;
        mainPanel.add(new JScrollPane(borrowingListPanel), gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showFormInMainPanel(JPanel formPanel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminDashboard::new);
    }
}
