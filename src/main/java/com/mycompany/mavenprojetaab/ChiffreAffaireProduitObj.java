/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

/**
 *
 * @author calver03
 */
class ChiffreAffaireProduitObj {

    public String produit;
    public Double ca;
    public ChiffreAffaireProduitObj(String n ,Double c){
    this.produit=n;
    this.ca=c;}
    
    
    public String State(){
         return this.produit;}
     public Double CA(){
         return this.ca;} 
    }