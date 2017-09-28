
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Atendente;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class AtendenteDAO<T> extends DAOGenerico<Atendente> implements Serializable {

    public AtendenteDAO(){
        super();        
        super.classePersistente = Usuarios.class;
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Atendente obj = (Atendente)em.createQuery("from Usuarios where upper(usuarios) = :usuarios").
                setParameter("usuarios", usuario.toUpperCase()).getSingleResult();
        obj.getTipos_usuario().size();
        return obj;
    }
    
    @Override
    public Atendente getObjectById(Integer id) throws Exception {
        Atendente obj = (Atendente) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
}
