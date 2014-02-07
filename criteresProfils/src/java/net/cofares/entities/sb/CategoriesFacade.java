/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.cofares.entities.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import net.cofares.entities.Categories;
import net.cofares.entities.Profil;

/**
 *
 * @author pfares
 */
@Stateless
public class CategoriesFacade extends AbstractFacade<Categories> {

    @PersistenceContext(unitName = "criteresProfilsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriesFacade() {
        super(Categories.class);
    }

    public List<Categories> findHeadCategories() {
        TypedQuery<Categories> query
                = em.createNamedQuery("Categories.findHeadCategories", Categories.class);
        List<Categories> results = query.getResultList();
        return results;
    }
    
    public List<Categories> findSubCategories(Categories id) {
        TypedQuery<Categories> query
                = em.createNamedQuery("Categories.findSubCategories", Categories.class);
        query.setParameter("idCategories", id);
        List<Categories> results = query.getResultList();
        return results;
    }
    
    public List<Categories> findProfilHeadCategories(Profil id) {
        TypedQuery<Categories> query
                = em.createNamedQuery("Categories.findProfilHeadCategories", Categories.class);
        query.setParameter("profil", id);
        List<Categories> results = query.getResultList();
        return results;
    }

}
