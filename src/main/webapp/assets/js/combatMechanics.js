$(document).ready(function(){
	var adversaire;
	var attaquant;
	setupMonsters();
	
});
	function heal(){
		console.log("healing")
		$.ajax({
			type:"POST",
			url:'/fakemon-front/mechanics',
			data:{"activity":"heal"}
		})
	}
	
	function setupMonsters(){
		
		$.ajax({
			type:'POST',
			url: '/fakemon-front/mechanics',
			data:{'activity':'monstersInfos'},
			success: function(resp){
				data = JSON.parse(resp)
				
				adversaire = data.adversaire
				attaquant = data.attaquant
				
				pvBarPlayMon = $("#progressPlayMon")
				pvPlayMonPrg = data.attaquant.PV/data.attaquant.PVmax*100
				
				pvBarPlayMon.css("width",pvPlayMonPrg+"%")
				
				pvBarAdvMon = $("#progressAdv")
				pvBarAdvMon.css("position","relative")
				pvAdvMonPrg = data.adversaire.PV/data.adversaire.PVmax*100
				pvBarAdvMon.css("width",pvAdvMonPrg+"%")
				$("#xp").text(data.attaquant.exp+"/"+data.attaquant.expNextLevel)
			}
		})
	}
	
	function hasPlayed(playerTurn, finCombat){
		if(!playerTurn){
			$("#menuSelectAtk").find("button").each(function(){
				$(this).prop("disabled",true)
			})
			if(!finCombat){
				
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
			url:'/fakemon-front/actioncombat',
			data:{'playerPlays':false,'handTo':'adv','action':'attaque'},
			success: function(response){
				rep = JSON.parse(response)
				console.log("bot")
				console.log(rep)
				pvBarPlayMon = $("#progressPlayMon")
				pvPlayMonPrg = rep.pvAtk/rep.pvMaxAtk*100
				pvBarPlayMon.css("width",pvPlayMonPrg+"%")
				pvBarPlayMon.text(rep.pvAtk+" / "+rep.pvMaxAtk)
				hasMsg(rep.msg)
				isFightEnded(rep.endFight)
				hasPlayed(rep.playerTurn, rep.endFight)
			}
			
		})
	}
	
	function hasMsg(msg){
		console.log("in hasMsg")
		console.log(msg)
		if( msg != "null"){
			console.log("msg not null")
			$("#encartMsgPlace").text(msg)
			$("#encartMsg").css("display","block")
			console.log($("#encartMsg").css("display"))
			
		}else{
			console.log("msg null")
			$("#encartMsg").css("display","none")
		}
		
	}
	
	function isFightEnded(endFight){
		if(endFight){
			heal()
			toasty()
			setTimeout(moveToIndex,4000)
		}
	}
	
	function moveToIndex(){
		window.location.href="/fakemon-front/gamescene"
	}
	
	function switchMonster(){
		$.ajax({
			
			type:"POST",
			url:'/fakemon-front/actioncombat',
			data:{'action':'switch','context':'combat'},
			success: function(response){
				$("#sceneCombat").html(response)
			}
			
		})
	}
	
	function capture(){
		$.ajax({
			
			type:"POST",
			url:'/fakemon-front/actioncombat',
			data:{'action':'capture'},
			success: function(response){
				console.log("capture")
				rep = JSON.parse(response)
				hasMsg(rep.msg)
				isFightEnded(rep.endFight)
				hasPlayed(rep.playerTurn,rep.endFight)
			}
			
		})
	}
	
	function sendCombat(atkId){
		
		$.ajax({
			
			type:"POST",
			url:'/fakemon-front/actioncombat',
			data:{'atkId' : atkId,'playerPlays':true,'handTo':'player','action':'attaque'},
			success: function(response){
				console.log("player")
				rep = JSON.parse(response)
				console.log(rep)
				console.log("Player")
	
				pvBarAdvMon = $("#progressAdv")
				pvBarMonAdvVal = rep.pvAdv/rep.pvMaxAdv*100
				pvBarAdvMon.css("width",pvBarMonAdvVal+"%")
				pvBarAdvMon.text(rep.pvAdv+" / "+rep.pvMaxAdv)
				
				$("#xp").text(rep.monster.exp+"/"+rep.monster.expNextLevel)
				
				hasMsg(rep.msg)
				isFightEnded(rep.endFight)
				hasPlayed(rep.playerTurn,rep.endFight)
				
			}
			
		})
		
	}
	function toasty(){
		$("#toastyAudio").get(0).play()
		$("#toastyJordan").addClass("toast-it")
	}
