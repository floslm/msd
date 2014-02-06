/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    
    public List<CritereOffrant> findByIdOffrant(Integer idoffrant) {
        TypedQuery<CritereOffrant> query
                = em.createNamedQuery("CritereOffrant.findByIdOffrant", CritereOffrant.class);
        query.setParameter("idOffrant", idoffrant);
        List<CritereOffrant> results = query.getResultList();
        return results;
    }
    
}
