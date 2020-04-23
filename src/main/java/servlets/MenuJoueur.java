package servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Monster;
import model.Player;

/**
 * Servlet implementation class MenuJoueur
 */
@WebServlet("/menujoueur")
public class MenuJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuJoueur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/menuJoueur.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(Player.getInstance().getEquipePlayer());
		/*if(Player.getInstance().getEquipePlayer().size() == 0 || Player.getInstance().getEquipePlayer() == null) {
			Player.getInstance().setEquipePlayer(new LinkedList<Monster>() {{addAll(Player.getInstance().tableRencontre(5, 1));}});
		}*/
		request.getSession().setAttribute("joueur", Player.getInstance().getEquipePlayer());
		doGet(request, response);
	}

}
