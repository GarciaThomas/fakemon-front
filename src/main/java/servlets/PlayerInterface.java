package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

/**
 * Servlet implementation class PlayerInterface
 */
@WebServlet("/playerinterface")
public class PlayerInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayerInterface() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("opt");
		System.out.println(option);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		switch(option) {
		case "new":
			Player.getInstance().setPosition(new int[]{0,0});
			sb.append("\"playerPos\":[").append(Player.getInstance().getPosition()[0]).append(",").append(Player.getInstance().getPosition()[1]).append("]");
			break;
		case "update":
			sb.append("\"playerPos\":[").append(Player.getInstance().getPosition()[0]).append(",").append(Player.getInstance().getPosition()[1]).append("]");
			break;
		default:
			Player.getInstance().setPosition(new int[]{0,0});
			sb.append("\"playerPos\":[").append(Player.getInstance().getPosition()[0]).append(",").append(Player.getInstance().getPosition()[1]).append("]");
			break;
		}
		
		sb.append("}");
		response.getWriter().write(sb.toString());
	}

}
