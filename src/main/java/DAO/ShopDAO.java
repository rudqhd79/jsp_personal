package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	public String totalprice(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection conn = open();
			String sql = "select sum(a.product_price) from product a join bucket b on (a.product_id = b.product_id)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				request.setAttribute("total", rs.getInt(1));
			}
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
	
	public String manager(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Product> products = new ArrayList<>();
		
		try {
			Connection conn = open();
			String sql = "select img_name, product_name, product_price, product_id from product";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = new Product();
				product.setImg_name(rs.getString(1));
				product.setProduct_name(rs.getString(2));
				product.setProduct_price(rs.getString(3));
				product.setProduct_id(rs.getInt(4));
				
				products.add(product);
			}
			request.setAttribute("products", products);
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "manager.jsp";
	}
	
	public String info(HttpServletRequest request, HttpServletResponse response) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = open();
			// 수정한 회원 정보를 가져옴
			int product_id = Integer.parseInt(request.getParameter("product_id"));

			String sql = "select img_name, product_id, product_name, product_price";
			sql += " from product where product_id = " + product_id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			Product product = new Product();

			if(rs.next()) {
				product.setImg_name(rs.getString(1));
				product.setProduct_id(rs.getInt(2));
				product.setProduct_name(rs.getString(3));
				product.setProduct_price(rs.getString(4));
			}
			request.setAttribute("product", product);
			request.setAttribute("product_id", product_id);

			conn.close();
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "info.jsp";
	}
	
	public int update(HttpServletRequest request, HttpServletResponse response) {
		PreparedStatement ps = null;
		
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		String product_name = request.getParameter("product_name");
		String product_price = request.getParameter("product_price");
		String img_name = request.getParameter("img_name");
		int result = 0;
		
		try {
			Connection conn = open();
			String sql = "update product set";
			sql += " product_id = ?,";
			sql += " product_name = ?,";
			sql += " product_price = ?,";
			sql += " img_name = ?";
			sql += " where product_id = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, product_id);
			ps.setString(2, product_name);
			ps.setString(3, product_price);
			ps.setString(4, img_name);
			
			result = ps.executeUpdate();
			
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
