package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Context;
import model.Player;

/**
 * Servlet implementation class SelectionMonstres
 */
@WebServlet("/selection")
public class SelectionMonstres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectionMonstres() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		Context.getInstance().rebuildPropositions();
		if(Player.getInstance().getEquipePlayer().size() == 0)
			Player.getInstance().addEquipePlayer(Context.getInstance().getMonstresProposition().get(0));

			Player.getInstance().getEquipePlayer().forEach(System.out::println);
		if(request.getParameter("status").equals("passe")) {
			request.getServletContext().getRequestDispatcher("/WEB-INF/selectMonster.jsp").forward(request, response);
		}else if(request.getParameter("status").equals("passeList")) {
			Gson gson = new Gson();
			String s = gson.toJson(Player.getInstance().getEquipePlayer());
			response.getWriter().write(s);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
