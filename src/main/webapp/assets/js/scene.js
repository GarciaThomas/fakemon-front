$(document).ready(function(){

	updatePlayer()
	var tailleCase = 40
	var scene = {}
	updatePlayer()
	sceneSetup()
	/**
	* Gestion des mouvements dans la scene
	* @param e event lorsque la touche est relachee
	*/
	$(document).keyup(function(e){
		if(e.keyCode == 27){
			console.log("Prochain emplacement menu")
		}else{
			avatar = $("#avatar")
			posY = parseInt(avatar.attr("posY"))
			posX = parseInt(avatar.attr("posX"))
			if(e.keyCode == 38){
				//haut
				actualY = (posY-1)*tailleCase
				if(checkWalk(posX,posY-1)){
					avatar.css("top",actualY+"px")
					avatar.attr("posY",posY-1)
				}
			}else if(e.keyCode==40){
				//bas
				actualY = (posY+1)*tailleCase
				if(checkWalk(posX,posY+1)){
					avatar.css("top",actualY+"px")
					avatar.attr("posY",posY+1)
				}
			}else if(e.keyCode == 39){
				//droite
				
				newX = (posX+1) * tailleCase
				if(checkWalk(posX+1,posY)){
					avatar.css("left",newX+"px")
					avatar.attr("posX",posX+1)
				}
			}else if(e.keyCode == 37){
				//gauche
				newX=(posX-1) * tailleCase
				if(checkWalk(posX-1,posY)){
					avatar.css("left",newX+"px")
					avatar.attr("posX",posX-1)
				}
			}
			posY = parseInt(avatar.attr("posY"))
			posX = parseInt(avatar.attr("posX"))
			checkEncounter(posX,posY)
			checkTrigger(posX,posY)
		}
	})
	
	function avatarPosition(x,y){
		
		avatar = $("#avatar")
		avatar.css("left",(x*tailleCase)+"px")
		avatar.css("top",(y*tailleCase)+"px")
		avatar.attr("posX",x)
		avatar.attr("posY",y)
	}
	
	/**
	*	Regarde si la position en x,y donnee contient une rencontre
	*	@param x,y int,int position sur la grille
	*/
	function checkEncounter(x,y){
		encounterX = scene.triggers.encounter[0]
		encounterY = scene.triggers.encounter[1]
		posY = parseInt(avatar.attr("posY"))
		posX = parseInt(avatar.attr("posX"))
		
		if(encounterX == x && encounterY == y){
			updatePlayerInfos()
			selectMonsterMenu();
		}
	}
	
	function updatePlayerInfos(){
		console.log("updating")
		$.ajax({
			type:'POST',
			url:'player/posupdate',
			data:{'y':parseInt($("#avatar").attr("posY")),'x':parseInt($("#avatar").attr("posX")),'scene' : scene.id}
		})
	}
	
	function selectMonsterMenu(){
		console.log("selecting")
		$.ajax({
			
			type:"GET",
			url:'mechanics/select',
			success: function(resp){
				//console.log(resp)
				$("#scene").html(resp)
			}
			
		})
	}

	
	/**
	*	Regarde si la position donnee est accessible par l'avatar
	*	@param x,y int,int position sur la grille
	*/
	function checkWalk(x,y){
		returnBool = true
		if(scene.nowalk.hasOwnProperty(y)){
			returnBool = $.inArray(x,scene.nowalk[y])
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
	}
	
	function checkTrigger(x,y){
		console.log("trigger")
		inter = scene.triggers.interact[0]
		if(inter.pos[0] == x && inter.pos[1] == y){
			if(inter.event_type == "move"){
				console.log("ok")
				updatePlayerInfos()
				sceneSetup()
			}
		}
		
	}
	
	function nextTile(setupObj){
		$("#scene").css("background-image","url(assets/img/"+setupObj.background+")")
		$("#avatar").css("top",tailleCase*setupObj.y)
		$("#avatar").css("left",tailleCase*setupObj.x)
		$("#avatar").attr("posY",setupObj.y)
		$("#avatar").attr("posX",setupObj.x)
	}
	
	function updatePlayer(){
		$.ajax({
			type:"GET",
			data: {"opt":"update"},
			url:'player/infosTest',
			success: function(resp){
				data = JSON.parse(resp)
				//console.log(data)
				posX = data.position[0]
				posY = data.position[1]
				avatarPosition(posX,posY)
				
			}
		})
	}
	
	
	function sceneSetup(){
		$.ajax({
			type:"GET",
			url:'/fakemon-front/mechanics/scene/setup',
			success:function(resp){
				data = JSON.parse(resp)
				console.log(data)
				scene = data
				$("#scene").css("background-image","url("+scene.background+")")
				$("#scene").css("background-color","none")
			}
		});
		
		$.ajax({
			type:"GET",
			url:'player/infosTest',
			success:function(resp){
				data = JSON.parse(resp)
				console.log(scene.id+" : "+data.idScene)
				if(scene.id != data.idScene){
					console.log("onUpdate boiii")
					updatePlayerInfos()
					posX = scene.startpos[0]
					posY = scene.startpos[1]
					avatarPosition(posX,posY)
				}else{
					console.log("moving boiii")
					updatePlayerInfos()
				}
			}
		});
	}
	
	btnHeal = $("#healBtn")
	
	function heal(){
		$.ajax({
			type:"POST",
			url:'/mechanics',
			data:{"activity":"heal"}
		})
	}
});