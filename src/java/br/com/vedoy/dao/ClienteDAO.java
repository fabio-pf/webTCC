
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Clientes;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class ClienteDAO<T> extends DAOGenerico<Clientes> implements Serializable {

    public ClienteDAO(){
        super();        
        super.classePersistente = Clientes.class;
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Clientes obj = (Clientes)em.createQuery("from Usuarios where upper(usuarios) = :usuarios").
                setParameter("usuarios", usuario.toUpperCase()).getSingleResult();
        obj.getTipos_usuario().size();
        return obj;
    }
    
    @Override
    public Clientes getObjectById(Integer id) throws Exception {
        Clientes obj = (Clientes) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
}
