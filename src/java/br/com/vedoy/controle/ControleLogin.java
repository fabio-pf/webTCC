/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.DAOGenerico;
import br.com.vedoy.dao.OsDAO;
import br.com.vedoy.dao.UsuarioDAO;
import br.com.vedoy.modelo.Ordem_Servicos;
import br.com.vedoy.util.Util;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import net.bootsfaces.component.alert.Alert;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Fabio V
 */
@Named(value = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {

    private Usuarios usuarioAutenticado;
    @EJB
    private UsuarioDAO<Usuarios> dao;
    @NotNull(message = "O nome de usuário não pode ser nulo")
    @NotBlank(message = "O nome de usuário deve ser informado")
    private String usuario;
    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha deve ser informada")
    private String senha;
    
   

    public ControleLogin() {

    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }
    

    public String efetuarLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(this.usuario, this.senha);
            if (request.getUserPrincipal() != null) {
                usuarioAutenticado
                        = dao.localizaPorNomeUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!");
                usuario = "";
                senha = "";
            }
            return "/index";
        } catch (Exception e) {
            Util.mensagemErro("Usuario ou senha inválidos! "
                    + Util.getMensagemErro(e));
            return "/login.xhtml";
        }
    }
    
     public Usuarios pegarDaSessao() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        return (Usuarios) session.getAttribute("usuarioAutenticado");
    }
    

    public String efetuarLogout() {
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            Util.mensagemInformacao("Logout efetuado com sucesso!");
            return "/index";
        } catch (Exception e) {
            Util.mensagemErro("Erro: " + Util.getMensagemErro(e));
            return "/index";
        }
    }

    public Boolean temPermissoes(List<String> permissoes) {
        for (String permissao : permissoes) {
            if (usuarioAutenticado.getTipo().getNome().equals(permissao)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    public Usuarios getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuarios usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public UsuarioDAO<Usuarios> getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO<Usuarios> dao) {
        this.dao = dao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
