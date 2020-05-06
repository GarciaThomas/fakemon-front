package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Player;
@Controller
@RequestMapping("/player")
public class PlayerMechanics {
	
	@PostMapping("/posupdate")
	public void playerInfos(@RequestParam int x, @RequestParam int y) {
		Player.getInstance().setPosition(new int[]{x,y});
	}

}
