/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.util;

import java.util.Collection;
import java.util.List;
import net.cofares.CritereDemandeur;
import net.cofares.CritereOffrant;
import net.cofares.Demandeur;
import net.cofares.Offrant;

/**
 *
 * @author pfares
 */
public class Distances {
    
    /**
     * Valeur de matching
     * @param cd
     * @param co
     * @return 0 pas de matching, 100 matching exact, <100 moins, > 100 mieux
     */
    public static int getDistance(CritereDemandeur cd, CritereOffrant co){
        int rd=0;
        int idTypeDemandeur = cd.getCritereDemandeurPK().getIdtType();
        int idTypeOffrant = co.getCritereOffrantPK().getIdtType();
        if (idTypeDemandeur == idTypeOffrant) return 100+(cd.getValeur()-co.getValeur());
        return rd;
    }
    /**
     * 
     * @param lcd liste critères demandeur d'un SEUL demandeur
     * @param lco liste critères offrant d'un SEUL offrant
     * @return 
     */
    public static int getDistance(Collection<CritereDemandeur> lcd,Collection< CritereOffrant> lco){
        int rd=0;
        for (CritereDemandeur cd: lcd){
            for(CritereOffrant co : lco) {
                rd += getDistance(cd, co);
            }
        }
        return rd;
    }
    
}
