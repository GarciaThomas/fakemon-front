package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.Monster;
import model.PVException;
import model.Player;

/**
 * Servlet implementation class ActionCombat
 */
@WebServlet("/actioncombat")
public class ActionCombat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionCombat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this.getServletContext().getRequestDispatcher("/WEB-INF/combat.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int action = Integer.valueOf(request.getParameter("atkId"));
		if(action != 100) {
				actionAttaque(request,response);
		}else {
				actionCapture(request,response);
		}
		
	}
	
	protected void actionCapture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Action a = ((Monster) request.getSession().getAttribute("adversaire")).captureMonstreFront();
		if(a.getM() == null) {
			sb.append("\"playerTurn\" : false,\"msg\" : \"").append(a.getMessage()).append("\",\"endFight\" : false, \"status\" : \"capture\"");
			
		}else {
			sb.append("\"playerTurn\" : false,\"msg\" : \"").append(a.getMessage()).append("\",\"endFight\" : true, \"status\" : \"capture\"");
		}
		sb.append("}");
		System.out.println(sb.toString());
		response.getWriter().write(sb.toString());
	}

	protected void actionAttaque(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean playerPlays = Boolean.parseBoolean(request.getSession().getAttribute("playerTurn").toString());
		Monster m1 = null;
		Monster m2 = null;
		int atkId = Integer.valueOf((String) request.getParameter("atkId"));
		
		
		if(playerPlays) {
			m1 = (Monster) request.getSession().getAttribute("attaquant");
			m2 = (Monster) request.getSession().getAttribute("adversaire");
			request.getSession().setAttribute("playerTurn", false);
			request.setAttribute("playerTurn", false);
			
			try {
				Action act = m1.combat(m2,atkId);
				m2 = act.getM();
				sb.append("\"playerTurn\":false,\"pvAdv\":"+m2.getPV()+",\"pvAtk\":"+m1.getPV()+",\"pvMaxAdv\" : "+m2.getPVmax()+",\"pvMaxAtk\" : "+m1.getPVmax());
				sb.append(",\"endFight\":"+false+",\"msg\": \""+act.getMessage()+"\"");
			} catch (PVException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(m1.getPV() > 0)
					m1.getExpGain();
				sb.append("\"playerTurn\":false,\"pvAdv\":"+m2.getPV()+",\"pvAtk\":"+m1.getPV()+",\"pvMaxAdv\" : "+m2.getPVmax()+",\"pvMaxAtk\" : "+m1.getPVmax());
				sb.append(",\"endFight\":"+true+",\"msg\": \"Fin du combat !!\"");
				request.getSession().setAttribute("endFight", true);
				request.getSession().setAttribute("msg", "Fin du combat !!");
			}
		}else {
			m1 = (Monster) request.getSession().getAttribute("adversaire");
			m2 = (Monster) request.getSession().getAttribute("attaquant");
			request.getSession().setAttribute("playerTurn", true);
			request.setAttribute("playerTurn", true);
			try {
				Action act = m1.combat(m2,atkId);
				m2 = act.getM();
				sb.append("\"playerTurn\":true,\"pvAdv\":"+m1.getPV()+",\"pvAtk\":"+m2.getPV()+",\"pvMaxAdv\" : "+m1.getPVmax()+",\"pvMaxAtk\" : "+m2.getPVmax());
				sb.append(",\"endFight\":"+false+",\"msg\": \""+act.getMessage()+"\"");
			} catch (PVException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(m2.getPV() > 0)
					m2.getExpGain();
				sb.append("\"playerTurn\":true,\"pvAdv\":"+m1.getPV()+",\"pvAtk\":"+m2.getPV()+",\"pvMaxAdv\" : "+m1.getPVmax()+",\"pvMaxAtk\" : "+m2.getPVmax());
				sb.append(",\"endFight\":"+true+",\"msg\": \"Fin du combat !!\"");
				
			}
		}
		sb.append(",\"status\" : \"attaque\"}");
		System.out.println(sb.toString());
		response.getWriter().write(sb.toString());
		//doGet(request,response);
	}

}
