package org.example;

import org.bson.Document;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class View extends JFrame implements ActionListener, MouseListener{

    private final JTextField txtOrderID;
    private final JTextField txtCusID;
    private final JTextField txtCusName;
    private final JTextField txtInDate;
    private final JTextField txtOutDate;
    private final JTextField txtOrderDate;
    private final JTextField txtTax;
    private final JTextField txtRoomCode;
    private final JTextField txtDiscount;
    private final JTextField txtUnitPrice;
    private final JTextField txtTotal;
    private final JButton btnAdd;
    private final JButton btnDelete;
    private final JButton btnUpdate;
    private final JButton btnSearch;
    private final JButton btnSort;
    private final JTextField txtSearch;
    private final DefaultTableModel mdlTable;
    private final JTable tblTable;
    private final TableRowSorter tbrsorter;
    private final JTextField txtService;
    private final Services services;
    private final DBConnect dbConnect = new DBConnect();
    private final JButton btnEmpty;
    private boolean isConfirmUpdate = true;

    public View() {
        super("Oders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pnlCenter = new JPanel();
        JPanel pnlSouth = new JPanel();
        JPanel pnlNorth = new JPanel();
//        JPanel pnlC = new JPanel();
//        pnlC.setLayout(new BorderLayout());

        // Phần Nhập Thông tin

        pnlNorth.setLayout(new GridLayout(6, 2, 15, 5));
        pnlNorth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Thông Tin", TitledBorder.LEFT,TitledBorder.TOP),new EmptyBorder(20,20,20,20)));

        // Kích thước cố định cho JLabel và JTextField
        Dimension labelSize = new Dimension(120, 30); // Chiều rộng 120, chiều cao 30 cho JLabel
        Dimension textFieldSize = new Dimension(350, 20); // Chiều rộng 150, chiều cao 30 cho JTextField

        // Field Mã Hoá Đơn
        JPanel pnlOrderID = new JPanel();
        pnlOrderID.setLayout(new BoxLayout(pnlOrderID, BoxLayout.X_AXIS));
        JLabel lblOrderID = new JLabel("Mã Hoá Đơn: ");
        lblOrderID.setPreferredSize(labelSize);
        lblOrderID.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlOrderID.add(lblOrderID);
        pnlOrderID.add(Box.createHorizontalStrut(5));
        txtOrderID = new JTextField();
        txtOrderID.setPreferredSize(textFieldSize);
        txtOrderID.setMaximumSize(textFieldSize);
        pnlOrderID.add(txtOrderID);
        pnlOrderID.add(Box.createHorizontalGlue());

        // Field Mã Khách Hàng
        JPanel pnlCusID = new JPanel();
        pnlCusID.setLayout(new BoxLayout(pnlCusID, BoxLayout.X_AXIS));
        JLabel lblCusID = new JLabel("Mã Khách Hàng: ");
        lblCusID.setPreferredSize(labelSize);
        lblCusID.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlCusID.add(lblCusID);
        pnlCusID.add(Box.createHorizontalStrut(5));
        txtCusID = new JTextField();
        txtCusID.setPreferredSize(textFieldSize);
        txtCusID.setMaximumSize(textFieldSize);
        pnlCusID.add(txtCusID);
        pnlCusID.add(Box.createHorizontalGlue());

        // Field Tên Khách Hàng
        JPanel pnlCusName = new JPanel();
        pnlCusName.setLayout(new BoxLayout(pnlCusName, BoxLayout.X_AXIS));
        JLabel lblCusName = new JLabel("Tên Khách Hàng: ");
        lblCusName.setPreferredSize(labelSize);
        lblCusName.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlCusName.add(lblCusName);
        pnlCusName.add(Box.createHorizontalStrut(5));
        txtCusName = new JTextField();
        txtCusName.setPreferredSize(textFieldSize);
        txtCusName.setMaximumSize(textFieldSize);
        pnlCusName.add(txtCusName);
        pnlCusName.add(Box.createHorizontalGlue());

        // Field Ngày Check in
        JPanel pnlInDate = new JPanel();
        pnlInDate.setLayout(new BoxLayout(pnlInDate, BoxLayout.X_AXIS));
        JLabel lblInDate = new JLabel("Ngày Vào: ");
        lblInDate.setPreferredSize(labelSize);
        lblInDate.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlInDate.add(lblInDate);
        pnlInDate.add(Box.createHorizontalStrut(5));
        txtInDate = new JTextField();
        txtInDate.setPreferredSize(textFieldSize);
        txtInDate.setMaximumSize(textFieldSize);
        pnlInDate.add(txtInDate);
        pnlInDate.add(Box.createHorizontalGlue());

        // Field Ngày Check out
        JPanel pnlOutDate = new JPanel();
        pnlOutDate.setLayout(new BoxLayout(pnlOutDate, BoxLayout.X_AXIS));
        JLabel lblOutDate = new JLabel("Ngày Ra: ");
        lblOutDate.setPreferredSize(labelSize);
        lblOutDate.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlOutDate.add(lblOutDate);
        pnlOutDate.add(Box.createHorizontalStrut(5));
        txtOutDate = new JTextField();
        txtOutDate.setPreferredSize(textFieldSize);
        txtOutDate.setMaximumSize(textFieldSize);
        pnlOutDate.add(txtOutDate);
        pnlOutDate.add(Box.createHorizontalGlue());

        // Field Ngày Lập Hoá Đơn
        JPanel pnlOrderDate = new JPanel();
        pnlOrderDate.setLayout(new BoxLayout(pnlOrderDate, BoxLayout.X_AXIS));
        JLabel lblOrderDate = new JLabel("Ngày lập Hoá Đơn: ");
        lblOrderDate.setPreferredSize(labelSize);
        lblOrderDate.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlOrderDate.add(lblOrderDate);
        pnlOrderDate.add(Box.createHorizontalStrut(5));
        txtOrderDate = new JTextField();
        txtOrderDate.setPreferredSize(textFieldSize);
        txtOrderDate.setMaximumSize(textFieldSize);
        pnlOrderDate.add(txtOrderDate);
        pnlOrderDate.add(Box.createHorizontalGlue());

        // Field Thuế
        JPanel pnlTax = new JPanel();
        pnlTax.setLayout(new BoxLayout(pnlTax, BoxLayout.X_AXIS));
        JLabel lblTax = new JLabel("Thuế: ");
        lblTax.setPreferredSize(labelSize);
        lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlTax.add(lblTax);
        pnlTax.add(Box.createHorizontalStrut(5));
        txtTax = new JTextField();
        txtTax.setPreferredSize(textFieldSize);
        txtTax.setMaximumSize(textFieldSize);
        pnlTax.add(txtTax);
        pnlTax.add(Box.createHorizontalGlue());

        // Field Mã Phòng
        JPanel pnlRoomCode = new JPanel();
        pnlRoomCode.setLayout(new BoxLayout(pnlRoomCode, BoxLayout.X_AXIS));
        JLabel lblRoomCode = new JLabel("Mã Phòng: ");
        lblRoomCode.setPreferredSize(labelSize);
        lblRoomCode.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlRoomCode.add(lblRoomCode);
        pnlRoomCode.add(Box.createHorizontalStrut(5));
        txtRoomCode = new JTextField();
        txtRoomCode.setPreferredSize(textFieldSize);
        txtRoomCode.setMaximumSize(textFieldSize);
        pnlRoomCode.add(txtRoomCode);
        pnlRoomCode.add(Box.createHorizontalGlue());

        // Field Đơn giá
        JPanel pnlUnitPrice = new JPanel();
        pnlUnitPrice.setLayout(new BoxLayout(pnlUnitPrice, BoxLayout.X_AXIS));
        JLabel lblUnitPrice = new JLabel("Đơn Giá: ");
        lblUnitPrice.setPreferredSize(labelSize);
        lblUnitPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlUnitPrice.add(lblUnitPrice);
        pnlUnitPrice.add(Box.createHorizontalStrut(5));
        txtUnitPrice = new JTextField();
        txtUnitPrice.setPreferredSize(textFieldSize);
        txtUnitPrice.setMaximumSize(textFieldSize);
        pnlUnitPrice.add(txtUnitPrice);
        pnlUnitPrice.add(Box.createHorizontalGlue());

        // Field Giảm giá
        JPanel pnlDiscount = new JPanel();
        pnlDiscount.setLayout(new BoxLayout(pnlDiscount, BoxLayout.X_AXIS));
        JLabel lblDiscount = new JLabel("Giảm Giá: ");
        lblDiscount.setPreferredSize(labelSize);
        lblDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlDiscount.add(lblDiscount);
        pnlDiscount.add(Box.createHorizontalStrut(5));
        txtDiscount = new JTextField();
        txtDiscount.setPreferredSize(textFieldSize);
        txtDiscount.setMaximumSize(textFieldSize);
        pnlDiscount.add(txtDiscount);
        pnlDiscount.add(Box.createHorizontalGlue());

        // Field Tổng tiền
        JPanel pnlTotal = new JPanel();
        pnlTotal.setLayout(new BoxLayout(pnlTotal, BoxLayout.X_AXIS));
        JLabel lblTotal = new JLabel("Tổng tiền: ");
        lblTotal.setPreferredSize(labelSize);
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlTotal.add(lblTotal);
        pnlTotal.add(Box.createHorizontalStrut(5));
        txtTotal = new JTextField();
        txtTotal.setPreferredSize(textFieldSize);
        txtTotal.setMaximumSize(textFieldSize);
        pnlTotal.add(txtTotal);
        pnlTotal.add(Box.createHorizontalGlue());

        // Field Dịch Vụ
        JPanel pnlService = new JPanel();
        pnlService.setLayout(new BoxLayout(pnlService, BoxLayout.X_AXIS));
        JLabel lblSerVice = new JLabel("Dịch Vụ: ");
        lblSerVice.setPreferredSize(labelSize);
        lblSerVice.setHorizontalAlignment(SwingConstants.RIGHT);
        pnlService.add(lblSerVice);
        pnlService.add(Box.createHorizontalStrut(5));
        txtService = new JTextField();
        txtService.setPreferredSize(textFieldSize);
        txtService.setMaximumSize(textFieldSize);
        pnlService.add(txtService);
        pnlService.add(Box.createHorizontalGlue());

        // add pnlNorth
        pnlNorth.add(pnlOrderID);
        pnlNorth.add(pnlCusID);
        pnlNorth.add(pnlCusName);
        pnlNorth.add(pnlInDate);
        pnlNorth.add(pnlOutDate);
        pnlNorth.add(pnlOrderDate);
        pnlNorth.add(pnlTax);
        pnlNorth.add(pnlRoomCode);
        pnlNorth.add(pnlDiscount);
        pnlNorth.add(pnlUnitPrice);
        pnlNorth.add(pnlTotal);
        pnlNorth.add(pnlService);
        //----------------------------
        add(pnlNorth, BorderLayout.NORTH);

        // Phần button

        pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.X_AXIS));
        pnlCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Chức Năng",TitledBorder.LEFT,TitledBorder.TOP),new EmptyBorder(0, 0, 0, 0)));
        btnAdd = new JButton("Thêm");
        btnAdd.setBackground(Color.GREEN);
        btnDelete = new JButton("Xoá");
        btnDelete.setBackground(Color.RED);
        btnUpdate = new JButton("Sửa");
        btnUpdate.setBackground(Color.YELLOW);
        btnEmpty = new JButton("Xoá Trắng");
        btnEmpty.setBackground(Color.GRAY);
        JLabel lblSort = new JLabel("Sắp xếp theo ngày lập hoá đơn gần nhất:");
        btnSort = new JButton("Sắp Xếp");
        btnSort.setBackground(Color.PINK);
        btnSearch = new JButton("Tìm Kiếm");
        btnSearch.setBackground(Color.CYAN);
        txtSearch = new JTextField();
        txtSearch.setMaximumSize(textFieldSize);
        pnlCenter.add(Box.createHorizontalStrut(100));
        pnlCenter.add(btnAdd);
        pnlCenter.add(Box.createHorizontalStrut(30));
        pnlCenter.add(btnDelete);
        pnlCenter.add(Box.createHorizontalStrut(30));
        pnlCenter.add(btnUpdate);
        pnlCenter.add(Box.createHorizontalStrut(40));
        pnlCenter.add(btnEmpty);
        pnlCenter.add(Box.createHorizontalStrut(40));
        pnlCenter.add(lblSort);
        pnlCenter.add(Box.createHorizontalStrut(10));
        pnlCenter.add(btnSort);
        pnlCenter.add(Box.createHorizontalStrut(40));
        pnlCenter.add(btnSearch);
        pnlCenter.add(Box.createHorizontalStrut(10));
        pnlCenter.add(txtSearch);
        pnlCenter.add(Box.createHorizontalStrut(100));
        add(pnlCenter, BorderLayout.CENTER);

    // Phần Bảng
        pnlSouth.setLayout(new BorderLayout());
        pnlSouth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Danh Sách Hoá Đơn",TitledBorder.LEFT,TitledBorder.TOP),new EmptyBorder(10,10,10,10)));
        String[] title = {"Mã Hoá Đơn", "Mã Khách Hàng", "Tên Khách Hàng", "Ngày CheckIn", "Ngày Checkout", "Ngày Lập Hoá Đơn", "Thuế", "Mã Phòng", "Đơn giá", "Giảm Giá", "Dịch Vụ", "Tổng Tiền"};
        mdlTable = new DefaultTableModel(title,0);
        tblTable = new JTable(mdlTable);

        tblTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,12));
        tbrsorter = new TableRowSorter<TableModel>(mdlTable);
        tblTable.setAutoCreateRowSorter(true);
        tblTable.setRowSorter(tbrsorter);
        tblTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JScrollPane scrTable = new JScrollPane(tblTable);
        scrTable.setPreferredSize(new Dimension(1000,300));
        pnlSouth.add(scrTable);
        add(pnlSouth, BorderLayout.SOUTH);

        services = new Services(dbConnect);

        // Xử lý sự kiện
        tblTable.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEmpty.addActionListener(this);
        btnUpdate.addActionListener(this);

        //Phần Hiển thị
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //Làm mới bảng
    private void updateTable(){
        mdlTable.setRowCount(0);
        var orders = services.getAllOrders();
        for (var order : orders) {
            mdlTable.addRow(new Object[]{
                    order.getOrderID(),
                    order.getCustomerID(),
                    order.getCustomerName(),
                    order.getCheckinDate(),
                    order.getCheckoutDate(),
                    order.getOrderDate(),
                    order.getTax(),
                    order.getRoomCode(),
                    order.getUnitPrice(),
                    order.getDiscount(),
                    order.getService(),
                    order.getTotalPrice()
            });
        }
    }

    //Chuyển từ String sang LocalDateTime
    public LocalDateTime convertStringToLocalDateTime(String date) {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeException e){
            JOptionPane.showMessageDialog(this, "Định dạng ngày tháng chưa đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    //tải dữ liệu lên bảng
    public void loadOrdersToTable() {
        List<Orders> orders = services.getAllOrders();

        mdlTable.setRowCount(0);

        for (Orders order : orders) {
            mdlTable.addRow(new Object[]{
                    order.getOrderID(),
                    order.getCustomerID(),
                    order.getCustomerName(),
                    order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    order.getCheckinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    order.getCheckoutDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    order.getTax(),
                    order.getRoomCode(),
                    order.getUnitPrice(),
                    order.getDiscount(),
                    order.getService(),
                    order.getTotalPrice()
            });
        }
    }
    //Lấy dữ liệu của dòng trong bảng
    public void getDataOrders(){
        int rowSelected = tblTable.convertRowIndexToModel(tblTable.getSelectedRow());
        this.txtOrderID.setText(this.mdlTable.getValueAt(rowSelected, 0).toString());
        this.txtCusID.setText(this.mdlTable.getValueAt(rowSelected, 1).toString());
        this.txtCusName.setText(this.mdlTable.getValueAt(rowSelected, 2).toString());
        this.txtOrderDate.setText(this.mdlTable.getValueAt(rowSelected, 3).toString());
        this.txtInDate.setText(this.mdlTable.getValueAt(rowSelected, 4).toString());
        this.txtOutDate.setText(this.mdlTable.getValueAt(rowSelected, 5).toString());
        this.txtTax.setText(this.mdlTable.getValueAt(rowSelected, 6).toString());
        this.txtRoomCode.setText(this.mdlTable.getValueAt(rowSelected, 7).toString());
        this.txtUnitPrice.setText(this.mdlTable.getValueAt(rowSelected, 8).toString());
        this.txtDiscount.setText(this.mdlTable.getValueAt(rowSelected, 9).toString());
        this.txtService.setText(this.mdlTable.getValueAt(rowSelected, 10).toString());
        this.txtTotal.setText(this.mdlTable.getValueAt(rowSelected, 11).toString());
    }

    //Nút thêm order
    public void addOrders() {
        if (this.txtOrderID.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this,"Mã hoá đơn không được để trống","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else {
            String orderID = txtOrderID.getText();
            String cusID = txtCusID.getText();
            String cusName = txtCusName.getText();
            LocalDateTime orderDate = convertStringToLocalDateTime(this.txtOrderDate.getText());
            LocalDateTime inDate = convertStringToLocalDateTime(this.txtInDate.getText());
            LocalDateTime outDate = convertStringToLocalDateTime(this.txtOutDate.getText());
            int tax = Integer.parseInt(txtTax.getText());
            String roomCode = txtRoomCode.getText();
            Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int discount = Integer.parseInt(txtDiscount.getText());
            Double total = Double.parseDouble(txtTotal.getText());
            List<String> service = (List<String>) Arrays.asList(txtService.getText().split(","));

            try {
                Orders orders = new Orders(orderID, cusID, cusName, orderDate, inDate, outDate, tax, roomCode, unitPrice, discount, total, service);
                services.addOrder(orders);
                loadOrdersToTable();
                JOptionPane.showMessageDialog(this,"Thêm hoá đơn thành công!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Thêm hoá đơn không thành công!","Lỗi",JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        }
    }

    //nút xoá
    public void deleteOrders() {
        int rowSelected = tblTable.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this,"Vui lòng chọn hoá đơn cần xoá","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else{
            int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xoá?","Xác nhận",JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String orderID = tblTable.getValueAt(rowSelected, 0).toString();
                services.deleteOrder(orderID);
                JOptionPane.showMessageDialog(this,"Xoá thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        updateTable();
    }

    //xoá trắng
    public void emptyDelete(){
        txtOrderID.setText("");
        txtCusID.setText("");
        txtCusName.setText("");
        txtOrderDate.setText("");
        txtInDate.setText("");
        txtOutDate.setText("");
        txtTax.setText("");
        txtRoomCode.setText("");
        txtUnitPrice.setText("");
        txtDiscount.setText("");
        txtTotal.setText("");
        txtService.setText("");
        tblTable.clearSelection();
    }

    //Cập nhật
    public void updateOrders() {
        int rowSelected = tblTable.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this,"Chọn dòng cần sửa","Lỗi",JOptionPane.ERROR_MESSAGE);
        } else {
            String orderID = txtOrderID.getText();
            String cusID = txtCusID.getText();
            String cusName = txtCusName.getText();
            LocalDateTime orderDate = convertStringToLocalDateTime(this.txtOrderDate.getText());
            LocalDateTime inDate = convertStringToLocalDateTime(this.txtInDate.getText());
            LocalDateTime outDate = convertStringToLocalDateTime(this.txtOutDate.getText());
            int tax = Integer.parseInt(txtTax.getText());
            String roomCode = txtRoomCode.getText();
            Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int discount = Integer.parseInt(txtDiscount.getText());
            Double total = Double.parseDouble(txtTotal.getText());
            List<String> service = (List<String>) Arrays.asList(txtService.getText().split(","));

            try {
                Orders orders = new Orders(orderID, cusID, cusName, orderDate, inDate, outDate, tax, roomCode, unitPrice, discount, total, service);
                services.updateOrder(orderID, orders);
                loadOrdersToTable();
                JOptionPane.showMessageDialog(this,"Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e){
                JOptionPane.showMessageDialog(this,"Sửa không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o == btnAdd){
            addOrders();
        } else if (o == btnDelete){
            deleteOrders();
            tblTable.clearSelection();
        } else if (o == btnEmpty){
            emptyDelete();
        } else if (o == btnUpdate) {
            if(isConfirmUpdate){
                txtOrderID.setEnabled(false);
                txtCusID.setEnabled(false);
                isConfirmUpdate = false;
                btnUpdate.setText("Xong");
            } else{
                txtOrderID.setEnabled(true);
                txtCusID.setEnabled(true);
                updateOrders();
                isConfirmUpdate = true;
                btnUpdate.setText("Sửa");
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.getDataOrders();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
