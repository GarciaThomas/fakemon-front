/**
 * Obligatoire pour un script de trigger interaction : bloquer les mouvements et en fonction de la resolution autoriser a nouvea les mouvements
 * Pour afficher un message ingame, utiliser la boite de dialogue
 * @returns
 */

avatarMove = false;
$('#boiteMsg').empty()
$('#boiteMsg').append("<p>tu veux tu prendre ce monstre ?</p><button class='btn btn-link' onclick='yes()'>yes papa</button><button class='btn btn-link' onclick='no()'>no</button>")
$('#boiteMsg').css("display","block")

function yes(){
	$.ajax({
		type:'POST',
		url:'player/starter/1'
	})
	$('#boiteMsg').empty()
	$('#boiteMsg').css("display",'none')
	avatarMove=true;
}

function no(){
	$('#boiteMsg').empty()
	$('#boiteMsg').css("display",'none')
	avatarMove=true;
}
