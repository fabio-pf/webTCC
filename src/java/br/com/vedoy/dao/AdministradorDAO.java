
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Administrador;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class AdministradorDAO<T> extends DAOGenerico<Administrador> implements Serializable {

    public AdministradorDAO(){
        super();        
        super.classePersistente = Administrador.class;
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Administrador obj = (Administrador)em.createQuery("from Usuarios where upper(usuarios) = :usuarios").
                setParameter("usuarios", usuario.toUpperCase()).getSingleResult();
        obj.getTipos_usuario().size();
        return obj;
    }
    
    @Override
    public Administrador getObjectById(Integer id) throws Exception {
        Administrador obj = (Administrador) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
}
