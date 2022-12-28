package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;
import DTO.Product;

@WebServlet("/")
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopDAO dao;
	private ServletContext ctx;
       
	//DAO 는 싱글톤처럼 한번만 생성되어 공유한다
    @Override
	public void init() throws ServletException {
		super.init();
		dao = new ShopDAO();
		ctx = getServletContext();
	}

	public ShoppingController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = "/main.jsp";
		
		switch (command) {
		case "/main.jsp" :
			site = "main";
		case "/shop":
			site = dao.shoppinglist(request, response);
			break;
		case "/bucket":
			site = dao.bucketlist(request, response);
			break;
		case "/bucketinsert":
			site = dao.infopage(request, response); 
			break;
//		case "/insert":
//			site = dao.insert(request, response);
//			break;
//		case "/add":
//			site = dao.nextCustid(request, response);
//			try {
//				System.out.println("dao.link();");
//				 dao.link();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;
		}
		if (site.startsWith("redirect:/")) {
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		} else {
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		}
	}

}
