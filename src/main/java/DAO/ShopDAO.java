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

//		System.out.println("s.executeQuery();) : ");
			while (rs.next()) {
				Product img = new Product();
				img.setImg_name(rs.getString(1));
				img.setProduct_id(rs.getInt(2));
				img.setProduct_name(rs.getString(3));
				img.setProduct_price(rs.getString(4));
//				System.out.println("rs.next() : " + rs.getString(1));
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
			String sql = "select a.img_name, a.product_name, a.product_price from product a join bucket b on (a.product_id = b.product_id)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//			System.out.println("try문");
			while (rs.next()) {
				Bucket bucket = new Bucket();
				bucket.setImg_name(rs.getString(1));
				bucket.setProduct_name(rs.getString(2));
				bucket.setProduct_price(rs.getString(3));
				
//				System.out.println("while문");
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
	
//	public String insert (HttpServletRequest request, HttpServletResponse response) {
//		return "";
//	}
	
}
