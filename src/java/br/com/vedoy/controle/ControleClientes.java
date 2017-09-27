/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.CategoriasDAO;
import br.com.vedoy.dao.ClienteDAO;
import br.com.vedoy.dao.TipoUsuarioDAO;
import br.com.vedoy.modelo.Categorias;
import br.com.vedoy.modelo.Clientes;
import br.com.vedoy.modelo.Tipo_Usuario;
import br.com.vedoy.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fabio Vedoy
 */
@Named(value = "controleClientes")
@SessionScoped
public class ControleClientes implements Serializable{
    @EJB
    private ClienteDAO<Clientes> dao;
    private Clientes objeto;
    private Boolean editando;
    @EJB
    private CategoriasDAO<Categorias> daoCategorias;
    @EJB
    private TipoUsuarioDAO<Tipo_Usuario> daoPermissao;
    private Tipo_Usuario permissao;
    private Boolean editandoPermissao;
    
    public ControleClientes(){
        editando = false;
        editandoPermissao = false;
    }
    
    public String listar(){
        editando = false;
        editandoPermissao = false;
         return "/privado/cliente/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        editandoPermissao=false;
        objeto = new Clientes(){};
    }
    
    public void alterar(Integer id){
        try{
            objeto = dao.getObjectById(id);
            editando = true; 
            editandoPermissao=false;
        }catch(Exception e){
            Util.mensagemErro("Erro ao Recuperar objeto "+Util.getMensagemErro(e));
        }    
    }
    
     public void remover(Integer id){
        try{
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch(Exception e){
            Util.mensagemErro("Erro ao Recuperar objeto "+Util.getMensagemErro(e));
        }    
    }
     
    public void salvar(){
        try{
            if(objeto.getId() ==  null){
                dao.persist(objeto);
               } else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch(Exception e){
            Util.mensagemErro("Erro ao Recuperar objeto "+Util.getMensagemErro(e));
            
        }
    }
    
    public void novaPermissao(){
        editandoPermissao=true;
        
    }
    
    public void salvarPermissao(){
        if(!objeto.getTipos_usuario().contains(permissao)){
            objeto.getTipos_usuario().add(permissao);
            Util.mensagemInformacao("Permissao adicionada com sucesso");
        }else{
            Util.mensagemErro("Usuario ja possui permissao!!");
        }
        editandoPermissao= false;
    }
    
    public void removerPermissao(Tipo_Usuario obj){
        objeto.getTipos_usuario().remove(obj);
        Util.mensagemInformacao("Permissao removida com sucesso!!");
     }

    public ClienteDAO<Clientes> getDao() {
        return dao;
    }

    public void setDao(ClienteDAO<Clientes> dao) {
        this.dao = dao;
    }

    public Clientes getObjeto() {
        return objeto;
    }

    public void setObjeto(Clientes objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public CategoriasDAO<Categorias> getDaoCategorias() {
        return daoCategorias;
    }

    public void setDaoCategorias(CategoriasDAO<Categorias> daoCategorias) {
        this.daoCategorias = daoCategorias;
    }

    public TipoUsuarioDAO<Tipo_Usuario> getDaoPermissao() {
        return daoPermissao;
    }

    public void setDaoPermissao(TipoUsuarioDAO<Tipo_Usuario> daoPermissao) {
        this.daoPermissao = daoPermissao;
    }

    public Tipo_Usuario getPermissao() {
        return permissao;
    }

    public void setPermissao(Tipo_Usuario permissao) {
        this.permissao = permissao;
    }

    public Boolean getEditandoPermissao() {
        return editandoPermissao;
    }

    public void setEditandoPermissao(Boolean editandoPermissao) {
        this.editandoPermissao = editandoPermissao;
    }

    

}