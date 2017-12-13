<%-- 
    Document   : AdminVue
    Created on : 12 déc. 2017, 09:06:05
    Author     : ludovicblanc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Administrateur</title>
<!-- On charge jQuery -->
 <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
    
    // Load the Visualization API and the piechart package.
    google.charts.load('current', {'packages':['corechart']});
      
    // Set a callback to run when the Google Visualization API is loaded.
    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawChartState);
    google.charts.setOnLoadCallback(drawChartProduit);

      var dataRequestObject= {}; 
                dataRequestObject= {DateDeb:'2010-01-01',DateFin:'2018-01-01'};
      var dataRequestObjectgeo= {}; 
                dataRequestObjectgeo= {DateDeb:'2010-01-01',DateFin:'2018-01-01'};
      var dataRequestObjectProd= {}; 
                dataRequestObjectProd= {DateDeb:'2010-01-01',DateFin:'2018-01-01'};
    function drawChart() {
        
      var jsonData = $.ajax({
          url: "ChiffreAffaireClient",
          type:'POST',
          cache:false,
           contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
          dataType: "json",
          data: dataRequestObject,
          async: false
          }).responseText;
        cac=JSON.parse(jsonData);
                var dataClient = new google.visualization.DataTable();
                dataClient.addColumn('string', 'Task');
                dataClient.addColumn('number', 'Hours per Day');
                for(var i=0;i<cac[0].length;i++){
                dataClient.addRows([
                    [cac[0][i],cac[1][i]]
                 
                                ]);
                            }
                            

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(jsonData);
        
      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('Client'));
      chart.draw(dataClient, {width: 1200, height: 600, title:'Chiffre daffaire par client'});
    }
    function ChangeDateClient() {
      dataRequestObject= {DateDeb:document.getElementById('ByClientDateDeb').value,DateFin:document.getElementById('ByClientDateFin').value};
         window.alert("Modification des dates prises en comptes");
         drawChart();
    }
    function drawChartState() {
      var jsonData = $.ajax({
          url: "ChiffreAffaireState",
          type:'GET',
          cache:false,
          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
          dataType: "json",
          data:dataRequestObjectgeo,
          async: false
          }).responseText;
        cac=JSON.parse(jsonData);
                var dataState = new google.visualization.DataTable();
                dataState.addColumn('string', 'etat');
                dataState.addColumn('number', "Chiffre d'affaire");
                for(var i=0;i<cac[0].length;i++){
                dataState.addRows([
                    [cac[0][i],cac[1][i]]
                 
                                ]);
                            }
                       

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(jsonData);
        
      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('Geo'));
      chart.draw(dataState, {width: 1200, height: 600, title:'Chiffre daffaire par Etat'});
    }
    function ChangeDateGeo() {
      dataRequestObjectgeo= {DateDeb:document.getElementById('debutgeo').value,DateFin:document.getElementById('fingeo').value};
         window.alert("Modification des dates prises en comptes");
         drawChartState();
    }
    
    function drawChartProduit() {
      var jsonData = $.ajax({
          url: "ChiffreAffaireProduit",
          type:'GET',
          cache:false,
          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
          dataType: "json",
          data:dataRequestObjectProd,
          async: false
          }).responseText;
        cac=JSON.parse(jsonData);
                var dataProduit = new google.visualization.DataTable();
                dataProduit.addColumn('string', 'produit');
                dataProduit.addColumn('number', "Chiffre d'affaire");
                for(var i=0;i<cac[0].length;i++){
                dataProduit.addRows([
                    [cac[0][i],cac[1][i]]
                 
                                ]);
                            }
                       

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(jsonData);
        
      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('Prod'));
      chart.draw(dataProduit, {width: 1200, height: 600, title:'Chiffre daffaire par Produit'});
    }
       function ChangeDateProd() {
      dataRequestObjectProd= {DateDeb:document.getElementById('debutprod').value,DateFin:document.getElementById('finprod').value};
         window.alert("Modification des dates prises en comptes");
         drawChartProduit();
    }
    

    </script>

  </head>
  <body>
      <h1>Seletionez une periode pour l'affichage du chiffre d'affaire par client</h1>
          Date de départ:<input id="ByClientDateDeb" placeholder="2016-12-23" type="date" >
          Date de fin:<input id="ByClientDateFin" placeholder="2017-12-23" type='text'  ></input>
          <button onClick="JavaScript:ChangeDateClient()">Afficher</button>
    <div id="Client" ></div>
       
      <h1>Seletionez une periode pour l'affichage du chiffre d'affaire par Etats</h1>

          date de débuts:<input placeholder="2016-12-23" type='date' id='debutgeo' ></input>
          date de fin:<input placeholder="2017-12-23" type='date' id='fingeo' ></input>
          <button onClick="JavaScript:ChangeDateGeo()">Afficher</button>  
          <div id="Geo" ></div>
    
          <h1>Seletionez une periode pour l'affichage du chiffre d'affaire par Produit</h1>

          date de debut:<input placeholder="2016-12-23" type='date' id='debutprod' ></input>
          date de fin:<input placeholder="2017-12-23" type='date' id='finprod' ></input>
          <button onClick="JavaScript:ChangeDateProd()">Afficher</button>  
    <div id="Prod" style="width: 800px; height: 500px;"></div>
    <form action="Deconnect"><button><input type="submit">Déconnexion</input></button></form>
  </body>
</html>