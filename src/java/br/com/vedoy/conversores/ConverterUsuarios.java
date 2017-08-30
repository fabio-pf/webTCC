/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.conversores;

import br.com.vedoy.modelo.Categorias;
import br.com.vedoy.modelo.Cotas;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fabio V
 */
@FacesConverter(value = "converterUsuarios")
public class ConverterUsuarios implements Serializable, Converter {
    
    @PersistenceContext(unitName = "OSWebPU")
    private EntityManager em;    

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Usuarios.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null){
            return null;
        }
        Usuarios obj = (Usuarios) o;
        return obj.getId().toString();
    }

}
