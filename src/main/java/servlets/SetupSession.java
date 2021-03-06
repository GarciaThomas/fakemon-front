package servlets;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Context;
import model.Monster;
import model.Player;

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
		request.getServletContext().getRequestDispatcher("/WEB-INF/combat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Passe dans le setup Session");
		
		HttpSession ses = request.getSession(false);
		System.out.println("nouveau ? "+ses.isNew());
		if(request.getSession().getAttribute("endFight") != null) {
			Boolean context = (Boolean) request.getSession().getAttribute("endFight");
			System.out.println("Jai un context ?");
			if(context) {
				Monster choixJoueur = Player.getInstance().getEquipePlayer().get(Integer.valueOf((String) request.getParameter("mstrId")));
				request.getSession().setAttribute("attaquant", choixJoueur);
			}else {
				request.getSession().invalidate();
				List<Monster> monstresPossibles = Context.getInstance()
											.getMonstresProposition();
				Monster choixJoueurNewCombat = Player.getInstance().getEquipePlayer().get(Integer.valueOf((String) request.getParameter("mstrId")));
				monstresPossibles.remove(Integer.valueOf((String) request.getParameter("mstrId")).intValue());
				Random r = new Random();
				
				Monster adverse = Player.getInstance().rencontreSauvage();
				request.getSession().setAttribute("endFight", true);
				request.getSession().setAttribute("attaquant", choixJoueurNewCombat);
				request.getSession().setAttribute("adversaire", adverse);
				request.getSession().setAttribute("playerTurn", true);					
			}
		}else {
			request.getSession();
			List<Monster> monstresPossibles = Context.getInstance()
										.getMonstresProposition();
			Monster choixJoueurNewCombat = Player.getInstance().getEquipePlayer().get(Integer.valueOf((String) request.getParameter("mstrId")));
			monstresPossibles.remove(Integer.valueOf((String) request.getParameter("mstrId")).intValue());
			Random r = new Random();
			//adverse = monstresPossibles.get(r.nextInt(monstresPossibles.size()));
			Monster adverse = Player.getInstance().rencontreSauvage();
			/**/request.getSession().setAttribute("endFight", true);
			request.getSession().setAttribute("attaquant", choixJoueurNewCombat);
			request.getSession().setAttribute("adversaire", adverse);
			request.getSession().setAttribute("playerTurn", true);	
		}
		
		doGet(request, response);
	}

}
