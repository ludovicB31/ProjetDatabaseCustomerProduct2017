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
      
    function drawChart() {
      var jsonData = $.ajax({
          url: "ChiffreAffaireClient",
          type:'GET',
          dataType: "json",
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

    </script>

  </head>
  <body>
      <h1>Seletionez une periode pour l'affichage du chiffre d'affaire par client</h1>
      <form id='Clientdate' onsubmit="event.preventDefault();drawChart;">
          Date de départ:<input placeholder="2016-12-23" type="date" max="2050-06-25" min="2011-08-13" name="the_date">
          Date de fin:<input placeholder="2017-12-23" type='date' id='finclient' ></input>
          <button type='submit'>Afficher</button>
      </form>
    <div id="Client" ></div>
    <div id="chart_div"></div>
        <div id="demo"></div>
        <div id="demo2"></div>

    
    <form id='Geodate' onsubmit="event.preventDefault();ChiffreGeo();">
          datedeb:<input type='date' id='debutgeo' ></input>
          datefin:<input type='date' id='fingeo' ></input>
          <input type='submit'>calculer</input>
    </form>
    <div id="Geo" style="width: 800px; height: 500px;"></div>
    
    
    <form id='Proddate' onsubmit="event.preventDefault();ChiffreProd();">
          datedeb:<input type='date' id='debutprod' ></input>
          datefin:<input type='date' id='finprod' ></input>
          <input type='submit'>calculer</input>
    </form>
    <div id="Prod" style="width: 800px; height: 500px;"></div>
  </body>
</html>