
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
		<a href="/campeonato-paulista/jogos"><button link="google.com"
				style="position: absolute; margin-left: -20px; margin-top: -30px; border-radius: 5px; font-size: 20px;">Voltar
				para página principal</button></a>


		<h2 style="text-align: center; margin-bottom: 30px;">Registrar
			Rodada</h2>


		<div class="row" style="margin-top: 10px;">
			<div class="col-6">
				<h2 style="text-align: center;">Tabela de Jogos</h2>
			</div>
			<div class="col-6">
				<form action="http://localhost:8080/campeonato-paulista/registrar"
					method="get"
					style="float: right; width: 350px; margin-right: -50px;">
					<div class="form-row">
						<div class="col-10">
							<input name="data" type="text" class="form-control"
								placeholder="Pesquisar rodada para modificar">
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
		<form style="text-align: right; margin-top: 20px;"
			action="http://localhost:8080/campeonato-paulista/registrar"
			method="post">

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

					<c:forEach var="jogo" items="${jogos}" varStatus="contador">
						<tr>
							<input name="codigoA${contador.count}" type="text"
								value="${jogo.codigoTimeA}" style="display: none;">
							<input name="codigoB${contador.count}" type="text"
								 value="${jogo.codigoTimeB}" style="display: none;">
							
							<td style="text-align: center;"><input
								name="timeA${contador.count}" type="text" style="width: 150px;"
								value="${jogo.timeA}" disabled></td>


							<td style="text-align: center; width: 10px;"><input
								name="golsA${contador.count}" type="number" style="width: 50px;"
								value="${jogo.golsA}"></td>


							<td style="text-align: center;"><c:out value="X" /></td>


							<td style="text-align: center; width: 10px;"><input
								name="golsB${contador.count}" type="number" style="width: 50px;"
								value="${jogo.golsB}"></td>


							<td style="text-align: center;"><input
								name="timeB${contador.count}" type="text" style="width: 150px;"
								value="${jogo.timeB}" disabled></td>


							<td style="text-align: center;">${jogo.data}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${jogos.isEmpty()}">
				<div class="alert alert-warning" role="alert">${dataSemJogos}</div>
			</c:if>
			<c:if test="${temJogo == 0}">

				<input type="submit" value="Gravar Resultados"
					style="font-size: 28px; font-weight: bolder; border-radius: 5px; background-color: Orange;" />
			</c:if>

		</form>

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