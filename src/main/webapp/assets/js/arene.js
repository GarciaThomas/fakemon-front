
/*$.ajax({
	type:"GET",
	url:"mechanics/arene/left",
	success : function(data){
		console.log("check arene"+data)
		if(data){
			generateArena();
		}
	}
})*/

//function generateArena(){
	$.ajax({
		type:'GET',
		url:'mechanics/arene/pop',
		success:function(resp){
			console.log(resp)
			if(resp != ""){
				data = JSON.parse(resp)
				scene.triggers.interact.push(data)
				dresseur = $("<img src='assets/img/monsters/1.png' type='dresseur' style='position:absolute; z-index:1; height:40px; width:40px;' />")
				dresseur.css("top",(tailleCase*data.pos[1])+"px")
				dresseur.css("left",(tailleCase*data.pos[0])+"px")
				$("#scene").append(dresseur)
			}
		}
	})
//}
console.log("in arene")