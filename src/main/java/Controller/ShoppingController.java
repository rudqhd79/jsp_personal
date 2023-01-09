package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ShopDAO;

@WebServlet("/")
public class ShoppingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShopDAO dao;
	private ServletContext ctx;

	// DAO 는 싱글톤처럼 한번만 생성되어 공유한다
	@Override
	public void init() throws ServletException {
		super.init();
		dao = new ShopDAO();
		ctx = getServletContext();
	}

	public ShoppingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = "/shop";

		switch (command) {
		case "/shop":
			site = dao.shoppinglist(request, response);
			break;
			
		case "/bucket":
			site = dao.bucketlist(request, response);
			dao.totalprice(request, response);
			break;
			
		case "/bucketinsert":
			site = dao.infopage(request, response);
			break;

		case"/modify":
			site = dao.modify(request, response);
			break;
			
		case"/update":
			site = dao.update(request, response);
			break;
			
		case "/join":
			int result = dao.join(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			if (result == 1) {
				out.println("<script>");
				out.println(" alert('회원가입을 축하합니다!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			} else {
				out.println("<script>");
				out.println(" alert('회원가입실패!'); location.href='" + context + "';");
				out.println("</script>");
				out.flush();
			}
			break;
			
		case "/delete":
			String[] de_box = request.getParameterValues("chk");
			dao = new ShopDAO();
			int res = dao.delete(de_box);
			System.out.println(de_box + "++++ 장바구니 삭제 품목은:  " + res + "개");
		}
		if (site.startsWith("redirect:/")) {
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);
			response.sendRedirect(rview);
		}
		ctx.getRequestDispatcher("/" + site).forward(request, response);
	}

}
