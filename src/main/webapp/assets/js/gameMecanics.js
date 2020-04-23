$(document).ready(function(){
	
	loadStarters();
	
	/**
	* Gestion des mouvements dans la scene
	* @param e event lorsque la touche est relachee
	*/
	$(document).keyup(function(e){
		avatar = $("#avatar")
		posY = parseInt(avatar.attr("posY"))
		posX = parseInt(avatar.attr("posX"))
		if(e.keyCode == 38){
			//haut
			actualY = (posY-1)*20
			if(checkWalk(posX,posY-1)){
				avatar.css("top",actualY+"px")
				avatar.attr("posY",posY-1)
			}
		}else if(e.keyCode==40){
			//bas
			actualY = (posY+1)*20
			if(checkWalk(posX,posY+1)){
				avatar.css("top",actualY+"px")
				avatar.attr("posY",posY+1)
			}
		}else if(e.keyCode == 39){
			//droite
			
			newX = (posX+1) * 20
			if(checkWalk(posX+1,posY)){
				avatar.css("left",newX+"px")
				avatar.attr("posX",posX+1)
			}
		}else if(e.keyCode == 37){
			//gauche
			newX=(posX-1) * 20
			if(checkWalk(posX-1,posY)){
				avatar.css("left",newX+"px")
				avatar.attr("posX",posX-1)
			}
		}
		posY = parseInt(avatar.attr("posY"))
		posX = parseInt(avatar.attr("posX"))
		checkEncounter(posX,posY)
	})
	
	function loadStarters(){

		$.ajax({
			type:"POST",
			url:'${pageContext.request.contextPath}/mechanics',
			data:{"activity":"starterLoad"},
			success: function(reponse){
				console.log(reponse)
			}
		})		

		
	}
	
	/***
	 * TODO aller chercher les rencontres
	 */
	function getEncounters(){
		
	}
	
	/**
	*	Regarde si la position en x,y donnee contient une rencontre
	*	@param x,y int,int position sur la grille
	
	function checkEncounter(x,y){
		encounterX = ${encounter.x}
		encounterY = ${encounter.y}
		if(encounterX == x && encounterY == y){
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/selection',
				data:"newSession="+true,
				success: function(){
					window.location.href='${pageContext.request.contextPath}'+'/selection'
				}
			})
		}
	}
	*/
	/**
	*	Regarde si la position donnee est accessible par l'avatar
	*	@param x,y int,int position sur la grille
	
	function checkWalk(x,y){
		jsonNoWalk = ${noWalk}
		returnBool = true
		if(jsonNoWalk.hasOwnProperty(y)){
			returnBool = $.inArray(x,jsonNoWalk[y])
			if(returnBool === -1){
				returnBool = true
			}else{
				returnBool=false
			}
		}
		if(x == 10 || x < 0 || y == 10 || y < 0){
			returnBool = false
		}

		return returnBool
	}*/
});