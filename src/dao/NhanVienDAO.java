package dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import connectDB.MongoDBConnection;
import entity.NhanVien;
import nhanVien.NhanVienMapper;

public class NhanVienDAO {
	private final MongoCollection<Document> collection = new MongoDBConnection().getCollection();
	private final NhanVienMapper mapper = new NhanVienMapper();
	
	public List<NhanVien> find(String maNV) {
		List<NhanVien> list = new ArrayList<NhanVien>();
		collection.find(Filters.eq("maNV", maNV)).forEach(doc -> list.add(mapper.documentToNV(doc)));
		return list;
		
	}
	
	public List<NhanVien> findAll() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		collection.find().forEach(doc -> list.add(mapper.documentToNV(doc)));
		return list;
	}

	public boolean save(NhanVien nv) {
		collection.insertOne(mapper.nvToDocument(nv));
		return true;
	}
	
	public boolean update(String maNV, NhanVien nv) {
		return collection.updateMany(Filters.eq("maNV", maNV), List.of(
							Updates.set("ho", nv.getHo()),
							Updates.set("ten", nv.getTen()),
							Updates.set("boPhan", nv.getBoPhan()),
							Updates.set("namSinh", nv.getNamSinh()),
							Updates.set("namKinhNghiem", nv.getNamKinhNghiem()),
							Updates.set("luong", nv.getLuong())
							)
						)
						.getModifiedCount() > 0;
	}

	public boolean delete(String maNV) {
		return collection
				.updateOne(
					Filters.eq("maNV", maNV), 
					Updates.set("hoatDong", false))
				.getModifiedCount() > 0;
				
	}
}