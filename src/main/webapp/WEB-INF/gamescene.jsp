<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selection</title>

<script>
$(document).ready(function(){
	updatePlayer()
	var tailleCase = 40
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
		encounterX = ${encounter.x}
		encounterY = ${encounter.y}
		posY = parseInt(avatar.attr("posY"))
		posX = parseInt(avatar.attr("posX"))
		console.log({lastY: posY,lastX: posX,status:"passe"})
		if(encounterX == x && encounterY == y){
			$.ajax({
				type:"POST",
				url:'${pageContext.request.contextPath}/selection',
				//data:"newSession="+true,
				data : {"lastY": posY,"lastX": posX,"status":"passe"},
				success: function(resp){
					$("#scene").html(resp)
					//window.location.href='${pageContext.request.contextPath}'+'/selection'
				}
			})
		}
	}
	
	/**
	*	Regarde si la position donnee est accessible par l'avatar
	*	@param x,y int,int position sur la grille
	*/
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
	}
	
	function checkTrigger(x,y){
		/**
		*	ToDo : trouver le moyen d'avoir une liste des triggers possible sur le setup et déclencher les bonnes fonctions
		*/
		
		if(x == 3 && y == 3){
			setupObj = {
					background:"tileExt.png",
					y:5,
					x:9
			}
			nextTile(setupObj)
		}
		
	}
	
	function nextTile(setupObj){
		$("#scene").css("background-image","url(${pageContext.request.contextPath}/assets/img/"+setupObj.background+")")
		$("#avatar").css("top",tailleCase*setupObj.y)
		$("#avatar").css("left",tailleCase*setupObj.x)
		$("#avatar").attr("posY",setupObj.y)
		$("#avatar").attr("posX",setupObj.x)
	}
	
	function updatePlayer(){
		$.ajax({
			type:"POST",
			data: {"opt":"update"},
			url:'${pageContext.request.contextPath}/playerinterface',
			success: function(resp){
				data = JSON.parse(resp)
				console.log(data)
				posX = data.playerPos[0]
				posY = data.playerPos[1]
				avatarPosition(posX,posY)
				
			}
		})
	}
});

</script>

</head>
<body>
	<div id="game" class="container">
		<div class="row h-100 justify-content-around align-items-center no-gutters">
			<div class="col-4 p-0">
				<div id="scene" style="width:404px;height:404px;border-radius:2px;border:black 2px solid;background-color:white;background-image:url('${pageContext.request.contextPath}/assets/img/fondScene.png');background-size:cover">
					<img
						width="40"
						height="40"
						id="avatar" 
						src="${pageContext.request.contextPath}/assets/img/avatar.png"
						style="position:relative;z-index:2;"
						posX="0"
						posY="0"
					/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>