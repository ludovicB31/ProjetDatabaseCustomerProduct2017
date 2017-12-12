/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ludovicblanc
 */
public class tester {
    
    
    public static void main(String[] args) throws SQLException{
        DAOprojet mydao=new DAOprojet(DataSourceFactory.getDataSource());
        ArrayList<ChiffreAffaireClientObj> cac=null;
        ArrayList cacTO =new ArrayList();
        ArrayList cols =new ArrayList();
        ArrayList rows=new ArrayList();
        
             
            
            
    
        
             String jsonClient = new Gson().toJson(cac);
             System.out.print(jsonClient);
    
    }}
