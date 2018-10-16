/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import modele.Client;
import modele.Pays;

/**
 *
 * @author Zakina
 */
public class ClientTest {
    
    public static void main(String[] args) {
        
        Client unClient = new Client(null, 1, "dupont", "luc", "", "", "", "", false, null, null);
        unClient.setPays(new Pays("FRA", "FRANCE", null));     
        System.out.println(unClient.getNom() + "  " + unClient.getPays().getNom());
        
        
    }
    
}
