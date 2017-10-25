
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Pessoas;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class PessoaDAO<T> extends DAOGenerico<Pessoas> implements Serializable {

    public PessoaDAO(){
        super();        
        super.classePersistente = Pessoas.class;
        ordem = "nome";
    }
    
    @Override
    public Pessoas getObjectById(Integer id) throws Exception {
        Pessoas obj = (Pessoas) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
   
}
