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
    
    public static void ordonnancer() {
        
        Collection<Tache> taches =
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getTacheDao()
                    .findAllNotScheduled();
        
        // choisir un atelier dont la dispo est minimale
        // ordonnancer les tâches :
        //      temps max minimal
        //      pénalités max
        
        
        
    }
    
}
