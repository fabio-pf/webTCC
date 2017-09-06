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
    
    public ControleCausas(){        
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/causas/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Causas();
    }
    
    public void alterar(Integer id){
      try {
            objeto = dao.getObjectById(id); 
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));            
        }                
        
    }
        
    public void excluir(Integer id){
       try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro a remover objeto: "+Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if(objeto.getId_causa() == 0 ){
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
    
    
    
}