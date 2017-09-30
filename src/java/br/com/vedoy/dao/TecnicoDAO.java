
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Tecnicos;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class TecnicoDAO<T> extends DAOGenerico<Tecnicos> implements Serializable {

    public TecnicoDAO(){
        super();
         super.classePersistente = Tecnicos.class;
        super.setOrdem("nome");
    }
    @Override
    public Tecnicos getObjectById(Integer id) throws Exception {
        Tecnicos obj = (Tecnicos) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    } 
}
