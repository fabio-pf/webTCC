/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.dao;

import br.com.vedoy.modelo.Causas;
import java.io.Serializable;

/**
 *
 * @author Fabio V
 */
public class CausasDAO<T> extends DAOGenerico<Causas> implements Serializable {

    public CausasDAO() {
        super();
        super.classePersistente = Causas.class;
    }

}
