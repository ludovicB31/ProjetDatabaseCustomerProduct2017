/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

/**
 *
 * @author ludovicblanc
 */
public class ChiffreAffaireClientObj {
    public String nom;
    public Double ca;
    public ChiffreAffaireClientObj(String n,Double c){
    this.nom=n;
    this.ca=c;}
    public String NOM(){
         return this.nom;}
     public Double CA(){
         return this.ca;}
}
