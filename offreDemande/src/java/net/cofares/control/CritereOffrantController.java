package net.cofares.control;

import net.cofares.CritereOffrant;
import net.cofares.control.util.JsfUtil;
import net.cofares.control.util.JsfUtil.PersistAction;
import net.cofares.sb.CritereOffrantFacade;

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

@Named("critereOffrantController")
@SessionScoped
public class CritereOffrantController implements Serializable {

    @EJB
    private net.cofares.sb.CritereOffrantFacade ejbFacade;
    private List<CritereOffrant> items = null;
    private CritereOffrant selected;

    public CritereOffrantController() {
    }

    public CritereOffrant getSelected() {
        return selected;
    }

    public void setSelected(CritereOffrant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getCritereOffrantPK().setIdOffrant(selected.getOffrant().getIdOffrant());
        selected.getCritereOffrantPK().setIdtType(selected.getType().getIdType());
    }

    protected void initializeEmbeddableKey() {
        selected.setCritereOffrantPK(new net.cofares.CritereOffrantPK());
    }

    private CritereOffrantFacade getFacade() {
        return ejbFacade;
    }

    public CritereOffrant prepareCreate() {
        selected = new CritereOffrant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CritereOffrantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CritereOffrantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CritereOffrantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CritereOffrant> getItems() {
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

    public CritereOffrant getCritereOffrant(net.cofares.CritereOffrantPK id) {
        return getFacade().find(id);
    }

    public List<CritereOffrant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CritereOffrant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CritereOffrant.class)
    public static class CritereOffrantControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CritereOffrantController controller = (CritereOffrantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "critereOffrantController");
            return controller.getCritereOffrant(getKey(value));
        }

        net.cofares.CritereOffrantPK getKey(String value) {
            net.cofares.CritereOffrantPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new net.cofares.CritereOffrantPK();
            key.setIdOffrant(Integer.parseInt(values[0]));
            key.setIdtType(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(net.cofares.CritereOffrantPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdOffrant());
            sb.append(SEPARATOR);
            sb.append(value.getIdtType());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CritereOffrant) {
                CritereOffrant o = (CritereOffrant) object;
                return getStringKey(o.getCritereOffrantPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CritereOffrant.class.getName()});
                return null;
            }
        }

    }

}
