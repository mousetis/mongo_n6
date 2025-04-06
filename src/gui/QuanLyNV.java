package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entity.NhanVien;
import nhanVien.NhanVienMapper;
import nhanVien.NhanVienService;

public class QuanLyNV extends JPanel implements ActionListener {
	NhanVienService nhanVienService = new NhanVienService();
	NhanVienMapper nhanVienMapper = new NhanVienMapper();
	List<NhanVien> nhanViens;
	
	private final int WIDTH = 900;
	private final int HEIGHT = 500;
	
	private JPanel pNorth, pCenter;
	
	// north
	private JLabel lblMaNV, lblHo, lblTen, lblBoPhan, lblNamSinh, lblNamKinhNghiem, lblLuong, lblHoatDong;
	private JTextField txtMaNV, txtHo, txtTen, txtBoPhan, txtNamSinh, txtNamKinhNghiem, txtLuong, txtHoatDong;
	
	// center
	private JButton btnThem, btnXoaRong, btnXoa, btnSua;
	private JLabel lblTimKiem;
	
	private DefaultTableModel model;
	private JTable table;
	
	public QuanLyNV() {
		
		// north
		pNorth = new JPanel();
		Box horizontal = Box.createHorizontalBox();
		Box left = Box.createVerticalBox();
		Box right = Box.createVerticalBox();
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		
		p1.add(lblMaNV = new JLabel("Mã NV: "));
		lblMaNV.setPreferredSize(new Dimension(100, 20));
		p1.add(txtMaNV = new JTextField(20));
		p2.add(lblHo = new JLabel("Họ: "));
		lblHo.setPreferredSize(new Dimension(100, 20));
		p2.add(txtHo = new JTextField(20));
		p3.add(lblTen = new JLabel("Tên: "));
		lblTen.setPreferredSize(new Dimension(100, 20));
		p3.add(txtTen = new JTextField(20));
		p4.add(lblBoPhan = new JLabel("Bộ phận: "));
		lblBoPhan.setPreferredSize(new Dimension(100, 20));
		p4.add(txtBoPhan = new JTextField(20));
		p5.add(lblNamSinh = new JLabel("Năm sinh: "));
		lblNamSinh.setPreferredSize(new Dimension(100, 20));
		p5.add(txtNamSinh = new JTextField(20));
		
		left.add(p1);
		left.add(Box.createHorizontalStrut(10));
		left.add(p2);
		left.add(p3);
		left.add(p4);
		left.add(p5);
		horizontal.add(left);
		
		p6.add(lblNamKinhNghiem = new JLabel("Năm kinh nghiệm: "));
		lblNamKinhNghiem.setPreferredSize(new Dimension(100, 15));
		p6.add(txtNamKinhNghiem = new JTextField(20));
		p7.add(lblLuong = new JLabel("Lương: "));
		lblLuong.setPreferredSize(new Dimension(100, 15));
		p7.add(txtLuong = new JTextField(20));
		p8.add(lblHoatDong = new JLabel("Hoạt động: "));
		lblHoatDong.setPreferredSize(new Dimension(100, 15));
		p8.add(txtHoatDong = new JTextField(20));
		
		right.add(Box.createVerticalStrut(30));
		right.add(p6);
		right.add(p7);
		right.add(p8);
		horizontal.add(right);
		
		pNorth.add(horizontal);
		pNorth.setBorder(new TitledBorder("Records Editor"));;
		add(pNorth, BorderLayout.NORTH);
		
		// center
		JPanel c1 = new JPanel();
		JPanel c2 = new JPanel();
		pCenter = new JPanel();
		pCenter.setBorder(new TitledBorder("Danh sách các cuốn sách"));
		
		c1.add(btnThem = new JButton("Thêm"));
		btnThem.addActionListener(this);
		c1.add(btnXoaRong = new JButton("Xóa rỗng"));
		btnXoaRong.addActionListener(this);
		c1.add(btnXoa = new JButton("Xóa"));
		btnXoa.addActionListener(this);
		c1.add(btnSua = new JButton("Sửa"));
		btnSua.addActionListener(this);
		c1.add(lblTimKiem = new JLabel("Tìm theo mã sách"));
		
		String str[] = {"1", "2", "3"};
		
		DefaultComboBoxModel<String> cbbModel = new DefaultComboBoxModel<String>(str);
		JComboBox<String> cbb = new JComboBox<String>(cbbModel);
		c1.add(cbb);
		pCenter.add(c1);
		
		Object title[] = {"Mã NV", "Họ", "Tên", "Bộ phận", "Năm sinh", "Năm kinh nghiệm", "Lương", "Trạng thái"};
		model = new DefaultTableModel(null, title);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(888, 200));
		c2.add(scroll);
		pCenter.add(c2);
		
		loadData();
		
//		nhanViens = nhanVienService.getAll();
//		for (NhanVien nv : nhanViens) {
//			model.addRow(nhanVienMapper.nvToObjectArray(nv));
//		}
		
		add(pCenter, BorderLayout.CENTER);
		
	}
	
	public void loadData() {
		nhanVienService
			.getAll()
				.forEach(nv ->
					model.addRow(nhanVienMapper.nvToObjectArray(nv)));
	}
	
	public void themNV() {
		if(nhanVienService.get(txtMaNV.getText()) != null)
			return;
		
		NhanVien nv = nhanVienMapper.dataToNV(txtMaNV, txtHo, txtTen, txtBoPhan, txtNamSinh,
				txtNamKinhNghiem, txtLuong, txtHoatDong);
		
		nhanVienService.addNV(nv);
		
		model.addRow(nhanVienMapper.nvToObjectArray(nv));
		loadData();
	}
	
	public void suaNV() {
		String maNV = (String)model.getValueAt(table.getSelectedRow(), 0);
		
		NhanVien nv = nhanVienMapper.dataToNV(txtMaNV, txtHo, txtTen, txtBoPhan, txtNamSinh,
				txtNamKinhNghiem, txtLuong, txtHoatDong);
		
		nhanVienService.update(maNV, nv);
		model.removeRow(table.getSelectedRow());
		model.addRow(nhanVienMapper.nvToObjectArray(nhanVienService.get(maNV)));
	}
	
	public void xoaNV() {
		int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa không", "Cảnh báo", JOptionPane.YES_NO_OPTION);
		if(option == JOptionPane.NO_OPTION)
			return;
			
		String maNV = (String)model.getValueAt(table.getSelectedRow(), 0);
		nhanViens.removeIf(
				nv -> nv.getMaNV().equals(maNV)
		);
		
		nhanVienService.delete(maNV);
		model.removeRow(table.getSelectedRow());
		model.addRow(nhanVienMapper.nvToObjectArray(nhanVienService.get(maNV)));
	}
	
	public void xoaRong() {
		txtMaNV.requestFocus(true);
		
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtBoPhan.setText("");
		txtNamSinh.setText("");
		txtNamKinhNghiem.setText("");
		txtLuong.setText("");
		txtHoatDong.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn.equals(btnThem)) {
			themNV();
			
		} else if(btn.equals(btnXoaRong)) {
			xoaRong();
			
		} else if(btn.equals(btnXoa)) {
			xoaNV();
			
		} else if(btn.equals(btnSua)) {
			suaNV();
		} else {
			System.exit(1);
		}
	}
}
