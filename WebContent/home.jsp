
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Home</title>
</head>
<body style="background-color: #1D5959;">
	<div id="container"
		style="margin: 0% 8.5% 0% 8.5%; padding: 40px; background-color: white;">
		<h1 style="text-align: center; margin-bottom: 30px;">Times do
			Paulistão</h1>
		<c:if test="${database != null}">
			<div class="alert alert-danger" role="alert">${database}</div>
		</c:if>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">Nome</th>
					<th scope="col">PT</th>
					<th scope="col">J</th>
					<th scope="col">V</th>
					<th scope="col">E</th>
					<th scope="col">D</th>
					<th scope="col">GP</th>
					<th scope="col">GC</th>
					<th scope="col">SG</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="time" items="${times}" varStatus="contador">
					<tr>
						<td scope="row"><c:out value="${contador.count}" /></td>
						<td><c:out value="${time.nome_time}" /></td>
						<td><c:out value="${time.pontos}" /></td>
						<td><c:out value="${time.num_jogos_disputados}" /></td>
						<td><c:out value="${time.vitorias}" /></td>
						<td><c:out value="${time.empates}" /></td>
						<td><c:out value="${time.derrotas}" /></td>
						<td><c:out value="${time.gols_marcados}" /></td>
						<td><c:out value="${time.gols_sofridos}" /></td>
						<td><c:out value="${time.saldo_gols}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="text-align: center;">

			<c:if test="${times.size() != 0 && grupos.size() == 0}">
				<a href="/campeonato-paulista/grupos"><button
						style="font-weight: bolder; font-size: 26px; border-radius: 5px; background-color: red;">Gerar
						Grupos</button></a>
			</c:if>
			<c:if test="${grupos.size() != 0}">
				<a href="/campeonato-paulista/grupos">
					<button
						style="font-weight: bolder; font-size: 26px; border-radius: 5px; background-color: red;">Vizualizar
						Grupos</button>
				</a>
			</c:if>
			<c:if test="${grupos.size() != 0 && jogos.size() == 0}">
				<a href="/campeonato-paulista/jogos">
					<button
						style="font-weight: bolder; font-size: 26px; border-radius: 5px; background-color: blue;">Gerar
						Jogos</button>
				</a>
			</c:if>
			<c:if test="${grupos.size() != 0 && jogos.size() != 0}">
				<a href="/campeonato-paulista/jogos">
					<button
						style="font-weight: bolder; font-size: 26px; border-radius: 5px; background-color: blue;">Vizualizar
						Jogos</button>
				</a>
			</c:if>
			<c:if test="${grupos.size() != 0 && jogos.size() != 0}">
				<a href="/campeonato-paulista/mata-mata">
					<button
						style="font-weight: bolder; font-size: 26px; border-radius: 5px; background-color: green;">Vizualizar
						Quartas</button>
				</a>
			</c:if>


		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>