/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.Demandeur;

/**
 *
 * @author pfares
 */
@Stateless
public class DemandeurFacade extends AbstractFacade<Demandeur> {
    @PersistenceContext(unitName = "offreDemandePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandeurFacade() {
        super(Demandeur.class);
    }
    
}
