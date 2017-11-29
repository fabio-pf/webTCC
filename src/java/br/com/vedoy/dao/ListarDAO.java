/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Ordem_Servicos;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import org.primefaces.component.orderlist.OrderList;

/**
 *
 * @author Fabio V
 */
@Stateful
public class ListarDAO<T> extends DAOGenerico<Ordem_Servicos> implements Serializable  {
    private Integer user;

    
    public ListarDAO() {
        super();
        super.classePersistente = Ordem_Servicos.class;
        
    }
    
    public List<T> getListaClientes() {
        String jpql = "from " + classePersistente.getSimpleName() + " where cliente = " + ordem;
        return em.createQuery(jpql).getResultList();
    } 
    
    public List<T> getListaTecnicos() {
        String jpql = "from " + classePersistente.getSimpleName() + " where tecnico = " + ordem;
        return em.createQuery(jpql).getResultList();
    } 
    
    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
    
    
}
