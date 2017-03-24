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
import tp_poo4_4.metier.Ordonnanceur;

/**
 *
 * @author Nicolas
 */
public class OrdoAtelier {
    private List<OrdoMachine> machines = new ArrayList<>();
    private List<OrdoTache> taches = new ArrayList<>();
    public Date dateDispoInitiale = Date.from(Instant.now());
    
    public OrdoAtelier() {
        this(2);
    }
    
    public OrdoAtelier(int nbMachines) {
        this(nbMachines, Date.from(Instant.now()));
    }
    
    public OrdoAtelier(int nbMachines, Date dateDispoInitiale) {
        this.dateDispoInitiale = dateDispoInitiale;
        
        if(nbMachines <= 0) {
            nbMachines = 1;
        }
        
        while(nbMachines > 0) {
            machines.add(new OrdoMachine(dateDispoInitiale));
            nbMachines--;
        }
    }
    
    public void setMachines(int number) {
        while(number > machines.size()) {
            delMachine();
        }
        while(number < machines.size()) {
            addMachine();
        }
    }
    
    public void addMachine() {
        machines.add(new OrdoMachine(dateDispoInitiale));
    }
    
    public void delMachine() {
        if(machines.size() > 1) {
            machines.remove(1);
        }
    }
    
    public int getMachinesSize() {
        return machines.size();
    }

    public List<OrdoTache> getTaches() {
        return taches;
    }

    public void setTaches(List<OrdoTache> taches) {
        this.taches = taches;
    }
    
    public OrdoMachine getMachineDispoFirst() {
        OrdoMachine retour = machines.get(0);
        for(OrdoMachine m: machines) {
            if(m.getTempsTotal() < retour.getTempsTotal()) {
                retour = m;
            }
        }
        return retour;
    }
    
    public void JeuDeTest() {
        System.out.println("Lancement du jeu de tests.");
        Test1();
        Test2();
    }
    
    private void Test1() {
        System.out.println("-- -- -- Test 1 -- -- --");
        
        setMachines(2);
        System.out.println("Atelier à " + machines.size() + " machines.");
        
        taches.add(
            new OrdoTache(1, 1, Ordonnanceur.DateAdd(dateDispoInitiale, -5), 1));
        taches.add(
            new OrdoTache(2, 1, Ordonnanceur.DateAdd(dateDispoInitiale,  1), 1));
        taches.add(
            new OrdoTache(3, 1, Ordonnanceur.DateAdd(dateDispoInitiale,  1), 2));
        taches.add(
            new OrdoTache(4, 6, Ordonnanceur.DateAdd(dateDispoInitiale,  7), 2));
        taches.add(
            new OrdoTache(5, 4, Ordonnanceur.DateAdd(dateDispoInitiale, 12), 2));
        taches.add(
            new OrdoTache(6, 1, Ordonnanceur.DateAdd(dateDispoInitiale, 12), 2));
        
        System.out.println("Il y a " + taches.size() + " tâches.");
        
        Collections.sort(taches, new Comparator<OrdoTache>() {
            @Override
            public int compare(OrdoTache o1, OrdoTache o2) {
                int retour = 0;
                
                retour =        Ordonnanceur.DateAdd(o1.getDateLimite(), new Double(-o1.getPenalite()).intValue())
                    .compareTo( Ordonnanceur.DateAdd(o2.getDateLimite(), new Double(-o1.getPenalite()).intValue())
                    );
                
                if(retour == 0) {
                    retour = Double.compare(o2.getPenalite(), o1.getPenalite());
                }
                
                return retour;
            }
        });
        
        System.out.println("");
        System.out.println("Tri des tâches pour attribution machine : T1 T3 T2 T4 T5 T6");
        for(OrdoTache t: taches) {
            System.out.println("T" + t.getId() + " - lim " + t.getDateLimite() + " - temps " + t.getTemps() + " - pén " + t.getPenalite());
        }
        
        for(OrdoTache t: taches) {
            getMachineDispoFirst().taches.add(t);
        }
        
        System.out.println("");
        System.out.println("Attribution des tâches : 1 = T1 T2 T5 T6 / 2 = T3 T4");
        for(OrdoMachine m: machines) {
            System.out.println("Machine TExec " + m.getTempsTotal());
            
            for(OrdoTache t: m.taches) {
                System.out.println("T" + t.getId() + " - lim " + t.getDateLimite() + " - temps " + t.getTemps() + " - pén " + t.getPenalite());
            }
        }
        
        
        System.out.println("");
        System.out.println("Tri des tâches par machine : 1 = T2 T5 T6 T1 / 2 = T3 T4");
        for(OrdoMachine m: machines) {
            m.trierTaches();
            System.out.println("Machine TExec " + m.getTempsTotal());
            
            for(OrdoTache t: m.taches) {
                System.out.println("T" + t.getId() + " - lim " + t.getDateLimite() + " - temps " + t.getTemps() + " - pén " + t.getPenalite());
            }
        }
        
    }
    
    private void Test2() {
        
    }
}

/*

Collections.sort(ltd, (Tache o1, Tache o2) -> (
new Date(o1.getDatelimite().getTime() - (o1.getTempsprod()*60*1000))).compareTo(new Date(o2.getDatelimite().getTime() - (o2.getTempsprod()*60*1000))));

*/