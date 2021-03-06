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
import javax.inject.Named;
import net.cofares.CritereDemandeur;
import net.cofares.Demandeur;
import net.cofares.Offrant;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.OffrantFacade;
import net.cofares.util.Distances;

@Named("offrantController")
@SessionScoped
public class OffrantController implements Serializable {

    @EJB
    private net.cofares.sb.OffrantFacade ejbFacade;
    private List<Offrant> items = null;
    private Offrant selected;

    public OffrantController() {
    }

    public Offrant getSelected() {
        return selected;
    }

    public void setSelected(Offrant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OffrantFacade getFacade() {
        return ejbFacade;
    }

    public Offrant prepareCreate() {
        selected = new Offrant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OffrantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OffrantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OffrantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Offrant> getItems() {
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

    public Offrant getOffrant(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Offrant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Offrant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
    
   
     public int distance(Offrant o, Demandeur d) {
       return Distances.getDistance(d.getCritereDemandeurCollection(), o.getCritereOffrantCollection());
    }

    @FacesConverter(forClass = Offrant.class)
    public static class OffrantControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OffrantController controller = (OffrantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "offrantController");
            return controller.getOffrant(getKey(value));
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
            if (object instanceof Offrant) {
                Offrant o = (Offrant) object;
                return getStringKey(o.getIdOffrant())+o.getInformations();
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Offrant.class.getName()});
                return null;
            }
        }

    }

}
