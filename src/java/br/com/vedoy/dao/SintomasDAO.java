/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import java.io.Serializable;
import javax.ejb.Stateful;
import br.com.vedoy.modelo.Sintomas;
import java.util.List;

/**
 *
 * @author Fabio V
 */
@Stateful
public class SintomasDAO<T> extends DAOGenerico<Sintomas> implements Serializable  {
   
   public SintomasDAO(){
        super();
        super.classePersistente = Sintomas.class;
        ordem = "nome";
    }
    
    public T getObjectById(String id) throws Exception {
        return (T) em.find(classePersistente, id);
    }
    
}
