/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Produtos;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author Fabio V
 */
@Stateful
public class ProdutosDAO<T> extends DAOGenerico<Produtos> implements Serializable{
    
     public ProdutosDAO(){
        super();
        super.classePersistente = Produtos.class;
        ordem = "nome";
    }
    
    public Produtos getObjectById(String id) throws Exception {
        return (Produtos) em.find(classePersistente, id);
    } 
    
    @Override
    public List<Produtos> getListaObjetos() {
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
        totalObjetos = em.createQuery("select  nome from " + classePersistente.getSimpleName() +
                where + " order by " + ordem).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).
                setMaxResults(maximoObjetos).getResultList();        
    }    
    
}
