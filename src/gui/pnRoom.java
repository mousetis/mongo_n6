package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import dao.RoomDAO;
import entity.Room;

public class pnRoom extends JPanel implements ActionListener, MouseListener {
    private JButton btnAdd, btnDelete, btnUpdate, btnRefresh, btnFind;
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtRoom, txtTenp, txtLoaiPhong, txtGiap, txtSoluong, txtMess, txtfind;
    private JComboBox<String> TTPCombo, cboFilterLoaiPhong;
    private RoomDAO roomDAO;

    public pnRoom() {
        roomDAO = new RoomDAO();
        setLayout(new BorderLayout()); // Sử dụng BorderLayout làm layout chính

        // Panel chứa bảng (pnds)
        JPanel pnds = new JPanel(new BorderLayout());
        pnds.setBackground(new Color(240, 240, 240));
        pnds.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblds = new JLabel("Quản Lý Phòng Khách sạn", SwingConstants.CENTER);
        lblds.setFont(new Font("Arial", Font.BOLD, 20));
        lblds.setForeground(Color.BLACK);
        pnds.add(lblds, BorderLayout.NORTH);

        String[] columns = {"Mã Phòng", "Tên Phòng", "Loại Phòng", "Gía Phòng", "Tình trạng phòng", "Số lượng người"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        pnds.add(scrollPane, BorderLayout.CENTER);

        add(pnds, BorderLayout.CENTER); // Đặt bảng ở trung tâm để co giãn

        // Panel chứa thông tin và điều khiển
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Panel thông tin (infoPanel)
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(new Color(240, 240, 240));
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblInfoTitle = new JLabel("Thông tin Phòng", SwingConstants.CENTER);
        lblInfoTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        infoPanel.add(lblInfoTitle, gbc);

        // Mã Phòng
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        infoPanel.add(new JLabel("Mã Phòng: "), gbc);
        txtRoom = new JTextField(15);
        txtRoom.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 1;
        infoPanel.add(txtRoom, gbc);

        // Tên Phòng
        gbc.gridx = 2;
        infoPanel.add(new JLabel("Tên phòng: "), gbc);
        txtTenp = new JTextField(15);
        txtTenp.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 3;
        infoPanel.add(txtTenp, gbc);

        // Loại Phòng
        gbc.gridx = 0;
        gbc.gridy = 2;
        infoPanel.add(new JLabel("Loại Phòng: "), gbc);
        txtLoaiPhong = new JTextField(15);
        txtLoaiPhong.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 1;
        infoPanel.add(txtLoaiPhong, gbc);

        // Giá Phòng
        gbc.gridx = 2;
        infoPanel.add(new JLabel("Giá Phòng: "), gbc);
        txtGiap = new JTextField(15);
        txtGiap.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 3;
        infoPanel.add(txtGiap, gbc);

        // Số lượng người
        gbc.gridx = 0;
        gbc.gridy = 3;
        infoPanel.add(new JLabel("Số lượng người: "), gbc);
        txtSoluong = new JTextField(15);
        txtSoluong.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 1;
        infoPanel.add(txtSoluong, gbc);

        // Tình trạng phòng
        gbc.gridx = 2;
        infoPanel.add(new JLabel("Tình trạng phòng: "), gbc);
        TTPCombo = new JComboBox<>(new String[]{"Trống", "Đã Đặt", "Bảo Trì"});
        gbc.gridx = 3;
        infoPanel.add(TTPCombo, gbc);

        // Lọc theo loại phòng
        gbc.gridx = 0;
        gbc.gridy = 4;
        infoPanel.add(new JLabel("Lọc theo loại phòng: "), gbc);
        cboFilterLoaiPhong = new JComboBox<>(new String[]{"Tất cả", "VIP", "Thường", "Deluxe"});
        gbc.gridx = 1;
        infoPanel.add(cboFilterLoaiPhong, gbc);
        cboFilterLoaiPhong.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                filterRoomsByType();
            }
        });

        // Tìm kiếm
        gbc.gridx = 0;
        gbc.gridy = 5;
        infoPanel.add(new JLabel("Tìm kiếm: "), gbc);
        txtfind = new JTextField(15);
        txtfind.setBorder(new LineBorder(Color.BLACK, 2));
        gbc.gridx = 1;
        infoPanel.add(txtfind, gbc);
        btnFind = new JButton("Tìm kiếm");
        btnFind.setBackground(Color.LIGHT_GRAY);
        btnFind.setForeground(Color.BLACK);
        gbc.gridx = 2;
        infoPanel.add(btnFind, gbc);

        // Thông báo lỗi
        txtMess = new JTextField();
        txtMess.setEditable(false);
        txtMess.setBorder(null);
        txtMess.setForeground(Color.red);
        txtMess.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        infoPanel.add(txtMess, gbc);

        bottomPanel.add(infoPanel, BorderLayout.CENTER);

        // Panel điều khiển (controlPanel)
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(new Color(240, 240, 240));
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblControlTitle = new JLabel("Chỉnh sửa", SwingConstants.CENTER);
        lblControlTitle.setFont(new Font("Arial", Font.BOLD, 20));
        controlPanel.add(lblControlTitle);

        btnAdd = new JButton("Thêm");
        btnAdd.setBackground(Color.LIGHT_GRAY);
        btnAdd.setForeground(Color.BLACK);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(btnAdd);

        btnRefresh = new JButton("Làm mới");
        btnRefresh.setBackground(Color.LIGHT_GRAY);
        btnRefresh.setForeground(Color.BLACK);
        btnRefresh.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(btnRefresh);

        btnUpdate = new JButton("Cập nhật");
        btnUpdate.setBackground(Color.LIGHT_GRAY);
        btnUpdate.setForeground(Color.BLACK);
        btnUpdate.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(btnUpdate);

        btnDelete = new JButton("Xóa");
        btnDelete.setBackground(Color.LIGHT_GRAY);
        btnDelete.setForeground(Color.BLACK);
        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(btnDelete);

        bottomPanel.add(controlPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Thêm sự kiện
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnFind.addActionListener(this);
        table.addMouseListener(this);

        loadRooms();
    }

    public void loadRooms() {
        model.setRowCount(0); // Xóa dữ liệu cũ
        List<Room> rooms = roomDAO.getRoomList();
        for (Room room : rooms) {
            model.addRow(new Object[]{
                room.getRoomCode(),
                room.getRoomName(),
                room.getRoomType(),
                String.valueOf(room.getRoomPrice()),
                room.getRoomStatus(),
                String.valueOf(room.getMaxPeople())
            });
        }
    }

    private void clearFields() {
        txtRoom.setText("");
        txtTenp.setText("");
        txtLoaiPhong.setText("");
        txtGiap.setText("");
        txtSoluong.setText("");
        TTPCombo.setSelectedIndex(0);
        txtRoom.requestFocus();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtRoom.setText(table.getValueAt(row, 0).toString());
            txtTenp.setText(table.getValueAt(row, 1).toString());
            txtGiap.setText(table.getValueAt(row, 3).toString());
            TTPCombo.setSelectedItem(table.getValueAt(row, 4).toString());
            txtLoaiPhong.setText(table.getValueAt(row, 2).toString());
            txtSoluong.setText(table.getValueAt(row, 5).toString());
        }
    }

    private void showMessage(String message, JTextField txt) {
        txt.requestFocus();
        txtMess.setText(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            Room room = new Room(txtRoom.getText(), txtTenp.getText(), txtLoaiPhong.getText(),
                    Integer.parseInt(txtGiap.getText()), TTPCombo.getSelectedItem().toString(),
                    Integer.parseInt(txtSoluong.getText()));
            roomDAO.addRoom(room);
            loadRooms();
        } else if (e.getSource() == btnDelete) {
            roomDAO.removeRoom(txtRoom.getText());
            loadRooms();
        } else if (e.getSource() == btnUpdate) {
            Room room = new Room(txtRoom.getText(), txtTenp.getText(), txtLoaiPhong.getText(),
                    Integer.parseInt(txtGiap.getText()), TTPCombo.getSelectedItem().toString(),
                    Integer.parseInt(txtSoluong.getText()));
            roomDAO.updateRoom(txtRoom.getText(), room);
            loadRooms();
        } else if (e.getSource() == btnRefresh) {
            clearFields();
        } else if (e.getSource() == btnFind) {
            String roomCode = txtfind.getText().trim();
            Room room = roomDAO.findRoom(roomCode);
            if (room != null) {
                model.setRowCount(0);
                model.addRow(new Object[]{room.getRoomCode(), room.getRoomName(), room.getRoomType(),
                        room.getRoomPrice(), room.getRoomStatus(), room.getMaxPeople()});
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy phòng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void filterRoomsByType() {
        String selectedType = (String) cboFilterLoaiPhong.getSelectedItem();
        model.setRowCount(0);
        for (Room room : roomDAO.getRoomList()) {
            if ("Tất cả".equals(selectedType) || room.getRoomType().equals(selectedType)) {
                model.addRow(new Object[]{room.getRoomCode(), room.getRoomName(), room.getRoomType(),
                        room.getRoomPrice(), room.getRoomStatus(), room.getMaxPeople()});
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}