/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Cotas;
import br.com.vedoy.modelo.Produtos;
import java.io.Serializable;

/**
 *
 * @author Fabio V
 */
public class CotasDAO<T> extends DAOGenerico<Cotas> implements Serializable {
    
    public CotasDAO() {
        super();
        super.classePersistente = Cotas.class;
    }
    
}
