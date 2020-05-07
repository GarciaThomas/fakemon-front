package controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mechanics")
public class GameMechanics {
	
	
	
	@GetMapping("/scene/setup")
	@ResponseBody
	public String getSceneSetup(
			//@PathVariable(required = false) int[] playerPos
			) {
		
		Random r = new Random();
		String interaction = "[{\"pos\" : [2,2], \"html\" : \"<button onclick='test()'><script>function test() {console.log('bravo')}</script>test</button>\" }]";
		String exemple = "{\"id\" : 1, \"nowalk\" : {\"4\":[0,1,2,3,4,5,6,7]}, \"triggers\" : {\"encounter\": ["+r.nextInt(9)+","+(r.nextInt(4)+5)+"], \"interact\" : "+interaction+"}, \"startpos\" : [1,1], \"background\" : \"U/R/L\"}";
		return exemple;
	}
	
	@GetMapping("/select")
	public String getSelectMenu() {
		System.out.println("Go select");
		return "selectMonster";
	}
	
}
