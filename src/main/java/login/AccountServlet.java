package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String id = request.getParameter("id");
		AccountDAO dao = new AccountDAO();
		UserAccount account = dao.selectId(id);
		if(account != null){
			request.setAttribute("account", account);
			this.getServletContext().getRequestDispatcher("/customerdetail.jsp").forward(request, response);
		}
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		AccountDAO dao = new AccountDAO();
		HttpSession session = request.getSession(true);
		UserAccount account = new UserAccount();
		if(code.equals("update")){
			String[] birthtmp = {"","",""};
			String birth = "";

			birthtmp[0] = request.getParameter("year");
			birthtmp[1] = request.getParameter("month");
			birthtmp[2] = request.getParameter("day");

			birth = birthtmp[0] + "年" + birthtmp[1] + "月" + birthtmp[2] + "日";

			account.setId(request.getParameter("id"));
			account.setEmail(request.getParameter("email"));
			account.setBirthday(birth);

			if(dao.update(account)){
				request.setAttribute("account", account);
				this.getServletContext().getRequestDispatcher("/customerdetail.jsp").forward(request, response);
			}else{
				account = dao.selectId(request.getParameter("id"));
				String errormsg = "<a style=\"color:red\">エラー！更新できませんでした。</a>";
				request.setAttribute("msg", errormsg);
				if(account != null){
					request.setAttribute("account", account);
					this.getServletContext().getRequestDispatcher("/customerdetail.jsp").forward(request, response);
				}else{
					this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
				}
			}
		}else if(code.equals("del")){
			String id = request.getParameter("id");
			if(id != null && !id.isEmpty()){
				if(dao.delete(id)){
					session.setAttribute("id",request.getParameter("id"));
					session.setAttribute("clist",dao.selectAllUsers());
					this.getServletContext().getRequestDispatcher("/customertop.jsp").forward(request,response);
				}else{
					account = dao.selectId(request.getParameter("id"));
					String errormsg = "<a style=\"color:red\">エラー！更新できませんでした。</a>";
					request.setAttribute("msg", errormsg);
					if(account != null){
						request.setAttribute("account", account);
						this.getServletContext().getRequestDispatcher("/customerdetail.jsp").forward(request, response);
					}else{
						this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
					}
				}
			}
		}else{
			account = dao.selectId(request.getParameter("id"));
			if(account != null){
				request.setAttribute("account", account);
				this.getServletContext().getRequestDispatcher("/customerdetail.jsp").forward(request, response);
			}else{
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
			}
		}
	}

}
