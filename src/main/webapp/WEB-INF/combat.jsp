<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Combat</title>
<script>
$(document).ready(function(){
	pvBarPlayMon = $("#progressPlayMon")
	pvPlayMonPrg = ${sessionScope.attaquant.PV}/${sessionScope.attaquant.PVmax}*100
	
	pvBarPlayMon.css("width",pvPlayMonPrg+"%")
	
	pvBarAdvMon = $("#progressAdv")
	pvBarAdvMon.css("position","relative")
	pvAdvMonPrg = ${sessionScope.adversaire.PV}/${sessionScope.adversaire.PVmax}*100
	pvBarAdvMon.css("width",pvAdvMonPrg+"%")
});

function hasPlayed(playerTurn, finCombat){
	console.log(playerTurn)
	console.log(finCombat)
	if(!playerTurn){
		$("#menuSelectAtk").find("button").each(function(){
			$(this).prop("disabled",true)
		})
		if(!finCombat){
			
			$("#encartMsg").css("display","bloc")
			console.log($("#encartMsg div:first-child"))
			$("#encartMsg div:first-child").text("${sessionScope.adversaire.nom} utilise ${sessionScope.adversaire.listAttaque[0].nom}")
			console.log($("#imgAdv"))
			$("#imgAdv").addClass("shaking-adv")
			setTimeout(sendCombatBot,3000);
		}
	}else{
		$("#menuSelectAtk").find("button").each(function(){
			$(this).prop("disabled",false)
		})
	}
}

function sendCombatBot(){
		$.ajax({
		
		type:"POST",
		url:'${pageContext.request.contextPath}/actioncombat',
		data:{'atkId' : ${sessionScope.adversaire.listAttaque[0].id},'playerPlays':false},
		success: function(response){
			rep = JSON.parse(response)
			console.log("bot")
			console.log(rep)
			pvBarPlayMon = $("#progressPlayMon")
			pvPlayMonPrg = rep.pvAtk/${sessionScope.attaquant.PVmax}*100
			pvBarPlayMon.css("width",pvPlayMonPrg+"%")
			pvBarPlayMon.text(rep.pvAtk+" / "+${sessionScope.attaquant.PVmax})
			hasMsg(rep.msg)
			isFightEnded(rep.endFight)
			hasPlayed(rep.playerTurn, rep.endFight)
			//window.location.href='${pageContext.request.contextPath}/actioncombat'
		}
		
	})
}

function hasMsg(msg){
	//msg = "${sessionScope.msg}"
	
	if( msg != "null"){
		$("#encartMsgPlace").text(msg)
		$("#encartMsg").css("display","bloc")
		
	}else{
		$("#encartMsg").css("display","none")
	}
	
}

function isFightEnded(endFight){
	console.log("is ended")
	console.log(endFight)
	if(endFight){
		toasty()
		setTimeout(moveToIndex,4000)
	}
}

function moveToIndex(){
	window.location.href="${pageContext.request.contextPath}/"
}
function sendCombat(atkId){
	
	$.ajax({
		
		type:"POST",
		url:'${pageContext.request.contextPath}/actioncombat',
		data:{'atkId' : atkId,'playerPlays':true},
		success: function(response){
			console.log("player")
			rep = JSON.parse(response)
			console.log(rep)
			pvBarAdvMon = $("#progressAdv")
			pvBarMonAdvVal = rep.pvAdv/${sessionScope.adversaire.PVmax}*100
			console.log(pvBarMonAdvVal)
			pvBarAdvMon.css("width",pvBarMonAdvVal+"%")
			pvBarAdvMon.text(rep.pvAdv+" / "+${sessionScope.adversaire.PVmax})
			
			hasMsg(rep.msg)
			isFightEnded(rep.endFight)
			hasPlayed(rep.playerTurn,rep.endFIght)
		}
		
	})
	
}
function toasty(){
	$("#toastyAudio").get(0).play()
	$("#toastyJordan").addClass("toast-it")
}
</script>
<style>
@keyframes toaster{
	0%	{left:-360px}
	100%	{left:0}
}
.toast-it{
	animation-name:toaster;
	animation-duration:1s;
	animation-direction:alternate;
}
</style>
</head>
<body>
	<div class="container">
	<img id="toastyJordan" style="position:absolute;left:-360px;bottom:0" src="${pageContext.request.contextPath}/assets/img/BJ.png"/>
	<audio id="toastyAudio">
		<source src='http://soundfxcenter.com/video-games/mortal-kombat-3-trilogy/8d82b5_Mortal_Kombat_3_Toasty_Sound_Effect.mp3' type="audio/mpeg"/>
	</audio>
		<div class="row h-100 align-items-center">
			<div class="col-6" style="margin:0 auto; border:black 2px solid;background-color:#eaeaea">
				<div class="row">
					<!-- stat adverse -->
					<div id="blocAdv" class="font-mine" style="width:100%;padding:10px">
						<div class="row no-gutters">
							<div class="col text-right"><h4>${sessionScope.adversaire.nom}</h4></div>
						</div>
						<div class="row no-gutters">
							<div class="col text-right"><h5>lvl : ${sessionScope.adversaire.level}</h5></div>
						</div>
						<div class="progress float-right text-right" style="width:40%;border:black 2px solid;">
							<div class="progress-bar progress-bar-striped bg-danger progress-bar-animated" 
								role="progressbar" 
								id="progressAdv"
								aria-now="${sessionScope.adversaire.PV}"
								aria-min="0"
								aria-max="${sessionScope.adversaire.PVmax}"
								>
								
							</div>
						</div>
					</div>
				</div>
				<div class="row justify-content-end">
					<img 
						id="imgAdv" 
						src="${pageContext.request.contextPath}/assets/img/monsters/1.png" 
						height="100" 
						style="transform: scaleX(-1)"
					/>
				</div>
				<div class="row">
					<img 
						id="imgPlay" 
						src="${pageContext.request.contextPath}/assets/img/monsters/2.png" 
						height="100" 
					/>
				</div>
				<div class="row border-black" id="encartMsg"  style="min-height:20px;border-radius:5px;margin: 5 10px 0 10px; box-shadow: 2px 2px">
					<div class="font-mine" id="encartMsgPlace" style="margin-left:20px;"></div>
				</div>
				<div class="row">
					<!--  nos stat + actions -->
					
					<div id="menu_actions"  class="font-mine w-100" style="padding:10px">
						<h4>${sessionScope.attaquant.nom}</h4>
						<h5>lvl : ${sessionScope.attaquant.level}</h5>
						
						<div class="progress" style="width:40%; border:black solid 2px">
							<div class="progress-bar" 
									role="progressbar" 
									id="progressPlayMon"
									aria-now="${sessionScope.attaquant.PV}"
									aria-min="0"
									aria-max="${sessionScope.attaquant.PVmax}"
							>
							</div>
							
						</div>
						<div id="menuSelectAtk" style="border-left:1px solid black;border-top: black 1px solid;border-radius:5px;margin-top:2px; box-shadow: 2px 2px">
							<c:forEach items="${sessionScope.attaquant.listAttaque}" var="a">
								<div class="row">
									<div class="col-4" >
										<button class="btn btn-link text-dark" 
											data-toggle="collapse" 
											data-target="#${a.nom}Col" 
											onclick="sendCombat(${a.id})">${a.nom}</button>
									</div>
									<div class="col text-left">puissance : ${a.puissance} [${a.type}]</div>
								</div>
								
							</c:forEach>
						</div>
					</div>
				</div>
				<!--  <div class="row">
					${sessionScope.attaquant}<br>
					${sessionScope.adversaire}
				</div>-->
			</div>
		</div>
	</div>

</body>
</html>