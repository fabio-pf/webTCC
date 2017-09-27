
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Tecnicos;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;
import java.util.List;


@Stateful
public class TecnicoDAO<T> extends DAOGenerico<Tecnicos> implements Serializable {

    public TecnicoDAO(){
        super();
        super.setClassePersistente(Tecnicos.class);
        super.setOrdem("nome");
    }
    
    public Usuarios localizaPorNomeUsuario(String usuarios) {
        Tecnicos obj = (Tecnicos)em.createQuery("from Usuarios where upper(usuarios) = :usuarios").
                setParameter("usuario", usuarios.toUpperCase()).getSingleResult();
        obj.getTipos_usuario().size();
        return obj;
    }
    
    @Override
    public Tecnicos getObjectById(Integer id) throws Exception {
        Tecnicos obj = (Tecnicos) super.getEm().find(super.getClassePersistente(), id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }    
}
