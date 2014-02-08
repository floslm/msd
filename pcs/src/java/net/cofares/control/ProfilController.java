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
import net.cofares.ejb.ProfilFacade;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named("profilController")
@SessionScoped
public class ProfilController implements Serializable {

    @EJB
    private net.cofares.ejb.ProfilFacade ejbFacade;
    private List<Profil> items = null;
    private Profil selected;
    TreeNode treeItems = null;
    private TreeNode treeSelected;

    @Inject
    CategoriesController categoriesController;

    public ProfilController() {
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
        switch (treeSelected.getType()) {
            case "Profil":
                selected = (Profil) treeSelected.getData();
                break;
            case "Categorie":
                categoriesController.setSelected((Categories) treeSelected.getData());
                break;
        }
    }

    public TreeNode getTreeItems() {
        List<Profil> lcRacine = getItems();
        treeItems = new DefaultTreeNode("root", null);
        for (Profil p : lcRacine) {
            TreeNode current = new DefaultTreeNode("Profil", p, treeItems);
            List<Categories> lcp = categoriesController.headCategories(p);
            categoriesController._subItems(lcp, current);
        }
        //_subItems(lcRacine,treeItems);
        return treeItems;
    }

    public Profil getSelected() {
        return selected;
    }

    public void setSelected(Profil selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProfilFacade getFacade() {
        return ejbFacade;
    }

    public Profil prepareCreate() {
        selected = new Profil();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProfilCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProfilUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProfilDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Profil> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public Profil getProfil(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Profil> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Profil> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Profil.class)
    public static class ProfilControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProfilController controller = (ProfilController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "profilController");
            return controller.getProfil(getKey(value));
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
            if (object instanceof Profil) {
                Profil o = (Profil) object;
                return getStringKey(o.getIdProfil());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Profil.class.getName()});
                return null;
            }
        }

    }

}
