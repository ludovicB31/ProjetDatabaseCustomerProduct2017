/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.sql.DataSource;

/**
 *
 * @author calver03
 */
public class Commande {
    private int orderNum;
    private java.sql.Date dateCommand;
    private int customerId;
    private int quantity;
    private int productId;
    private float shippingCost;
    private String salesDate;
    private String shippingDate;
    private  String freightCompany;
    
    public  Commande(int customer,int quantity,int productId,int orderN,String freightCompany,float shippingCoast,Date date){
        this.dateCommand = new java.sql.Date(date.getYear(),date.getMonth(),date.getDay());
        this.customerId=customer;
        this.quantity=quantity;
        this.productId=productId;
        this.orderNum=orderN;
        this.freightCompany=freightCompany;
        this.shippingCost=shippingCoast;
        this.salesDate= date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
        this.shippingDate= date.getYear()+"-"+date.getMonth()+"-"+date.getDate();
        
        
    }
    public String getOrderNum(){
        
         return String.valueOf(this.orderNum);}
     
     public String getQuantity(){
        
         return String.valueOf(this.quantity);}
      public String getDate(){
        
         return String.valueOf(this.dateCommand);}
      
      public String getPrice(){
        
         return String.valueOf(this.shippingCost);}
       public String getName() throws SQLException{
            DataSource myDataSource = DataSourceFactory.getDataSource();
            DAOprojet myDAO = new DAOprojet(myDataSource);
            String name=myDAO.getProductByNum(productId);
         return name;}
       
       public String getTotalPrice() throws SQLException{
            DataSource myDataSource = DataSourceFactory.getDataSource();
            DAOprojet myDAO = new DAOprojet(myDataSource);
            Double total=myDAO.getCommandPrice(productId, quantity);
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
            String stringTotal=df.format(total);
            return stringTotal;}
       
       public String getLivreur(){
        
         return this.freightCompany;}
       
       
        public float getShippingCost(){
         return this.shippingCost;}
        public java.sql.Date getCommandDate(){
         return this.dateCommand;}
       
  //on ajoute les retour en format int pour la creation de la commande en SQL
       public int getOrderNumInt(){
         return this.orderNum;}
        public int getCustomerIdInt(){
         return this.customerId;}
         public int getProductIdInt(){
         return this.productId;}
         public int getQuantityInt(){
         return this.quantity;}

}

  


