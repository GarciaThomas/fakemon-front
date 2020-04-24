<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selection</title>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(
		
		function(){
			$("#listSelect").children().hover(
					function(){
						$(this).css("box-shadow","black 2px 2px")
					},
					function(){
						$(this).css("box-shadow","#eaeaea 2px 2px")
					}
				);
			$("#listSelect").sortable({stop:sortEventhandler})
			
		}
);
// Fonction inutile ici, juste un memo pour menu reorganisation equipe #thumbsUp
function sortEventhandler(event, ui){
    console.log($("#listSelect li:first-child").attr("index"))
};

function versCombat(idx){
	$.ajax({
		type:"POST",
		url:'${pageContext.request.contextPath}/setupsession',
		data:{'mstrId' : idx,'playerPlays':true},
		success: function(){
			window.location.href='${pageContext.request.contextPath}/setupsession'
		}
	});
}
</script>
</head>
<body>

	<div class="container">
		<div class="row h-100 align-items-center justify-content-around">
			<div class="col-6 text-center" style="background-color:#eaeaea;padding:5px; border-radius:5px;border:black 2px solid">
				<div id="testMsg">${data }</div>
				<h5 class="font-mine" style="padding:4px;">Selectionne ton monstre</h5>
				<ul id="listSelect" class="list-group">
					<c:forEach items="${monstres}" var="m" varStatus="loop">
						<li class="font-mine list-group-item" onclick="versCombat(${loop.index})" index="${loop.index}" style="box-shadow:#eaeaea 2px 4px; margin-bottom:2px">${m.nom}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>