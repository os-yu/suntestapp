package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserAccount account = new UserAccount();
		AccountDAO dao = new AccountDAO();

		account.setId(request.getParameter("id"));
		account.setPass(request.getParameter("pass"));

		if(dao.check(account)){
			HttpSession session = request.getSession(true);
			session.setAttribute("id",request.getParameter("id"));
			session.setAttribute("clist",dao.selectAllUsers());

			this.getServletContext().getRequestDispatcher("/customertop.jsp").forward(request,response);
		}
		else{
			this.getServletContext().getRequestDispatcher("/loginfalse.jsp").forward(request,response);
		}
	}

}
