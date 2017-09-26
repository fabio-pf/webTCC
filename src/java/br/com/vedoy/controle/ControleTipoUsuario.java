package br.com.vedoy.controle;




import br.com.vedoy.dao.TipoUsuarioDAO;
import br.com.vedoy.modelo.Tipo_Usuario;
import br.com.vedoy.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;



@Named(value = "controleTipoUsuarios")
@SessionScoped
public class ControleTipoUsuario implements Serializable {
    @EJB
     private TipoUsuarioDAO<Tipo_Usuario> dao;
    private Tipo_Usuario objeto;
    private Boolean editando;
    private Boolean novoObjeto;
    
    public ControleTipoUsuario(){        
        editando = false;
        novoObjeto = false;
    }
    
    public String listar(){
        editando = false;
        novoObjeto = false;
        return "/privado/tipo_usuario/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        objeto = new Tipo_Usuario();
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

    public TipoUsuarioDAO<Tipo_Usuario> getDao() {
        return dao;
    }

    public void setDao(TipoUsuarioDAO<Tipo_Usuario> dao) {
        this.dao = dao;
    }

    public Tipo_Usuario getObjeto() {
        return objeto;
    }

    public void setObjeto(Tipo_Usuario objeto) {
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
