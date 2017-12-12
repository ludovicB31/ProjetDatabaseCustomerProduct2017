<%-- 
    Document   : ClientVue
    Created on : 22 nov. 2017, 16:11:28
    Author     : lblanc01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!--//IMPORTANT fait fonctionner le parcour des liste pour les formilaires-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Compte Client</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="container">
	<!--header start-->
	<div class="row" >
    	<div class="col-sm-12" >
        	<div class="header bg-white" >            		
                    <div class="logo" style="text-justify: none; text-align: center;  " ><h1 id="page">Page Client: ${name} </h1></div>
                		          	
            </div>
        </div>
    </div>
    
    <!--sidebar and content-->
  	<div class="row">
    	<!--left sidebar-->
        <div class="col-sm-3"</div>
        <h1 class="rubrique">Votre Profil </h1>
        	<div class="left-sidebar bg-white"
            	<div class="row">
            		<div class="col-sm-12">
                           <h1 style="font-size: 17px; line-height:21px;">Nom: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${name}<br></br>Identifiant: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${id}<br></br>Email: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${email}<br></br>Telephone: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${phone}<br></br>Ville: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${city}<br></br>Adresse: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${adr1}<br></br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  ${adr2} </h1>
                	</div>
                </div>
            </div>
        </div>          
        <h1 class="rubrique">Passer une commande:</h1> 
        <div class="col-sm-5">
        	<!--middle top two block-->
        	<div class="row">
        		<div class="col-sm-6 col-xs-6">
                            <div class="middle-left bg-white"> 
                                
                               
           
                                
              <form style="margin-top: 13px; " method='GET' action="Connexion"  ><%-- Formulaire de commande lié à la servlet Connexion.java --%>
              
              <select name='cat' onchange='this.form.submit()'  >
                      <option>Choisir une categorie</option>
                       <c:forEach var="test" items="${cats}">
                           <option  value='${test}' >
                               <%-- Si l'option correspond a l'état sélectionné, on la sélectionne --%>
                               ${test}
                               
                           </option>
                       </c:forEach>
               </select>
               <input type="hidden" name="mp" value="${id}"> 
               <c:if  test="${prod =='Nos Produits' or  prod ==null }">    
               <input type="hidden" name="action" value="UPDprod"> <%//demande au controlleur de mettre à jour la liste des produits de la catégorie%>
             
               </c:if>
                   <c:if  test="${prod !='Nos Produits'  and prod !=null }">    
               <input type="hidden" name="action" value="Command"> <%//demande au controlleur de mettre à jour la liste des produits de la catégorie%>
              </c:if>
               
              <select name='prod' onchange='this.form.submit()'  >
                  
                 
                
               <c:if  test="${prod =='Nos Produits' or  prod ==null }"><%//si aucun produit sélectionner on affiche NOS PRODUITS%>
                <option>Nos Produits</option>
                  </c:if>
                  
         <c:if  test="${prod !='Nos Produits'  and prod !=null  }"><%//Si un produit deja selectioné on l affiche%>
         <option>   ${prod}</option>
      </c:if>
          
                  
                       <c:forEach var="test" items="${prods}">
                           <option  value='${test}' >
                               <%-- Si l'option correspond a l'état sélectionné, on la sélectionne --%>
                               ${test}
                               
                           </option>
                       </c:forEach>
               </select>
               
                   <c:if  test="${prod !='Nos Produits'  and prod !=null}">
        
                       Quantité : <input name="quantity" type="number"  min="0" max="1000000" size="10">
                       <select name='livreur'>
                           <option>Selectionnez un service de livraison:</option>
                           <option>Poney Express</option>
                           <option>Slow Snail</option>
                           <option>Western Fast</option>
                           <option>We deliver</option>
                           <option>Coastal Freight</option>
                           <option>Southern Delivery Service</option>
                           <option>FR Express</option>
                       </select>

      </c:if>
                       &nbsp;&nbsp;&nbsp;&nbsp;<button class="Command" onclick='this.form.submit()'><input type="hidden" name="action" value="Command">Commander</button> <%//demande au controlleur de mettre à jour la liste des produits de la catégorie%>
           </form>
                                
          
   
 </div>
                </div>
            	<div class="col-sm-6 col-xs-6">
                	<div class="middle-right bg-white"> 
                            <div style="color: #0e373d; display: inline-block;">${message}</div> <%-- afficheur de notification  --%></div>
                </div>
            </div>
            <!--main content--> 
            <h1 class="rubrique">Voir et modifier vos commandes :</h1>
            <div class="row">
            	<div class="col-sm-12">
                	<div class="main-content bg-white">
                    	<div class="row" >
            			
		                       <table border="1" id="tableCommand">
				<tr><th>Numéro Commande</th><th>Produit</th><th>Quantitée</th><th>Date</th><th>Prix Livraison</th><th>Prix Commande</th><th>Supprimer</th></tr>
				<c:forEach var="record" items="${CMDS}">
					<tr>
                                                <td>${record.getOrderNum()}</td>
                                                <td>${record.getName()} </td>
                                                <td>${record.getQuantity()}</td>
                                                <td>${record.getDate()}</td>
                                                <td>${record.getPrice()} € </td>
                                                <td>${record.getTotalPrice()} €</td>
                                                <td><a  href="?action=DELETE&code=${record.getOrderNum()}&mp=${id}"><button class="Command" >Supprimer</button></a></td>
						
                                                
						
                                           
					</tr>	  		    
				</c:forEach>  
                                        </table>
                            
                		</div>
                    </div>
                </div>
            </div>
           
       
    
    <!--bottom three block-->
    
    <!--footer start-->
    <div class="row">
    	<div class="col-sm-12">
        	<div class="footer bg-white">
                    <div id="deco">
                        
                        <form><button class="Command quit"  onclick='this.form.submit()'><input type="hidden" name="action" value="Deconnect" >Déconnexion</button> </form><%//demande au controlleur de deconexion%>
                    </div>
                    </div>
        </div>
    </div>
</div>
             
</body>
</html>