
package br.com.vedoy.conversores;

import br.com.vedoy.modelo.Tipo_Usuario;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@FacesConverter(value = "converterTipoUsuario")
public class ConverterTipoUsuario implements Serializable, Converter {
    
    @PersistenceContext(unitName = "OSWebPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Tipo_Usuario.class, string);
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Tipo_Usuario obj = (Tipo_Usuario) o;
        return obj.getNome();
    }

}
