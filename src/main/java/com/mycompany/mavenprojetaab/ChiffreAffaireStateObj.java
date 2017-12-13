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
class ChiffreAffaireStateObj {
   public String state;
    public Double ca;
    public ChiffreAffaireStateObj(String n,Double c){
    this.state=n;
    this.ca=c;}
    public String State(){
         return this.state;}
     public Double CA(){
         return this.ca;} 
}
