
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Ordem_Servicos;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;


@Stateful
public class OsDAO<T> extends DAOGenerico<Ordem_Servicos> implements Serializable {

    public OsDAO(){
        super();        
        super.classePersistente = Ordem_Servicos.class;
        ordem = "nome";
    }
    
     public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }
    
    @Override
    public List<Ordem_Servicos> getListaObjetos() {
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
        totalObjetos = em.createQuery("select id from " + classePersistente.getSimpleName() +
                where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    } 
    
}
