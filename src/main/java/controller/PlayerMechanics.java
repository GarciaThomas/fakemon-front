package controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Player;
@Controller
@RequestMapping("/player")
public class PlayerMechanics {
	@Autowired
	Player player;
	
	@PostMapping("/posupdate")
	@ResponseBody
	public boolean playerInfos(@RequestParam int x, @RequestParam int y, @RequestParam int scene) {
		player.setIdScene(scene);
		player.setPosition(new int[]{x,y});
		return true;
	}
	
	@GetMapping("/infos")
	@ResponseBody
	public String getPlayerInfos() {
		return "{ \"playerPos\" : ["+player.getPosition()[0]+","+player.getPosition()[1]+"]}";
	}
	
	@GetMapping("/infosTest")
	@ResponseBody
	public String getPlayerInfosTest() {
		ObjectMapper om = new ObjectMapper();
		String playerInfos ="";
		try {
			 playerInfos = om.writeValueAsString(player);
			System.out.println(playerInfos);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playerInfos;
	}
	
	@GetMapping("/heal")
	public void healSquad() {
		player.soinEquipeJoueur();
	}
	
	@GetMapping("/squad")
	@ResponseBody
	public String getSquad() {
		int size = player.getEquipePlayer().size();
		if(size == 0) {
			player.addEquipePlayer(player.rencontreSauvage());
		}
		//Gson gson = new Gson();
		
		ObjectMapper om = new ObjectMapper();
		
		String jsonToReturn = "";
		try {
			jsonToReturn = om.writeValueAsString(player.getEquipePlayer());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonToReturn;
		
	}
	
	
}
