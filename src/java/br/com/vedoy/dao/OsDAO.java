
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Ordem_Servicos;
import java.io.Serializable;
import javax.ejb.Stateful;
import java.util.List;


@Stateful
public class OsDAO<T> extends DAOGenerico<Ordem_Servicos> implements Serializable {

    public OsDAO() {
        super();
        super.classePersistente = Ordem_Servicos.class;
        ordem = "id";
    }
    
    public Ordem_Servicos getObjectById(String id) throws Exception {
        return (Ordem_Servicos) em.find(classePersistente, id);
    }
    
   @Override
    public List<Ordem_Servicos> getListaObjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        jpql += " order by "+ordem;
        totalObjetos = 100;
        System.out.println("SQL: "+jpql);
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
    public List<T> getListaCliente(Integer us) {
        String jpql = "from " + classePersistente.getSimpleName()+
                " where cliente = " + us;
        jpql += " order by "+ordem;
        totalObjetos = 100;
        System.out.println("SQL: "+jpql);
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
   
    public List<T> getListaTecnico(Integer us) {
         String jpql = "from " + classePersistente.getSimpleName()+
                " where tecnico = " + us;
        jpql += " order by "+ordem;
        totalObjetos = 100;
        System.out.println("SQL: "+jpql);
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList(); 
    } 
}
    
