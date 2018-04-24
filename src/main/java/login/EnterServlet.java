package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnterServlet
 */
@WebServlet("/EnterServlet")
public class EnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/enter.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserAccount account = new UserAccount();
		AccountDAO dao = new AccountDAO();
		String[] birthtmp = {"","",""};
		String birth = "";

		birthtmp[0] = request.getParameter("year");
		birthtmp[1] = request.getParameter("month");
		birthtmp[2] = request.getParameter("day");

		birth = birthtmp[0] + "年" + birthtmp[1] + "月" + birthtmp[2] + "日";

		account.setId(request.getParameter("id"));
		account.setPass(request.getParameter("pass"));
		account.setEmail(request.getParameter("email"));
		account.setBirthday(birth);

		if(dao.insert(account)){
			request.setAttribute("account", account);
			this.getServletContext().getRequestDispatcher("/success.jsp").forward(request,response);
		}
		else{
			String errormsg = "<a style=\"color:red\">エラー！登録できませんでした。<br>ユーザー名が重複しています。</a>";
			request.setAttribute("msg", errormsg);
			this.getServletContext().getRequestDispatcher("/enter.jsp").forward(request,response);
		}
	}

}
