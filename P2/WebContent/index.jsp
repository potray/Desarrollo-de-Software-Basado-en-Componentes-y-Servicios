<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<title>P2 - Daniel Díaz Salas</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>	
	<div class="container">
		<div class="panel panel-default">
			<h1>Práctica 2 - Daniel Díaz Salas</h1>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Crear usuario</h3>
				<form action="ListaCorreosServlet" method="post" role="form">
					<div class="form-group">
						<label for="nombre">Nombre</label>
						<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Introduce el nombre"/>
					</div>
					<div class="form-group">
						<label for="apellido">Apellido</label>
						<input type="text" class="form-control" id="apellido" name="apellido" placeholder="Introduce el apellido"/>
					</div>
					<div class="form-group">
						<label for="email">Email</label>
						<input type="text" class="form-control" id="email" name="email" placeholder="Introduce el correo"/>
					</div>
					<div class="form-group">			
						<input type="hidden" name="action" value="insertar"/>
						<input type="submit" class="btn btn-success"/>
					</div>
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Buscar usuario</h3>
				<form action="ListaCorreosServlet" method="post" role="form">
					<div class="form-group">
						<label for="email">Email</label>
						<input type="text" class="form-control" id="email" name="email" placeholder="Introduce el correo"/>
						<input type="hidden" name="action" value="seleccionar"/>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-info" value="Buscar"/>
					</div>
					<c:if test="${not empty usuario}">
						<h4>Resultado de la búsqueda</h4>
						<label>Nombre:&nbsp;</label>${usuario.getNombre()}<br/>
						<label>Apellido:&nbsp;</label>${usuario.getApellido()}
					</c:if>					
				</form>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-body">
				<h3>Lista de usuarios</h3>
				<c:if test="${not empty usuarios}">
					<table class="table table-striped">
						<thead>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Email</th>
						</thead>
						<tbody>
							<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<form action="ListaCorreosServlet" method="post">
										<td>
											<input type="text" name="nombre" value="${usuario.getNombre()}"/>
										</td>
										<td>
											<input type="text" name="apellido" value="${usuario.getApellido()}"/>
										</td>
										<td>									
											<input type="text" name="email" value="${usuario.getEmail()}"/>
										</td>
										<input type="hidden" name="idUsuario" value="${usuario.getIdUsuario()}"/>
										<input type="hidden" name="action" value="actualizar"/>
										<td>
											<input type="submit" value="Actualizar" class="btn btn-info"/>
										</td>
									</form>
									<td>							
										<form action="ListaCorreosServlet" method="post">
											<input type="hidden" name="action" value="eliminar"/>
											<input type="hidden" name="idUsuario" value="${usuario.getIdUsuario()}"/>
											<input type="submit" value="Eliminar" class="btn btn-danger"/>
										</form>
									</td>
								</tr>	
							</c:forEach>
						</tbody>
					</table>
				</c:if>
		</div>
		<c:if test="${not empty mensaje}">
			<c:choose>
				<c:when test="${mensaje=='actualizado'}">	
					<script languaje="javascript">
						alert("Usuario actualizado.")
					</script>
				</c:when>
				<c:when test="${mensaje=='usuarioNoExiste'}">	
					<script languaje="javascript">
						alert("Usuario no encontrado.")
					</script>
				</c:when>	
				<c:when test="${mensaje=='errorUsuario'}">	
					<script languaje="javascript">
						alert("Error: el email ya existe.")
					</script>
				</c:when>			
			</c:choose>
		</c:if>	
		</div>
	</div>
</body>
</html>