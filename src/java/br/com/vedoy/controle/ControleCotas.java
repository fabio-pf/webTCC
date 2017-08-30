/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.CotasDAO;
import br.com.vedoy.modelo.Cotas;
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
@Named(value = "controleCotas")
@SessionScoped
@Stateful
public class ControleCotas implements Serializable{
    @EJB
    private CotasDAO<Cotas> dao;
    private Cotas objeto;
    private Boolean editando;
    
    public ControleCotas(){        
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/cotas/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Cotas();
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
            if(objeto.getId()== 0 ){
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

    public CotasDAO<Cotas> getDao() {
        return dao;
    }

    public void setDao(CotasDAO<Cotas> dao) {
        this.dao = dao;
    }

    public Cotas getObjeto() {
        return objeto;
    }

    public void setObjeto(Cotas objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    } 
}