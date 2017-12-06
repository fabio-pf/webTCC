
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Tipo_Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class TipoUsuarioDAO<T> extends DAOGenerico<Tipo_Usuario> implements Serializable {

    public TipoUsuarioDAO(){
        super();
        super.classePersistente = Tipo_Usuario.class;
        ordem = "nome";
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }
    
    @Override
    public List<Tipo_Usuario> getListaObjetos() {
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
        totalObjetos = em.createQuery("select nome from " + classePersistente.getSimpleName() +
                where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
   
            
  
}
