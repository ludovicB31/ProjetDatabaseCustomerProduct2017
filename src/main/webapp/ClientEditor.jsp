<%-- 
    Document   : ClientEditor
    Created on : 14 nov. 2017, 13:38:22
    Author     : lblanc01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
   <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Compte client</title>
                <link href="css/cssConnectPage.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<!-- On montre le formulaire de saisie -->
                <h1 id="page">Espace Client et Gestion des Commandes</h1>
		<h1 id="welcome">Veuillez vous connecter</h1>
		<form id="connectFORM" method='GET' action="Connexion">
                    <label for="email">Email :</label><input class="input" name="email" size="20"><br/><br/><br/>
		    <label for="mp">Mot de passe :</label><input type="number" class="input" name="mp" size="20"><br/>
                 
			<input type="hidden" name="action" value="connect">
			<input id="GO" type="submit" value="se connecter">
		</form>
		<%--  On montre un éventuel message d'erreur --%>
		<div id="notification"><h4>${message}</h4></div>
                <h1>jumboeagle@example.com</h1>
		<%-- On on montre la liste des discount codes --%>
		
	</body>        
</html>