/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.mycompany.mavenprojetaab.ChiffreAffaireClientObj;
import com.mycompany.mavenprojetaab.DAOprojet;
import com.mycompany.mavenprojetaab.DataSourceFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author calver03
 */
@WebServlet(name = "ChiffreAffaireProduit", urlPatterns = {"/ChiffreAffaireProduit"})
public class ChiffreAffaireProduit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOprojet mydao=new DAOprojet(DataSourceFactory.getDataSource());
        ArrayList<ChiffreAffaireClientObj> cac=null;
         String dd=null;
        String df=null;
        try{dd=request.getParameter("DateDeb").toString();
        df=request.getParameter("DateFin").toString();}
        catch(Exception e){e.printStackTrace();
        System.out.println("dd="+request.getParameter("DateDeb"));}
        try {
             cac =mydao.ProduitChiffre(dd,df);
            
        } catch (ParseException ex) {
            Logger.getLogger(ChiffreAffaireProduit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChiffreAffaireProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        String jsonProduit = new Gson().toJson(cac);
       response.setContentType("application/json");

       response.getWriter().write(jsonProduit);

    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}