package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmHome extends JFrame implements ActionListener {
    private JMenuItem menuItemExit;
    private JPanel panelImage;
    private JLabel lblStoreImage;

    public frmHome() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Giao diện trang chủ");
        this.setSize(1550, 850);
        this.setMinimumSize(new Dimension(800, 600)); 
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Header
        JLabel lblHeader = new JLabel("Hệ Thống Quản Lý Khách sạn - Nhân Viên");
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setBackground(Color.DARK_GRAY);
        lblHeader.setOpaque(true);
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setFont(new Font("Times New Roman", Font.PLAIN, 27));
        add(lblHeader, BorderLayout.NORTH);

        // Panel chứa các menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS)); // Sử dụng BoxLayout thay vì FlowLayout
        menuPanel.setBackground(Color.LIGHT_GRAY);

        // System Menu
        menuPanel.add(createMenuPanel("Hệ thống", "img\\icons8-system-50.png", createSystemMenu()));
        // Management Menu
        menuPanel.add(createMenuPanel("Quản lí", "img\\icons8-manager-50.png", createManagementMenu()));
        // Process Menu
        menuPanel.add(createMenuPanel("Xử lý", "img\\icons8-process-50_1.png", createProcessMenu()));
        // Report Menu
        menuPanel.add(createMenuPanel("Báo cáo", "img\\icons8-report-40.png", createReportMenu()));
        // Help Menu
        menuPanel.add(createMenuPanel("Trợ giúp", "img\\icons8-help-50.png", createHelpMenu()));

        // Clock
        JLabel lblClock = new JLabel("");
        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
        lblClock.setFont(new Font("Times New Roman", Font.BOLD, 30));
        Timer clockTimer = new Timer(1000, e -> {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            lblClock.setText(timeFormat.format(new Date()));
        });
        clockTimer.start();
        JPanel clockPanel = new JPanel();
        clockPanel.add(lblClock);
        menuPanel.add(Box.createHorizontalGlue()); // Đẩy đồng hồ sang bên phải
        menuPanel.add(clockPanel);

        add(menuPanel, BorderLayout.NORTH); // Đặt menuPanel ở phía trên

        // Panel hình ảnh
        panelImage = new JPanel(new CardLayout());
        ImageIcon originalIcon = new ImageIcon("img\\giaodienchinh.jpg");
        lblStoreImage = new JLabel();
        updateImageSize(originalIcon); // Cập nhật kích thước hình ảnh ban đầu
        panelImage.add(lblStoreImage, "home");
        add(panelImage, BorderLayout.CENTER); // Đặt panelImage ở trung tâm để co giãn tốt hơn

        // Thêm ComponentListener để cập nhật kích thước hình ảnh khi cửa sổ thay đổi
        panelImage.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateImageSize(originalIcon);
            }
        });

        this.setVisible(true);
    }

    // Phương thức cập nhật kích thước hình ảnh
    private void updateImageSize(ImageIcon originalIcon) {
        if (originalIcon != null) {
            int width = panelImage.getWidth();
            int height = panelImage.getHeight();
            if (width > 0 && height > 0) { // Đảm bảo kích thước hợp lệ
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblStoreImage.setIcon(new ImageIcon(scaledImage));
            }
        }
    }

    private JPanel createMenuPanel(String title, String iconPath, JMenu menu) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel(new ImageIcon(iconPath)));
        JMenuBar menuBar = new JMenuBar();
        menu.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        menuBar.add(menu);
        panel.add(menuBar);
        return panel;
    }

    private JMenu createSystemMenu() {
        JMenu menuSystem = new JMenu("Hệ thống");
        JMenuItem menuItemHome = new JMenuItem("Trang chủ", new ImageIcon("img\\icons8-home-32.png"));
        menuItemHome.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuItemHome.addActionListener(e -> {
            CardLayout cl = (CardLayout) panelImage.getLayout();
            cl.show(panelImage, "home");
        });
        menuSystem.add(menuItemHome);

        menuItemExit = new JMenuItem("Thoát", new ImageIcon("img\\icons8-exit-24.png"));
        menuItemExit.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuItemExit.addActionListener(this);
        menuSystem.add(menuItemExit);

        return menuSystem;
    }

    private JMenu createManagementMenu() {
        JMenu menuManagement = new JMenu("Quản lí");
        JMenuItem menuItemRoom = new JMenuItem("Phòng", new ImageIcon("img\\icons8-home-32.png"));
        menuItemRoom.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuItemRoom.addActionListener(e -> {
            panelImage.removeAll();
            pnRoom room = new pnRoom();
            room.setPreferredSize(new Dimension(panelImage.getWidth(), panelImage.getHeight()));
            panelImage.add(room);
            panelImage.revalidate();
            panelImage.repaint();
        });
        menuManagement.add(menuItemRoom);

        JMenu menuProductSupplier = new JMenu("Dịch vụ");
        menuProductSupplier.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuManagement.add(menuProductSupplier);

        JMenuItem menuItemProduct = new JMenuItem("Sản phẩm", new ImageIcon("img\\icons8-product-32.png"));
        menuItemProduct.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuProductSupplier.add(menuItemProduct);

        JMenuItem menuItemSupplier = new JMenuItem("Nhà cung cấp", new ImageIcon("img\\icons8-supplier-32.png"));
        menuItemSupplier.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuProductSupplier.add(menuItemSupplier);

        JMenuItem menuItemEmployee = new JMenuItem("Nhân Viên", new ImageIcon("img\\icons8-employee-32.png"));
        menuItemEmployee.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuItemEmployee.addActionListener(e -> {
            panelImage.removeAll();
            QuanLyNV nv = new QuanLyNV();
            nv.setPreferredSize(new Dimension(panelImage.getWidth(), panelImage.getHeight()));
            panelImage.add(nv);
            panelImage.revalidate();
            panelImage.repaint();
        });
        menuManagement.add(menuItemEmployee);

        JMenuItem menuItemCustomer = new JMenuItem("Khách hàng", new ImageIcon("img\\icons8-customer-32.png"));
        menuItemCustomer.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuManagement.add(menuItemCustomer);

        return menuManagement;
    }

    private JMenu createProcessMenu() {
        JMenu menuProcess = new JMenu("Xử lý");
        JMenuItem menuItemCart = new JMenuItem("Đặt phòng", new ImageIcon("img\\icons8-shopping-cart-32.png"));
        menuItemCart.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuProcess.add(menuItemCart);

        JMenuItem menuItemPayment = new JMenuItem("Quản lí hóa đơn", new ImageIcon("img\\icons8-pay-32.png"));
        menuItemPayment.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuProcess.add(menuItemPayment);

        return menuProcess;
    }

    private JMenu createReportMenu() {
        JMenu menuReport = new JMenu("Báo cáo");
        JMenuItem menuItemYearlyRevenue = new JMenuItem("Doanh thu theo năm", new ImageIcon("img\\icons8-report-32.png"));
        menuItemYearlyRevenue.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuReport.add(menuItemYearlyRevenue);

        JMenuItem menuItemProductRevenue = new JMenuItem("Doanh thu theo SP", new ImageIcon("img\\icons8-product-knowledge-32.png"));
        menuItemProductRevenue.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuReport.add(menuItemProductRevenue);

        return menuReport;
    }

    private JMenu createHelpMenu() {
        JMenu menuHelp = new JMenu("Trợ giúp");
        JMenuItem menuItemFeedback = new JMenuItem("Hướng dẫn sử dụng", new ImageIcon("img\\icons8-customer-feedback-32.png"));
        menuItemFeedback.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        menuHelp.add(menuItemFeedback);

        return menuHelp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItemExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new frmHome();
    }
}