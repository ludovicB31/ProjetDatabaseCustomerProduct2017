/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

/**
 *
 * @author julien
 */
public class Produit {
	private int idProduit;
        private int quantite;

	public Produit(int idProduit, int quantite) {
		this.idProduit = idProduit;
		this.quantite = quantite;
	}


	public int getIdProduit() {
		return idProduit;
	}

	public int getQuantite() {
		return quantite;
	}
        
        public int addQuantite(int x) {
            return this.quantite + x;
        }
}

