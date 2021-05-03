
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
	
<style>
  
  .table td, .table th{
    border-top: 0;
  }
</style>
<title>Tabelas de Jogos</title>
</head>
<body style="background-color: #1D5959;">
	<div id="container"
		style="margin: 0% 8.5% 0% 8.5%; padding: 40px; background-color: white;">
		<a href="/campeonato-paulista/"><button link="google.com"
				style="position: absolute; margin-left: -20px; margin-top: -30px; border-radius: 5px; font-size: 20px;">Voltar
				para página principal</button></a>


		<h2 style="text-align: center; margin-bottom: 30px;">
			Quartas de Finais
			</h1>

			<div class="row">
				<div class="col-2"></div>
				<div style="text-align: center;" class="col-8">
					<table class="table">

						<tbody>
							<c:forEach var="time" items="${confrontos}" varStatus="contador">
								<tr>
									<td style="text-align: center;">
									<td />

									<td style="text-align: center; margin: 20px;">
									<td />

									<td style="text-align: center; margin: 20px;">
									<td />
								</tr>
								<tr class="table-info">
									<td style="text-align: center;"><c:out
											value="${time.timeA}" />
									<td />

									<td style="text-align: center; margin: 20px;"><c:out
											value="X" />
									<td />

									<td style="text-align: center; margin: 20px;"><c:out
											value="${time.timeB}" />
									<td />
								</tr>
								<tr >
									<td style="text-align: center;">
									<td />

									<td style="text-align: center; margin: 20px;">
									<td />

									<td style="text-align: center; margin: 20px;">
									<td />
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="col-2"></div>
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