package controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import model.Player;

@Controller
@RequestMapping("/mechanics")
public class GameMechanics {
	
	@Autowired
	Player player;
	
	@GetMapping("/scene/setup")
	@ResponseBody
	public String getSceneSetup(HttpServletRequest request) {
		String rencontre = "";
		String exemple ="";
		if(player.peutRencontrer()) {
			Random r = new Random();
			rencontre = "\"encounter\": ["+r.nextInt(9)+","+(r.nextInt(4)+5)+"],";
		}
		
		int scene = player.getIdScene();
		System.out.println("Setup de scene");
		System.out.println("player scene is : "+scene);
		if(scene == 1) {
			rencontre = "\"encounter\": [],";
			String interaction = "[{\"pos\" : [1,3], \"html\" : \"<script>function test() {alert('tu veux choisir monstre 1 ?')}</script>\" }]";
			exemple = "{\"id\" : 2, \"nowalk\" : {\"0\":[0,1,2,3,4,5,6,7,8]}, \"triggers\" : {"+rencontre+" \"interact\" : "+interaction+"}, \"startpos\" : [5,9], \"background\" : \"assets/img/fond2.png\"}";
		}else {
			String interaction = "[{\"pos\" : [2,2], \"event_type\" : \"move\", \"html\" : \"<button onclick='test()'><script>function test() {console.log('bravo')}</script>test</button>\" }]";
			exemple = "{\"id\" : 1, \"nowalk\" : {\"4\":[0,1,2,3,4,5,6,7]}, \"triggers\" : {"+rencontre+" \"interact\" : "+interaction+"}, \"startpos\" : [1,1], \"background\" : \"\"}";
		}
		

		return exemple;
	}
	
	@GetMapping("/select")
	public String getSelectMenu() {
		System.out.println("Go select");
		return "selectMonster";
	}
	
}
