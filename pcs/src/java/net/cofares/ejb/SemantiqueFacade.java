/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.Semantique;

/**
 *
 * @author pfares
 */
@Stateless
public class SemantiqueFacade extends AbstractFacade<Semantique> {
    @PersistenceContext(unitName = "pcsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SemantiqueFacade() {
        super(Semantique.class);
    }
    
}
