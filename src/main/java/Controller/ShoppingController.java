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
		case "/delete":
			String[] de_box = request.getParameterValues("chk");
			dao = new ShopDAO();
			int res = dao.delete(de_box);
			System.out.println(res + "++++" + de_box);
			if (res != 0) {
				site = "bucket";
			} else {
				System.out.println("삭제될 데이터 값이 없습니다.");
			}
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
