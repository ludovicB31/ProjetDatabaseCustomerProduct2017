/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;



public class DAOprojet {

	private final DataSource myDataSource;
public DAOprojet(DataSource dataSource) {
		this.myDataSource = dataSource;
	}
	/**
	 * Construit le AO avec sa source de données
	 * @param dataSource la source de données à utiliser
	 */
	


  
	/**
	 * Contenu de la table DISCOUNT_CODE
	 * @return Liste des discount codes
	 * @throws SQLException renvoyées par JDBC
	 */
	public List<DiscountCode> allCodes() throws SQLException {

		List<DiscountCode> result = new LinkedList<>();

		String sql = "SELECT * FROM DISCOUNT_CODE ORDER BY DISCOUNT_CODE";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("DISCOUNT_CODE");
				float rate = rs.getFloat("RATE");
				DiscountCode c = new DiscountCode(id, rate);
				result.add(c);
			}
		}
		return result;
	}
        
        public ArrayList<String> allCats() throws SQLException {

		ArrayList<String> result = new ArrayList();

		String sql = "SELECT DESCRIPTION FROM PRODUCT_CODE ";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String cat = rs.getString("DESCRIPTION");
				
				result.add(cat);
			}
		}
		return result;
	}
        
         public ArrayList<String> ProdsByCat(String desc) throws SQLException {

		ArrayList<String> result = new ArrayList();

		String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_CODE=(SELECT PROD_CODE FROM PRODUCT_CODE WHERE DESCRIPTION=?)";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, desc);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String prod = rs.getString("DESCRIPTION");
				
				result.add(prod);
			}
		}
		return result;
	}


	/**
	 * Ajout d'un enregistrement dans la table DISCOUNT_CODE
	 * @param code le code (non null)
	 * @param rate le taux (positive or 0)
	 * @return 1 si succès, 0 sinon
	 * @throws SQLException renvoyées par JDBC
	 */
	public int addDiscountCode(String code, float rate) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, code);
			stmt.setFloat(2, rate);
			result = stmt.executeUpdate();
		}
		return result;
	}
        
        public String ClientGetName(int ID) throws SQLException{
        String result=null;
        String sql="SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("NAME");}
                        
		}catch(Exception e){
                    result="erreur nom dans BD";
        }
        return result;
        }
         public String ClientGetCity(int ID) throws SQLException{
        String result=null;
        String sql="SELECT CITY FROM CUSTOMER WHERE CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("CITY");}
                        
		}catch(Exception e){
                    result="erreur ville dans BD";
        }
        return result;
        }
          public String ClientGetPhone(int ID) throws SQLException{
        String result=null;
        String sql="SELECT PHONE FROM CUSTOMER WHERE CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("PHONE");}
                        
		}catch(Exception e){
                    result="erreur telephone dans BD";
        }
        return result;
        }
          
           public String ClientGetAdress1(int ID) throws SQLException{
        String result=null;
        String sql="SELECT ADDRESSLINE1 FROM CUSTOMER WHERE CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("ADDRESSLINE1");}
                        
		}catch(Exception e){
                    result="erreur adresse dans BD";
        }
        return result;
        }
           
           public String ClientGetAdress2(int ID) throws SQLException{
        String result=null;
        String sql="SELECT ADDRESSLINE2 FROM CUSTOMER WHERE CUSTOMER_ID=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("ADDRESSLINE2");}
                        
		}catch(Exception e){
                    result=" ";
        }
        return result;
        }



		
        public boolean ClientExist(String email,int ID) throws SQLException{
        boolean exist=false;
        String result=null;
        String sql="SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID=? AND EMAIL=?";
        try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
			stmt.setString(2, email);
                        ResultSet rs=stmt.executeQuery();
                      while (rs.next()) {
                       result=rs.getString("NAME");}
                        
                        
		}
                if (result !=null){exist=true;}
                return exist;
        }
	/**
	 * Supprime un enregistrement dans la table DISCOUNT_CODE
	 * @param code la clé de l'enregistrement à supprimer
	 * @return le nombre d'enregistrements supprimés (1 ou 0)
	 * @throws java.sql.SQLException renvoyées par JDBC
	 **/
	public int deleteDiscountCode(String code) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, code);
			result = stmt.executeUpdate();
		}
		return result;
	}


      /**
	 * Supprime un enregistrement dans la table PURCHASE_ORDER
	 * @param numOrder la clé de l'enregistrement à supprimer
	 * @return le nombre d'enregistrements supprimés (1 ou 0)
	 * @throws java.sql.SQLException renvoyées par JDBC
	 **/
	public int deleteCommande(int numOrder) throws SQLException {
		int result = 0;
		String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, numOrder);
			result = stmt.executeUpdate();
		}
		return result;
	}
        
        
        
        	/**
	 * Ajout d'un enregistrement dans la table DISCOUNT_CODE
	 * @param Commande
	 * @return 1 si succès, 0 sinon
	 * @throws SQLException renvoyées par JDBC
	 */
        	public int addCommande(Commande commande) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO PURCHASE_ORDER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, commande.getOrderNumInt());
			stmt.setInt(2, commande.getCustomerIdInt());
                        stmt.setInt(3, commande.getProductIdInt());
                        stmt.setInt(4, commande.getQuantityInt());
                        stmt.setFloat(5, commande.getShippingCost());
                        stmt.setDate(6, commande.getCommandDate());
                        stmt.setDate(7, commande.getCommandDate());
                        stmt.setString(8, commande.getLivreur());
			result = stmt.executeUpdate();
		}
		return result;
	}
                
                
                public List<Commande> CommandesById(int ID)throws SQLException {
                    List<Commande> result =new LinkedList<>();
                    String sql = "SELECT * FROM PURCHASE_ORDER WHERE CUSTOMER_ID=? ORDER BY SALES_DATE";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                      stmt.setInt(1, ID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("CUSTOMER_ID");
				int quantity = rs.getInt("QUANTITY");
                                int prodID = rs.getInt("PRODUCT_ID");
                                int order = rs.getInt("ORDER_NUM");
                                String transport = rs.getString("FREIGHT_COMPANY");
                                Float price = rs.getFloat("SHIPPING_COST");
                                Date date=rs.getDate("SALES_DATE");
// Ordre des parametre de creation d'un objet commande: int customer,int quantity,int productId,int orderNum,String freightCompany,float shippingCoast
				Commande c = new Commande(id, quantity,prodID,order,transport,price,date);
				result.add(c);
			}
		}
		return result;
                }
                 //recupere le prix d'une commande avec id du produit et quantitée
         public Double getCommandPrice (int Num,int nb) throws SQLException{
        Double result=0.0;
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE PRODUCT_ID=?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setInt(1, Num);
                        ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				result = rs.getDouble("PURCHASE_COST");
				
				
			
			}
		}   
                result=result*nb;
                return result;
                
                
                }
          public String getProductByNum (int Num) throws SQLException{
        String name="fail to find name of product !";
        String sql = "SELECT DESCRIPTION FROM PRODUCT WHERE PRODUCT_ID=?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setInt(1, Num);
                        ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("DESCRIPTION");
				
				
			
			}
		}   
                
                return name;
                
                
                }
           public int getProductIDByDesc (String desc) throws SQLException{
        int id=0;
        String sql = "SELECT PRODUCT_ID FROM PRODUCT WHERE DESCRIPTION=?";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, desc);
                        ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("PRODUCT_ID");
				
				
			
			}
		}   catch(Exception e){e.printStackTrace();}
                
                return id;
                
                
                }
            public int OrderNumGenerator () throws SQLException{
                int orderNum=0;
               String sql = "SELECT MAX(ORDER_NUM) AS MM FROM PURCHASE_ORDER";
               try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        ResultSet rs=stmt.executeQuery();
			while (rs.next()) {
				orderNum = rs.getInt("MM");
				
				
			
			}
		}   catch(Exception e){e.printStackTrace();}
                return orderNum+1;

            }
            
            //liste [[nom],[Chiffre affaire ]] des chiffre d'affaires par client
            public ArrayList ClientChiffre(String dateDeb,String dateFin)throws SQLException, ParseException {
                ArrayList result=new ArrayList();
                    ArrayList resultNom =new ArrayList();
                    ArrayList resultCa =new ArrayList();
                    String sql = "SELECT NAME,SUM(QUANTITY)AS TOTQUANTITY,SUM((PURCHASE_COST*QUANTITY))AS TOTAL FROM CUSTOMER INNER JOIN PURCHASE_ORDER ON CUSTOMER.CUSTOMER_ID=PURCHASE_ORDER.CUSTOMER_ID AND SALES_DATE BETWEEN ? AND ? INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID=PURCHASE_ORDER.PRODUCT_ID GROUP BY NAME"; 
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");//il faut faire passer le format de date String en format comprehensible pat SQL
                        java.util.Date date = sdf1.parse(dateDeb);           //on utilise donc SimpleDateFormat pour faire passer en DATE SQL
                         java.util.Date datef = sdf1.parse(dateFin);
                        java.sql.Date sqldateDeb = new java.sql.Date(date.getTime()); 
                        java.sql.Date sqldateFin = new java.sql.Date(datef.getTime()); 
                        stmt.setDate(1, sqldateDeb);
                        stmt.setDate(2, sqldateFin);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String nom = rs.getString("NAME");
				Double caDouble = rs.getDouble("TOTAL");
                                
                  //creation de l'objet chiffreaffaireClientObjet
				ChiffreAffaireClientObj cac = new ChiffreAffaireClientObj(nom,caDouble);
				resultNom.add(cac.nom);
                               	resultCa.add(cac.ca);

			}
		}
                result.add(resultNom);
                result.add(resultCa);

		return result;
                }
            public ArrayList ProduitChiffre(String dateDeb,String dateFin)throws SQLException, ParseException {
                ArrayList result=new ArrayList();
                    ArrayList resultProduit =new ArrayList();
                    ArrayList resultCa =new ArrayList();
                    String sql ="SELECT PRODUCT.DESCRIPTION,SUM(PURCHASE_ORDER.QUANTITY*PRODUCT.PURCHASE_COST) AS CA FROM PURCHASE_ORDER,PRODUCT WHERE PURCHASE_ORDER.PRODUCT_ID=PRODUCT.PRODUCT_ID AND PURCHASE_ORDER.SALES_DATE BETWEEN ? AND ? GROUP BY PRODUCT.DESCRIPTION";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");//il faut faire passer le format de date String en format comprehensible pat SQL
                        java.util.Date date = sdf1.parse(dateDeb);           //on utilise donc SimpleDateFormat pour faire passer en DATE SQL
                         java.util.Date datef = sdf1.parse(dateFin);
                        java.sql.Date sqldateDeb = new java.sql.Date(date.getTime()); 
                        java.sql.Date sqldateFin = new java.sql.Date(datef.getTime()); 
                        stmt.setDate(1, sqldateDeb);
                        stmt.setDate(2, sqldateFin);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String produit = rs.getString("DESCRIPTION");
				Double caDouble = rs.getDouble("CA");
                                
                  //creation de l'objet chiffreaffaireClientObjet
				ChiffreAffaireProduitObj cac = new ChiffreAffaireProduitObj(produit,caDouble);
				resultProduit.add(cac.produit);
                               	resultCa.add(cac.ca);

			}
		}
                result.add(resultProduit);
                result.add(resultCa);

		return result;
                }
            public ArrayList StateChiffre(String dateDeb,String dateFin)throws SQLException, ParseException {
                ArrayList result=new ArrayList();
                    ArrayList resultState =new ArrayList();
                    ArrayList resultCa =new ArrayList();
                    String sql ="SELECT CUSTOMER.STATE,SUM(PURCHASE_ORDER.QUANTITY*PRODUCT.PURCHASE_COST) AS CA FROM PURCHASE_ORDER INNER JOIN PRODUCT ON PRODUCT.PRODUCT_ID=PURCHASE_ORDER.PRODUCT_ID AND PURCHASE_ORDER.SALES_DATE BETWEEN ? AND ? INNER JOIN CUSTOMER ON CUSTOMER.CUSTOMER_ID=PURCHASE_ORDER.CUSTOMER_ID GROUP BY CUSTOMER.STATE";
		try (Connection connection = myDataSource.getConnection(); 
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
                     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");//il faut faire passer le format de date String en format comprehensible pat SQL
                        java.util.Date date = sdf1.parse(dateDeb);           //on utilise donc SimpleDateFormat pour faire passer en DATE SQL
                         java.util.Date datef = sdf1.parse(dateFin);
                        java.sql.Date sqldateDeb = new java.sql.Date(date.getTime()); 
                        java.sql.Date sqldateFin = new java.sql.Date(datef.getTime()); 
                        stmt.setDate(1, sqldateDeb);
                        stmt.setDate(2, sqldateFin);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String state = rs.getString("STATE");
				Double caDouble = rs.getDouble("CA");
                                
                  //creation de l'objet chiffreaffaireClientObjet
				ChiffreAffaireStateObj cac = new ChiffreAffaireStateObj(state,caDouble);
				resultState.add(cac.state);
                               	resultCa.add(cac.ca);

			}
		}
                result.add(resultState);
                result.add(resultCa);

		return result;
                }
           
           
}
