package br.com.vedoy.dao;

import br.com.vedoy.modelo.Atendente;
import br.com.vedoy.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

@Stateful
public class AtendenteDAO<T> extends DAOGenerico<Atendente> implements Serializable {

    public AtendenteDAO() {
        super();
        super.classePersistente = Atendente.class;
    }

    @Override
    public Atendente getObjectById(Integer id) {
        try {
            Atendente obj = (Atendente) em.find(classePersistente, id);
            obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
            return obj;
            
        } catch (Exception e) {
            Util.mensagemErro("Erro ao Recuperar objeto " + Util.getMensagemErro(e));
        }
        return null;
    }
   
}
