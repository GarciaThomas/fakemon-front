package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

/**
 * Servlet implementation class GameScene
 */
@WebServlet("/gamescene")
public class GameScene extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameScene() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap encounterPos = new HashMap<String, Integer>();
		
		Random r = new Random();
		encounterPos.put("x",r.nextInt(9));
		encounterPos.put("y",r.nextInt(4)+5);
		
		
		
		String noPasaran = "{4:[0,1,2,3,4,5,6,7]}";
		
		request.setAttribute("noWalk", noPasaran);
		if(Player.getInstance().peutRencontrer()) {
			request.setAttribute("encounter",encounterPos);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/gamescene.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
