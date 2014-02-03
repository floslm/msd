/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.CritereOffrant;

/**
 *
 * @author pfares
 */
@Stateless
public class CritereOffrantFacade extends AbstractFacade<CritereOffrant> {
    @PersistenceContext(unitName = "offreDemandePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereOffrantFacade() {
        super(CritereOffrant.class);
    }
    
}
