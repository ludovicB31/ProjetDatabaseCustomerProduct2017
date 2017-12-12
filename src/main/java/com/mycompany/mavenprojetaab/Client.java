/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojetaab;

/**
 *
 * @author jancel
 */
public class Client {

	private String nomClient;

	private int idClient;

	public Client(String nomClient, int idClient) {
		this.nomClient = nomClient;
		this.idClient = idClient;
	}


	public String getNomClient() {
		return nomClient;
	}

	public int getIdClient() {
		return idClient;
	}

}
