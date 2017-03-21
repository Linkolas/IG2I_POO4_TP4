/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier;

import java.util.Collection;
import tp_poo4_4.dao.DaoFactory;

/**
 *
 * @author Nicolas
 */
public class Ordonnanceur {
    
    public static boolean ordonnancer() {
        
        Collection<Tache> taches =
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getTacheDao()
                    .findAllNotScheduled();
        
        if(taches.isEmpty()) {
            return true;
        }
        
        Collection<Atelier> ateliers = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getAtelierDao()
                    .findAll();
        
        if(ateliers.isEmpty()) {
            return false;
        }
        
        // choisir un atelier dont la dispo est minimale
        // ordonnancer les tâches :
        //      temps max minimal
        //      pénalités max
        
        Atelier atelierFirst = null;
        for(Atelier a: ateliers) {
            if(a.getMachineCollection().isEmpty()) {
                continue;
            }
            
            if(atelierFirst == null) {
                atelierFirst = a;
                continue;
            }
            
            if(atelierFirst.getDatedispo().after(a.getDatedispo())) {
                atelierFirst = a;
            }
        }
        
        if(atelierFirst == null) {
            return false;
        }
        
        int nbMachines = atelierFirst.getMachineCollection().size();
        
        
        
        return true;
    }
    
}
