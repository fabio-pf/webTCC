
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Tipo_Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class TipoUsuarioDAO<T> extends DAOGenerico<Tipo_Usuario> implements Serializable {

    public TipoUsuarioDAO(){
        super();
        super.classePersistente = Tipo_Usuario.class;
        ordem = "nome";
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }
    
   
            
  
}
