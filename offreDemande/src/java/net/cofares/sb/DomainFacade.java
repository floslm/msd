/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.cofares.Domain;

/**
 *
 * @author pfares
 */
@Stateless
public class DomainFacade extends AbstractFacade<Domain> {
    @PersistenceContext(unitName = "offreDemandePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DomainFacade() {
        super(Domain.class);
    }
    
}
