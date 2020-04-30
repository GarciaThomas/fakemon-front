package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Monster;
import model.Player;

/**
 * Servlet implementation class Mechanics
 */
@WebServlet("/mechanics")
public class Mechanics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mechanics() {
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
		// TODO Auto-generated method stub
		String meca = (String)request.getParameter("activity");
		System.out.println(meca);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		switch(meca) {
			case "starterLoad":
				StringBuffer startersJson = new StringBuffer();
				ArrayList<String> monstresJson = new ArrayList<String>();
				Player.getInstance().getStarters()
					.forEach(m -> monstresJson.add("{\"nom\": \""+m.getNom()+"\",\"event\":{}}"));
				sb.append("\"starters\" : ["+String.join(",",monstresJson)+"]");
				sb.append(",\"nogo\":{}");
				sb.append(",\"events\":[{\"type\":\"interact\",\"x\":2,\"y\":0}]");
				break;
			case "starterSelected":
				int index = Integer.valueOf((String) request.getAttribute("monsterSelected")).intValue();
				break;
			case "heal":
				System.out.println("healing wounds");
				Player.getInstance().soinEquipeJoueur();
				break;
			case "updateInfos":
				Player.getInstance().setPosition(new int[] {Integer.valueOf(request.getParameter("lastX")),Integer.valueOf(request.getParameter("lastY"))});
				break;
			case "monstersInfos":
				System.out.println("infos des monstres en update");
				Monster atk = (Monster)request.getSession().getAttribute("attaquant");
				Monster adv = (Monster)request.getSession().getAttribute("adversaire");
				Gson gson = new Gson();
				sb.append("\"attaquant\" : ").append(gson.toJson(atk));
				sb.append(",\"adversaire\" : ").append(gson.toJson(adv));
				break;
		
		}
		sb.append("}");
		System.out.println(sb.toString());
		response.getWriter().write(sb.toString());
		//doGet(request, response);
	}

}
