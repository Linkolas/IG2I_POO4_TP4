/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier.ordonnancement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import tp_poo4_4.metier.Ordonnanceur;

/**
 *
 * @author Nicolas
 */
public class OrdoMachine {
    public List<OrdoTache> taches = new ArrayList<>();
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
    
    public Date getDateDispo() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateDispoInitiale);
        
        for(OrdoTache t: taches) {
            cal.add(Calendar.MINUTE, t.getTemps());
        }
        
        return cal.getTime();
    }
    
    public double getPenalites() {
        double penalite = 0;
        long retard = 0;
        Date finTache = new Date(dateDispoInitiale.getTime());
        
        for(OrdoTache t: taches) {
            finTache = Ordonnanceur.DateAdd(finTache, t.getTemps());
            retard = TimeUnit.MILLISECONDS.toMinutes(
                    finTache.getTime() - t.getDateLimite().getTime());
            
            if(retard > 0) {
                penalite += retard * t.getPenalite();
            }
        }
        
        return penalite;
    }
    
    public void trierTaches() {
        ArrayList<OrdoTache> tachesRestantes = new ArrayList<>(taches);
        ArrayList<OrdoTache> tachesEnRetard = new ArrayList<>();
        
        Collections.sort(tachesRestantes, new Comparator<OrdoTache>() {
            @Override
            public int compare(OrdoTache o1, OrdoTache o2) {
                int retour = 0;
                
                retour =        Ordonnanceur.DateAdd(o1.getDateLimite(), -o1.getTemps())
                    .compareTo( Ordonnanceur.DateAdd(o2.getDateLimite(), -o2.getTemps())
                    );
                
                if(retour == 0) {
                    retour = Double.compare(o2.getPenalite(), o1.getPenalite());
                }
                
                return retour;
            }
        });
        
        taches = new ArrayList<>();
        
        while(tachesRestantes.size() > 0) {
            OrdoTache tache = tachesRestantes.get(0);
            tachesRestantes.remove(tache);
            
            // retirer les taches dépassées
            Date dateMax = Ordonnanceur.DateAdd(getDateDispo(), tache.getTemps());
            if(tache.getDateLimite().before(dateMax)) {
                // La date limite sera dépassée.
                // On aura de toute manière la pénalité, 
                // autant prioriser les autres tâches !
                tachesEnRetard.add(tache);
                continue;
            }
            
            taches.add(tache);
        }
        
        taches.addAll(tachesEnRetard);
    }
    
    public void trierTaches2() {
        List<OrdoTache> taches = new ArrayList<>(this.taches);
        this.taches.clear();
        
        for(OrdoTache t: taches) {
            t.retardPrevu = 0;
        }
        
        while(taches.size() > 0) {
            
        }
    }
}
