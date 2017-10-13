
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
   
}
