package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Bucket;
import DTO.Product;

public class ShopDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

	public Connection open() throws Exception {
		Connection conn = null;
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(JDBC_URL, "test", "test1234");

		return conn;
	}

	// 이미지 경로 리스트 가져오기
	public String shoppinglist(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Product> linklist = new ArrayList<>();

		try {
			Connection conn = open();
			String sql = "select img_name, product_id, product_name, product_price from product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product img = new Product();
				img.setImg_name(rs.getString(1));
				img.setProduct_id(rs.getInt(2));
				img.setProduct_name(rs.getString(3));
				img.setProduct_price(rs.getString(4));
				linklist.add(img);
			}
			request.setAttribute("linklist", linklist);
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "shopping.jsp";
	}
	
	public String infopage(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Product> linklist = new ArrayList<>();
		int product_id = Integer.parseInt(request.getParameter("product_id"));

		try {
			Connection conn = open();
			String sql = "insert into bucket values (?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, product_id);
			ps.executeUpdate();
		
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/shop";
	}
	
	public String bucketlist(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Bucket> buckets = new ArrayList<>();
		
		try {
			Connection conn = open();
			String sql = "select a.img_name, a.product_name, a.product_price, a.product_id from product a join bucket b on (a.product_id = b.product_id)";
//			sql += " from product a join bucket b on (a.product_id = b.product_id)";
//			sql += " group by a.img_name, a.product_id, a.product_name, a.product_price";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Bucket bucket = new Bucket();
				bucket.setImg_name(rs.getString(1));
				bucket.setProduct_name(rs.getString(2));
				bucket.setProduct_price(rs.getString(3));
				bucket.setProduct_id(rs.getInt(4));
				
				buckets.add(bucket);
			}
			request.setAttribute("buckets", buckets);
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bucket.jsp";
	}
	
	public int delete(String[] de_box) {
		int res = 0;
		int[] cnt = null;
		
		try {
			Connection conn = open();
			conn.setAutoCommit(false);
			PreparedStatement ps = null;
			String sql = "delete from bucket where product_id = ?";
			ps = conn.prepareStatement(sql);
			
			for (int i = 0; i<de_box.length; i++) {
				ps.setInt(1, Integer.parseInt(de_box[i]));
				ps.addBatch();
				ps.clearParameters();
			}
			cnt = ps.executeBatch();
			
			for (int i = 0; i<cnt.length; i++) {
				if (cnt[i]==-2) {
					res++;
				}
			}
			
			if (de_box.length==res) {
				conn.commit();
			} else {
				conn.rollback();
			}
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return res;
	}
}
