package nhanVien;

import java.util.List;

import dao.NhanVienDAO;
import entity.NhanVien;


public class NhanVienService {
	NhanVienDAO nhanVienDAO = new NhanVienDAO();
	List<NhanVien> nhanViens;
	NhanVienMapper nhanVienMapper = new NhanVienMapper();
	
	public NhanVien addNV(NhanVien nv) {
		nhanVienDAO.save(nv);
		return nv;
	}
	
	public List<NhanVien> getAll() {
		return nhanVienDAO.findAll();
	}
	
	public NhanVien get(String maNV) {
		List<NhanVien> list = nhanVienDAO.find(maNV);
		if(list.size() == 0)
			return null;
		
		return list.get(0);
	}
	
	public boolean update(String maNV, NhanVien updateData) { 
		NhanVien nv = get(maNV);
		return nhanVienDAO.update(maNV, updateData);
	}
	
	public void delete(String maNV) {
		nhanVienDAO.delete(maNV);
	}
}
