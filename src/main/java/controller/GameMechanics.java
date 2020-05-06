package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mechanics")
public class GameMechanics {
	
	
	@GetMapping("/scene/{id}")
	public String getScene(@PathVariable int id) {
		String exemple = "{ 'nowalk' : {'4':[0,1,2,3,4,5,6,7]}, 'triggers' : {'encounter': [1,1], 'interact' : {}, 'startpos' : [1,1], 'background' : 'U/R/L'}";
		return "va retourner un objetJson concernant la scene";
	}
	
	
	@GetMapping("/combat/attaque")
	public String combat() {
		
		/**
		 * Reçoit un json avec id monstre attaquant, id montre defenseur
		 * id de l'attaque si action joueur
		 */
		
		/**
		 * Choix de la voie à suivre si joueur ou bot
		 * Si joueur action de combat avec id attaque joueur
		 * Si bot action de combat avec selection automatique
		 */
		
		/**
		 * Retourner les data des monstres a jour
		 * 
		 * Doit contenir : 
		 * 	- objet monstre du jueur
		 *  - objet monstre bot
		 *  - combat terminé (vrai/faux)
		 *  - un message (si pas de message, attribut vide tout simplement
		 */
		
		return null;
	}
	
	/***
	 * Doit permettre la capture du monstre et renvoyer un json statuant la fin du combat,
	 * le message de capture
	 * @return
	 */
	@GetMapping("/combat/capture")
	public String captureMonster() {
		return null;
	}
}
