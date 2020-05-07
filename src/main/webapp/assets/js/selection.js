$(document).ready(function(){
	
	loadList()

	$("#listSelect").children().hover(
		function(){
			$(this).css("box-shadow","black 2px 2px")
		},
		function(){
			$(this).css("box-shadow","#eaeaea 2px 2px")
		}
	);		



	function versCombat(event){
		idx = event.data.param1
		$.ajax({
			type:"POST",
			url:'combat/',
			data:{'mstrId' : idx,'playerPlays':true},
			success: function(resp){
				$("#scene").html(resp)
				$("#scene").css("display","block")
			}
		});
	}
	
	function loadList(){
			$.ajax({
			type:"GET",
			url:'player/squad',
			success: function(resp){
				data = JSON.parse(resp)
				$.each(data,function(index,val){
					let elemLi = $("<li></li>")
					let elem = $("<button></button>")
					
					elemLi.css("list-style-image","none")
					elemLi.addClass("list-group-item")
					
					elem.addClass("font-mine btn btn-link")
					elemLi.css({"box-shadow":"#eaeaea 2px 4px","margin-bottom":"2px"})
					elem.text(val.nom)
					
					if(val.PV <= 0){
						console.log("disabling")
						elem.prop("disabled","true")
					}else{
						elem.click({param1:index},versCombat)
					}
					elemLi.append(elem)
					$("#listSelect").append(elemLi)
					
				})				
			}
		})
	}
});