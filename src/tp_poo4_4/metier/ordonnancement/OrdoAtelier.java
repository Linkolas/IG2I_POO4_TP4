/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier.ordonnancement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class OrdoAtelier {
    private List<OrdoMachine> machines = new ArrayList<>();
    public Date dateDispoInitiale = Date.from(Instant.now());
    
    public OrdoAtelier(int nbMachines, Date dateDispoInitiale) {
        this.dateDispoInitiale = dateDispoInitiale;
        
        if(nbMachines <= 0) {
            nbMachines = 1;
        }
        
        while(nbMachines > 0) {
            machines.add(new OrdoMachine(dateDispoInitiale));
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
    
    public void JeuDeTest() {
        
    }
}
