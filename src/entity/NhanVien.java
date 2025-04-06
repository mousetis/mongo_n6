package entity;

import java.util.Objects;

import org.bson.Document;

import com.mongodb.DBObject;

public class NhanVien {
	private String maNV;
	private String ho;
	private String ten;
	private String boPhan;
	private int namSinh;
	private int namKinhNghiem;
	private double luong;
	private boolean hoatDong;
	
	public NhanVien() {
		super();
	}
	
	public NhanVien(String maNV, String ho, String ten, String boPhan, int namSinh, int namKinhNghiem, double luong,
			boolean hoatDong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.boPhan = boPhan;
		this.namSinh = namSinh;
		this.namKinhNghiem = namKinhNghiem;
		this.luong = luong;
		this.hoatDong = hoatDong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(boPhan, ho, luong, maNV, namKinhNghiem, namSinh, ten, hoatDong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return this.getMaNV().equals(other.getMaNV());
	}
	
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", boPhan=" + boPhan + ", namSinh=" + namSinh
				+ ", namKinhNghiem=" + namKinhNghiem + ", luong=" + luong + ", hoatDong=" + hoatDong + "]";
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getBoPhan() {
		return boPhan;
	}

	public void setBoPhan(String boPhan) {
		this.boPhan = boPhan;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public int getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(int namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public boolean isHoatDong() {
		return hoatDong;
	}

	public void setHoatDong(boolean hoatDong) {
		this.hoatDong = hoatDong;
	}

	
}
