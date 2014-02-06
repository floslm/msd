package net.cofares.control;

import net.cofares.CritereDemandeur;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.CritereDemandeurFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("critereDemandeurController")
@SessionScoped
public class CritereDemandeurController implements Serializable {

    @EJB
    private net.cofares.sb.CritereDemandeurFacade ejbFacade;
    private List<CritereDemandeur> items = null;
    private CritereDemandeur selected;

    public CritereDemandeurController() {
    }

    public CritereDemandeur getSelected() {
        return selected;
    }

    public void setSelected(CritereDemandeur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getCritereDemandeurPK().setIdtType(selected.getType().getIdType());
        selected.getCritereDemandeurPK().setIdDemandeur(selected.getDemandeur().getIdDemandeur());
    }

    protected void initializeEmbeddableKey() {
        selected.setCritereDemandeurPK(new net.cofares.CritereDemandeurPK());
    }

    private CritereDemandeurFacade getFacade() {
        return ejbFacade;
    }

    public CritereDemandeur prepareCreate() {
        selected = new CritereDemandeur();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CritereDemandeurCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CritereDemandeurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CritereDemandeurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CritereDemandeur> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<CritereDemandeur> itemsByIdDemandeur(int iddem) {
        List<CritereDemandeur> ritems = getFacade().findByIdDemandeur(iddem);      
        return ritems;
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

    public CritereDemandeur getCritereDemandeur(net.cofares.CritereDemandeurPK id) {
        return getFacade().find(id);
    }

    public List<CritereDemandeur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CritereDemandeur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CritereDemandeur.class)
    public static class CritereDemandeurControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CritereDemandeurController controller = (CritereDemandeurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "critereDemandeurController");
            return controller.getCritereDemandeur(getKey(value));
        }

        net.cofares.CritereDemandeurPK getKey(String value) {
            net.cofares.CritereDemandeurPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new net.cofares.CritereDemandeurPK();
            key.setIdDemandeur(Integer.parseInt(values[0]));
            key.setIdtType(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(net.cofares.CritereDemandeurPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdDemandeur());
            sb.append(SEPARATOR);
            sb.append(value.getIdtType());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CritereDemandeur) {
                CritereDemandeur o = (CritereDemandeur) object;
                return getStringKey(o.getCritereDemandeurPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CritereDemandeur.class.getName()});
                return null;
            }
        }

    }

}
