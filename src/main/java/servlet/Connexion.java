/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.mycompany.mavenprojetaab.Commande;
import com.mycompany.mavenprojetaab.DAOprojet;
import com.mycompany.mavenprojetaab.DataSourceFactory;
import java.util.Calendar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lblanc01
 */
@WebServlet(name = "Connexion", urlPatterns = {"/Connexion"})
public class Connexion extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        action = (action == null) ? "" : action; // Pour le switch qui n'aime pas les null
        String email = request.getParameter("email");
	String mp = request.getParameter("mp");
        String pageConnect="ClientEditor.jsp";
        HttpSession session=null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          DAOprojet mydao =new DAOprojet(DataSourceFactory.getDataSource());
			
			switch (action) {
				case "connect": // Requête de connexion (vient du formulaire de saisie)
                                    if(email=="" || mp=="" ){
                                    request.setAttribute("message", "Veuillez remplir tout les champs ");
                                          }
                                    else if( mydao.ClientExist(email, Integer.parseInt(mp))){
                                        request.setAttribute("message", "Vous etes correctement Connecter");
                                         //on envoi la liste des catégories
                                        ArrayList<String> cats=mydao.allCats();
                                        request.setAttribute("cats", cats);
                                        //on renseigne la vue
                                        pageConnect="ClientVue.jsp";
                                        session=request.getSession(true);
                                        session.setAttribute("email", email);
                                        session.setAttribute("id",mp);
                                        String name=mydao.ClientGetName(Integer.parseInt(mp));
                                        session.setAttribute("name", name);
                                        String city=mydao.ClientGetCity(Integer.parseInt(mp));
                                        session.setAttribute("city", city);
                                        String phone=mydao.ClientGetPhone(Integer.parseInt(mp));
                                        session.setAttribute("phone", phone);
                                        String adr1=mydao.ClientGetAdress1(Integer.parseInt(mp));
                                        session.setAttribute("adr1", adr1);
                                        String adr2=mydao.ClientGetAdress2(Integer.parseInt(mp));
                                        session.setAttribute("adr2", adr2);
                                        List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));//on genere la liste des commande du client
                                        request.setAttribute("CMDS", CMDS);
                                       
		}
                                        else{ 
                                            
                                            if(email.equals(getInitParameter("loginADM")) && getInitParameter("passwordADM").equals(mp)){
                                                request.setAttribute("message", "Vous etes connecté en administrateur");
                                                pageConnect="AdminVue.jsp";
                                                session=request.getSession(true);
                                                
                                            }else{
                                            
                                            
                                            request.setAttribute("message", "Identifiant ou mot de passe incorrect");}
}
                                        
                                        
                        
											
				break;
                                case "Command"://requette pour ajouter une commande 
                              String quantity=request.getParameter("quant");
                              String livreur=request.getParameter("livreur");
                              if (quantity=="" || quantity=="0" || livreur.equals("Selectionnez un service de livraison:")){
                                   request.setAttribute("message", "Quantité ou service de livraison éroné");                                                
                                   List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));
                                    request.setAttribute("CMDS", CMDS);
                                    
                                    ArrayList<String> cats=mydao.allCats();
                                        request.setAttribute("cats", cats);
                                             
                                       
                                        }
                                        else{  
                                        
                                         //creation de la commande (objet)
                                        Date dateCommand=new Date();
                                        try{Commande command=new Commande(Integer.parseInt(mp),Integer.parseInt(request.getParameter("quantity")),mydao.getProductIDByDesc(request.getParameter("prod")),mydao.OrderNumGenerator(),request.getParameter("livreur"),(float)10.00,dateCommand);
                                        mydao.addCommande(command);}catch(Exception e){System.out.println("erreur commande"+e.getMessage());}
                                        request.setAttribute("message", "Votre Commande à été prise en compte");  
                                        List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));
                                        request.setAttribute("CMDS", CMDS);
                                        ArrayList<String> cats=mydao.allCats();
                                        request.setAttribute("cats", cats);
                                                         } 
                                      
                               pageConnect = "ClientVue.jsp";
                        break;
                                case "UPDprod":
                                    pageConnect="ClientVue.jsp";
                                   String produit=request.getParameter("prod");

                                    if (request.getParameter("cat").equals("Choisir une categorie")==false){
                                    request.setAttribute("message", "Vous avez sélectionné la catégorie [ "+request.getParameter("cat")+" ], Choisissez un produit :");
                                      ArrayList<String> cats=mydao.allCats();
                                        request.setAttribute("cats", cats);
                                        String cat = request.getParameter("cat");
                                        ArrayList<String> prods=mydao.ProdsByCat(cat);
                                        request.setAttribute("prods", prods);
                                        produit="Nos Produits";
                                        request.setAttribute("prod", produit);
                                        List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));//on genere la liste des commande du client
                                        request.setAttribute("CMDS", CMDS);
                                        System.out.println("categorie changé  "+request.getParameter("cat")+" O");
                                        
                                    }
                                     if (request.getParameter("cat").equals("Choisir une categorie")){
                                   
                                      ArrayList<String> cats=mydao.allCats();
                                        request.setAttribute("cats", cats);
                                        String cat = request.getParameter("cat");
                                        ArrayList<String> prods=mydao.ProdsByCat(cat);
                                        request.setAttribute("prods", prods);
                                        produit=request.getParameter("prod");
                                        request.setAttribute("prod", produit);
                                        List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));//on genere la liste des commande du client
                                        request.setAttribute("CMDS", CMDS);
                                        System.out.println("categorie NON changé");
                                        
                                    }
                                 
                                    if (produit.equals("Nos Produits")==false){
                                        System.out.println("-"+produit+"- n'est pas egal à "+ "Nos Produits");
                                         request.setAttribute("message", "Vous avez sélectionné le produit suivant:  [ "+produit+" ]");
                                         request.setAttribute("prod", produit);
                                         List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));//on genere la liste des commande du client
                                        request.setAttribute("CMDS", CMDS);
                                    }
                                    break;
                                case "Deconnect"://deconnexion et redirection vers la page de connexion
                                   
                                   request.setAttribute("message", "deconnexion");
                                    pageConnect="ClientEditor.jsp";
                                    request.getSession().invalidate();
                                  break;
                                   
                                    case "DELETE": // Requête de suppression (vient du formulaire d'édition des commandes)
                        request.setAttribute("message", "Commande correctement supprimée"); 
                        String orderNum = request.getParameter("code");
                        mydao.deleteCommande(Integer.parseInt(orderNum));
                        
                        List<Commande> CMDS = mydao.CommandesById(Integer.parseInt(mp));//on genere la liste des commande du client
                        request.setAttribute("CMDS", CMDS);
                        ArrayList<String> cats=mydao.allCats();
                        request.setAttribute("cats", cats);
                        pageConnect = "ClientVue.jsp";
                        break;
                        
                                    
        }
               System.out.println(action+"---");
        request.getRequestDispatcher(pageConnect).forward(request, response);
        }catch (Exception ex) {
            request.setAttribute("message", ex.getMessage());
			Logger.getLogger("Connexion").log(Level.SEVERE, "Action en erreur", ex);
			
		}

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlegfddddific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
