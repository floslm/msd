/*
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
import net.cofares.CritereDemandeur;

/**
 *
 * @author pfares
 */
@Stateless
public class CritereDemandeurFacade extends AbstractFacade<CritereDemandeur> {

    @PersistenceContext(unitName = "offreDemandePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CritereDemandeurFacade() {
        super(CritereDemandeur.class);
    }

    public List<CritereDemandeur> findByIdDemandeur(Integer iddemandeur) {
        TypedQuery<CritereDemandeur> query
                = em.createNamedQuery("CritereDemandeur.findByIdDemandeur", CritereDemandeur.class);
        query.setParameter("idDemandeur", iddemandeur);
        List<CritereDemandeur> results = query.getResultList();
        return results;
    }

}
