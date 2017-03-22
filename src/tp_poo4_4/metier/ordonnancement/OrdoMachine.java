/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier.ordonnancement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class OrdoMachine {
    private List<OrdoTache> taches = new ArrayList<>();
    private Date dateDispoInitiale = Date.from(Instant.now());
    
    public OrdoMachine(Date dateDispoInitiale) {
        this.dateDispoInitiale = dateDispoInitiale;
    }
    
    public int getTempsTotal() {
        int retour = 0;
        
        for(OrdoTache ot: taches) {
            retour += ot.getTemps();
        }
        
        return retour;
    }
    
    public void trierTaches() {
        ArrayList<OrdoTache> tachesRestantes = new ArrayList<>(taches);
        ArrayList<OrdoTache> tachesEnRetard = new ArrayList<>();
        
        Collections.sort(tachesRestantes, new Comparator<OrdoTache>() {
            @Override
            public int compare(OrdoTache o1, OrdoTache o2) {
                int retour = o1.getDateLimite().compareTo(o2.getDateLimite());
                
                if(retour == 0) {
                    retour = - Double.compare(o1.getPenalite(), o2.getPenalite());
                }
                
                return retour;
            }
        });
        
        while(tachesRestantes.size() > 0) {
            // retirer les taches dépassées
            
        }
    }
}
