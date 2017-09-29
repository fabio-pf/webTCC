
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Clientes;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class ClienteDAO<T> extends DAOGenerico<Clientes> implements Serializable {

    public ClienteDAO(){
        super();        
        super.classePersistente = Clientes.class;
    }
    
    @Override
    public Clientes getObjectById(Integer id) throws Exception {
        Clientes obj = (Clientes) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }   
}
