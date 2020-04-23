package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Context;
import model.Monster;

/**
 * Servlet implementation class SetupSession
 */
@WebServlet("/setupsession")
public class SetupSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("the get setup");
		System.out.println(request.getSession().getAttribute("adversaire"));
		request.getServletContext().getRequestDispatcher("/WEB-INF/combat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		System.out.println(request.getSession().isNew());
		List<Monster> monstresPossibles = Context.getInstance()
									.getMonstresProposition();
		Monster choixJoueur = monstresPossibles.get(Integer.valueOf((String) request.getParameter("mstrId")));
		monstresPossibles.remove(Integer.valueOf((String) request.getParameter("mstrId")).intValue());
		Random r = new Random();
		Monster adverse = monstresPossibles.get(r.nextInt(monstresPossibles.size()));
		System.out.println(adverse);
		request.getSession().setAttribute("endFight", false);
		request.getSession().setAttribute("attaquant", choixJoueur);
		request.getSession().setAttribute("adversaire", adverse);
		request.getSession().setAttribute("playerTurn", true);
		doGet(request, response);
	}

}
