package net.cofares.entities.control;

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
import net.cofares.entities.Categories;
import net.cofares.entities.Profil;
import net.cofares.entities.control.util.JsfUtil;
import net.cofares.entities.control.util.JsfUtil.PersistAction;
import net.cofares.entities.sb.CategoriesFacade;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named("categoriesController")
@SessionScoped
public class CategoriesController implements Serializable {

    @EJB
    private net.cofares.entities.sb.CategoriesFacade ejbFacade;
    private List<Categories> items = null;
    TreeNode treeItems = null;
    private Categories selected;
    private Integer idProfil;
    private TreeNode treeSelected;

    public CategoriesController() {
    }

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
            profilController.setSelected(null);
        } else {
            profilController.setSelected(profilController.getProfil(idProfil));
        }

    }

    /**
     * @return the treeSelected
     */
    public TreeNode getTreeSelected() {
        return treeSelected;
    }

    /**
     * @param treeSelected the treeSelected to set
     */
    public void setTreeSelected(TreeNode treeSelected) {
        this.treeSelected = treeSelected;
        this.selected = (Categories) treeSelected.getData();
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
    @Inject
    ProfilController profilController;

    public Categories prepareCreate() {
        Categories prep = new Categories();

        //if (profilController.getSelected() != null) prep.setPourProfil(profilController.getSelected());
        //prep.setIdCategories(selected.getIdCategories() * 10);
        //if (this.selected != null) prep.setCategorieParente(this.selected);
        initializeEmbeddableKey();
        this.selected = prep;
        return this.selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CategoriesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CategoriesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CategoriesDeleted"));
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
        List<Categories> lcRacine = headCategories(profilController.getSelected());
        treeItems = new DefaultTreeNode("root", null);
        _subItems(lcRacine, treeItems);
        return treeItems;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
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
