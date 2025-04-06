package nhanVien;

import java.util.Objects;

import javax.swing.JTextField;

import org.bson.Document;

import entity.NhanVien;

public class NhanVienMapper {
//	txtMaNV, txtHo, txtTen, txtBoPhan, txtNamSinh, txtNamKinhNghiem, txtLuong, txtHoatDong
	public NhanVien dataToNV(JTextField txtMaNV
			, JTextField txtHo
			, JTextField txtTen
			, JTextField txtBoPhan
			, JTextField txtNamSinh
			, JTextField txtNamKinhNghiem
			, JTextField txtLuong
			, JTextField txtHoatDong
			) {
		return new NhanVien(
				txtMaNV.getText()
				, txtHo.getText()
				, txtTen.getText()
				, txtBoPhan.getText()
				, Integer.parseInt(txtNamSinh.getText())
				, Integer.parseInt(txtNamKinhNghiem.getText())
				, Double.parseDouble(txtLuong.getText())
				, Boolean.parseBoolean(txtHoatDong.getText())
		);
	}
	
	public NhanVien documentToNV(Document doc) {
		if(Objects.isNull(doc))
			return null;
		
		return new NhanVien(
					(String)doc.get("maNV"),
					(String)doc.get("ho"),
					(String)doc.get("ten"),
					(String)doc.get("boPhan"),
					Integer.parseInt(doc.get("namSinh").toString()),
					Integer.parseInt(doc.get("namKinhNghiem").toString()),
					Double.parseDouble(doc.get("luong").toString()),
					Boolean.parseBoolean(doc.get("hoatDong").toString())
				);
	}
	
	public Document nvToDocument(NhanVien nv) {
		return new Document("maNV", nv.getMaNV())
				.append("ho", nv.getHo())
				.append("ten", nv.getTen())
				.append("boPhan", nv.getBoPhan())
				.append("namSinh", nv.getNamSinh())
				.append("namKinhNghiem", nv.getNamKinhNghiem())
				.append("luong", nv.getLuong())
				.append("hoatDong", nv.isHoatDong());
	}
	
	public Object[] nvToObjectArray(NhanVien nv) { 	
		Object[] objs = {
				nv.getMaNV(),
				nv.getHo(),
				nv.getTen(),
				nv.getBoPhan(),
				nv.getNamSinh(),
				nv.getNamKinhNghiem(),
				nv.getLuong(),
				nv.isHoatDong()
		};
		
		return objs;
	}
}
