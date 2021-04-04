
<%@page import="java.util.Calendar"%>
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
<title>Tabelas de Jogos</title>
</head>
<body style="background-color: #1D5959;">
	<div id="container"
		style="margin: 0% 8.5% 0% 8.5%; padding: 40px; background-color: white;">
		<a href="/campeonato-paulista/"><button link="google.com"
				style="position: absolute; margin-left: -20px; margin-top: -30px; border-radius: 5px; font-size: 20px;">Voltar
				para página principal</button></a>


		<h2 style="text-align: center; margin-bottom: 30px;">
			Tabela de Grupos
			</h1>

			<div class="row">

				<div class="col-6">
					<h3 style="text-align: center;">Grupo A</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Codigo</th>
								<th scope="col">Nome</th>
								<th scope="col">cidade</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="time" items="${grupoA}">
								<tr>
									<td scope="row"><c:out value="${time.codigo}" /></td>
									<td><c:out value="${time.nome}" /></td>
									<td><c:out value="${time.cidade}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-6">
					<h3 style="text-align: center;">Grupo B</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Codigo</th>
								<th scope="col">Nome</th>
								<th scope="col">Cidade</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="time" items="${grupoB}">
								<tr>
									<td scope="row"><c:out value="${time.codigo}" /></td>
									<td><c:out value="${time.nome}" /></td>
									<td><c:out value="${time.cidade}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-6">
					<h3 style="text-align: center;">Grupo C</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Codigo</th>
								<th scope="col">Nome</th>
								<th scope="col">Cidade</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="time" items="${grupoC}">
								<tr>
									<td scope="row"><c:out value="${time.codigo}" /></td>
									<td><c:out value="${time.nome}" /></td>
									<td><c:out value="${time.cidade}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col-6">
					<h3 style="text-align: center;">Grupo D</h3>
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Codigo</th>
								<th scope="col">Nome</th>
								<th scope="col">Cidade</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="time" items="${grupoD}">
								<tr>
									<td scope="row"><c:out value="${time.codigo}" /></td>
									<td><c:out value="${time.nome}" /></td>
									<td><c:out value="${time.cidade}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>

			<c:if test="${temJogo != 0}">
				<div class="row" style="margin-top: 10px;">
					<div class="col-6">
						<h2 style="text-align: center;">Tabela de Jogos</h2>
					</div>
					<div class="col-6">
						<form action="http://localhost:8080/campeonato-paulista/jogos"
							method="get"
							style="float: right; width: 350px; margin-right: -50px;">
							<div class="form-row">
								<div class="col-10">
									<input name="data" type="text" class="form-control"
										placeholder="Pesquisar por Data">
								</div>
							</div>
						</form>
					</div>
				</div>

				<c:if test="${database != null}">
					<div class="alert alert-danger" role="alert">${database}</div>
				</c:if>
				<c:if test="${data != null}">
					<div class="alert alert-warning" role="alert">${data}</div>
				</c:if>

				<table class="table table-striped" style="margin-top: 10px;">
					<thead>
						<tr>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col"></th>
							<th scope="col" style="text-align: center;">Data</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="jogo" items="${jogos}">
							<tr>
								<td style="text-align: center;"><c:out
										value="${jogo.timeA}" /></td>
								<td style="text-align: center;"><c:out
										value="${jogo.golsA}" /></td>
								<td style="text-align: center;"><c:out value="X" /></td>
								<td style="text-align: center;"><c:out
										value="${jogo.golsB}" /></td>
								<td style="text-align: center;"><c:out
										value="${jogo.timeB}" /></td>
								<td style="text-align: center;">${jogo.data}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${jogos.isEmpty()}">
					<div class="alert alert-warning" role="alert">${dataSemJogos}</div>
				</c:if>
			</c:if>
			<c:if test="${temJogo == 0}">

				<form style="text-align: center; margin-top: 20px;"
					action="http://localhost:8080/campeonato-paulista/jogos"
					method="post">
					<input type="submit" value="Gerar Jogos"
						style="font-size: 28px; font-weight: bolder; border-radius: 5px; background-color: blue;" />
				</form>
			</c:if>
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