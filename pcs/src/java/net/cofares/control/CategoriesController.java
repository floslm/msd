package net.cofares.control;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import net.cofares.Categories;
import net.cofares.Profil;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.ejb.CategoriesFacade;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named("categoriesController")
@SessionScoped
public class CategoriesController implements Serializable {

    @EJB
    private net.cofares.ejb.CategoriesFacade ejbFacade;
    private List<Categories> items = null;
    private Categories selected;
    private Categories duplicate;
    private Integer idProfil;
    private TreeNode treeSelected;
    TreeNode treeItems = null;

    @Inject
    private ProfilController profilController;

    /**
     * @return the duplicate
     */
    public Categories getDuplicate() {
        return duplicate;
    }

    /**
     * @param duplicate the duplicate to set
     */
    public void setDuplicate(Categories duplicate) {
        this.duplicate = duplicate;
    }

    /**
     * @return the profilController
     */
    public ProfilController getProfilController() {
        return profilController;
    }

    /**
     * @param profilController the profilController to set
     */
    public void setProfilController(ProfilController profilController) {
        this.profilController = profilController;
    }

    public CategoriesController() {
    }

    public Categories getSelected() {
        return selected;
    }

    public void setSelected(Categories selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CategoriesFacade getFacade() {
        return ejbFacade;
    }

    public Categories prepareDuplicate() {
        setDuplicate(new Categories());
        if (selected != null) {
            duplicate.setCategorieParente(selected);
            duplicate.setPourProfil(selected.getPourProfil());
        }
        return duplicate;
    }
    //TODO dupliquer toute la hierarchier parente 
    public void doDuplicate() {
        create(new Categories(selected));
        
        /**
        if (selected.getCategorieParente() != null) {
            selected = selected.getCategorieParente();
            doDuplicate();
            
        }
        */
    }
    public Categories prepareCreate() {
        selected = new Categories();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Categories categ){
         
        persist(categ, PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CategoriesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void create() {
        
        persist(selected, PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CategoriesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(selected, PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CategoriesUpdated"));
    }

    public void destroy() {
        persist(selected, PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CategoriesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Categories> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(Categories categ, PersistAction persistAction, String successMessage) {
        if (categ != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.CREATE) {
                    getFacade().create(categ);
                    JsfUtil.addSuccessMessage(successMessage);
                }else if (persistAction == PersistAction.UPDATE) {
                    getFacade().edit(categ);
                    JsfUtil.addSuccessMessage(successMessage);
                } else if (persistAction == PersistAction.DELETE) {
                    getFacade().remove(categ);
                    JsfUtil.addSuccessMessage(successMessage);
                } else {
                    JsfUtil.addErrorMessage("Erreur dans persistAcgtion");
                }
                
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Categories getCategories(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Categories> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Categories> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    //GENERER APRES manuellement
    //======================
    /**
     * @return the idProfil
     */
    public Integer getIdProfil() {
        return idProfil;
    }

    /**
     * @param idProfil the idProfil to set
     */
    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
        if (idProfil == null) {
            getProfilController().setSelected(null);
        } else {
            getProfilController().setSelected(getProfilController().getProfil(idProfil));
        }
    }

    /**
     * @return the treeSelected
     */
    public TreeNode getTreeSelected() {
        return treeSelected;
    }

    public List<Categories> getHeadItems() {
        return getFacade().findHeadCategories();
    }

    public List<Categories> headCategories(Profil p) {
        if (p == null) {
            return getFacade().findHeadCategories();
        } else {
            return getFacade().findProfilHeadCategories(p);
        }
    }

    public void _subItems(List<Categories> subCategories, TreeNode parent) {

        for (Categories c : subCategories) {
            TreeNode current = new DefaultTreeNode("Categorie", c, parent);
            //_subItems(c.getCategoriesList(),current);
            _subItems(ejbFacade.findSubCategories(c), current);
        }

    }

    public TreeNode getTreeItems() {
        List<Categories> lcRacine = headCategories(getProfilController().getSelected());
        treeItems = new DefaultTreeNode("root", null);
        _subItems(lcRacine, treeItems);
        return treeItems;
    }

    /**
     * @param treeSelected the treeSelected to set
     */
    public void setTreeSelected(TreeNode treeSelected) {
        this.treeSelected = treeSelected;
        this.selected = (Categories) treeSelected.getData();
    }

    @FacesConverter(forClass = Categories.class)
    public static class CategoriesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoriesController controller = (CategoriesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoriesController");
            return controller.getCategories(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Categories) {
                Categories o = (Categories) object;
                return getStringKey(o.getIdCategories());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Categories.class.getName()});
                return null;
            }
        }

    }

}
