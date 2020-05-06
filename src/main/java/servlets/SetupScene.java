package servlets;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

/**
 * Servlet implementation class SetupScene
 */
//@WebServlet("/scenesetup")
public class SetupScene extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupScene() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Random r = new Random();
	
		String noPasaran = "{\"4\":[0,1,2,3,4,5,6,7]}";
		
		request.setAttribute("noWalk", noPasaran);

		
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"noWalk\": ").append(noPasaran).append(",\"encounter\":{\"x\":").append(r.nextInt(9)).append(",\"y\":").append(r.nextInt(4)+5).append("}");
		sb.append("}");
		
		response.getWriter().write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
