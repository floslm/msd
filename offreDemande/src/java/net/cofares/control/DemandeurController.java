package net.cofares.control;

import net.cofares.Demandeur;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.DemandeurFacade;

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
import net.cofares.Offrant;
import net.cofares.util.Distances;

@Named("demandeurController")
@SessionScoped
public class DemandeurController implements Serializable {

    @EJB
    private net.cofares.sb.DemandeurFacade ejbFacade;
    private List<Demandeur> items = null;
    private Demandeur selected;

    public DemandeurController() {
    }

    public Demandeur getSelected() {
        return selected;
    }

    public void setSelected(Demandeur selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DemandeurFacade getFacade() {
        return ejbFacade;
    }

    public Demandeur prepareCreate() {
        selected = new Demandeur();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DemandeurCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DemandeurUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DemandeurDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Demandeur> getItems() {
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

    public Demandeur getDemandeur(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Demandeur> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Demandeur> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public int distance(Demandeur d, Offrant o) {
        return Distances.getDistance(d.getCritereDemandeurCollection(), o.getCritereOffrantCollection());
    }

    @FacesConverter(forClass = Demandeur.class)
    public static class DemandeurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DemandeurController controller = (DemandeurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "demandeurController");
            return controller.getDemandeur(getKey(value));
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
            if (object instanceof Demandeur) {
                Demandeur o = (Demandeur) object;
                return getStringKey(o.getIdDemandeur()) + o.getInformations();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Demandeur.class.getName()});
                return null;
            }
        }

    }

}
