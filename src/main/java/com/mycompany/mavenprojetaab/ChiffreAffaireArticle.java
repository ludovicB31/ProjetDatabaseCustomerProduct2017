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
public class ChiffreAffaireArticle {
    private String art;
    private float ca;
    
    public ChiffreAffaireArticle(String art ,float ca){
        this.art=art;
        this.ca=ca;
    }
    
    public float getCa(){
        return this.ca;
    }
    
    public String getArticle(){
        return this.art;
    }
}
