
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.Stateful;


@Stateful
public class UsuarioDAO<T> extends DAOGenerico<Usuarios> implements Serializable {

    public UsuarioDAO(){
        super();        
        super.classePersistente = Usuarios.class;
    }
    
    public Usuarios localizaPorNomeUsuario(String usuario) {
        Usuarios obj = (Usuarios)em.createQuery("from Usuarios where usuario = :nome").
                setParameter("nome", usuario).getSingleResult();
        System.out.println("objeto " +obj);
        return obj;
    }
    
    @Override
    public Usuarios getObjectById(Integer id) throws Exception {
        Usuarios obj = (Usuarios) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
}
