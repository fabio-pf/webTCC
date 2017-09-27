
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Tecnicos;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class TecnicoDAO<T> extends DAOGenerico<Tecnicos> implements Serializable {

    public TecnicoDAO(){
        super();        
        super.classePersistente = Tecnicos.class;
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Tecnicos obj = (Tecnicos)em.createQuery("from Usuarios where upper(usuarios) = :usuarios").
                setParameter("usuarios", usuario.toUpperCase()).getSingleResult();
        obj.getTipos_usuario().size();
        return obj;
    }
    
    @Override
    public Tecnicos getObjectById(Integer id) throws Exception {
        Tecnicos obj = (Tecnicos) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
}
