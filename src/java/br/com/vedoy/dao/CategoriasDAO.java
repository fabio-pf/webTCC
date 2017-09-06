/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Categorias;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Fabio V
 */
@Stateful
public class CategoriasDAO<T> extends DAOGenerico<Categorias> implements Serializable {
    
    public CategoriasDAO() {
        super();
        super.classePersistente = Categorias.class;
    }
    
}
