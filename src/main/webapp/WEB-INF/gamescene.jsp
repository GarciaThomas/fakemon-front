<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selection</title>

<script>
$(document).ready(function(){
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
	
	/**
	*	Regarde si la position en x,y donnee contient une rencontre
	*	@param x,y int,int position sur la grille
	*/
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
});

</script>

</head>
<body>
	<div id="game" class="container">
		<div class="row h-100 justify-content-around align-items-center">
			<div class="col-4 p-0">
				<div id="scene" style="overflow:hidden;width:200px;height:200px;border-radius:2px;border:black 2px solid;background-color:white;background-image:url('${pageContext.request.contextPath}/assets/img/fondScene.png')">
					<div style="z-index:1;position:absolute;top:180px;width:20px;height:20px;background-color:green;"></div>
					<img
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