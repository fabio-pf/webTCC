/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Produtos;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Fabio V
 */
@Stateful
public class ProdutosDAO<T> extends DAOGenerico<Produtos> implements Serializable{
    
    public ProdutosDAO() {
        super();
        super.classePersistente = Produtos.class;
    }
    
}
