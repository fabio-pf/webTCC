
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
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
        return obj;
    }
    
    @Override
    public Usuarios getObjectById(Integer id) throws Exception {
        Usuarios obj = (Usuarios) em.find(classePersistente, id);
        obj.getTipos_usuario().size(); // inicializa a lista de permissões do usuário
        return obj;
    }
    
    @Override
    public List<Usuarios> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        String where = "";
        // limpando o filtro contra injeção de SQL
        filtro = filtro.replaceAll("[';-]","");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                }catch (Exception e){}
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by "+ordem;
        totalObjetos = em.createQuery("select usuario from " + classePersistente.getSimpleName() +
                where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
    
    
}
