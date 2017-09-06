/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.CausasDAO;
import br.com.vedoy.modelo.Causas;
import br.com.vedoy.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fabio V
 */
@Named(value = "controleCausas")
@SessionScoped
@Stateful
public class ControleCausas implements Serializable{
    @EJB
    private CausasDAO<Causas> dao;
    private Causas objeto;
    private Boolean editando;
    private Boolean novoObjeto;
    
    public ControleCausas(){        
        editando = false;
        novoObjeto = false;
    }
    
    public String listar(){
        editando = false;
        novoObjeto = false;
        return "/privado/causas/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Causas();
        novoObjeto = true;
    }
    
    public void alterar(String nome){
      try {
            objeto = dao.getObjectById(nome); 
            editando = true;
            novoObjeto = false;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }                
        
    }
    
    public void excluir(String nome){
       try {
            objeto = dao.getObjectById(nome);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {                  
            if (novoObjeto){                
                dao.persist(objeto);            
            } else {                
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Sucesso ao persistir objeto");  
            editando = false;        
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir: "+Util.getMensagemErro(e));            
        }                
    }

    public CausasDAO<Causas> getDao() {
        return dao;
    }

    public void setDao(CausasDAO<Causas> dao) {
        this.dao = dao;
    }

    public Causas getObjeto() {
        return objeto;
    }

    public void setObjeto(Causas objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public Boolean getNovoObjeto() {
        return novoObjeto;
    }

    public void setNovoObjeto(Boolean novoObjeto) {
        this.novoObjeto = novoObjeto;
    }
    
    
}
