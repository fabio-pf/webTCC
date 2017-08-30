/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.UsuarioDAO;
import br.com.vedoy.util.Util;
import br.com.vedoy.modelo.Usuarios;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "O nome de usuario nao pode ser nulo")
    @NotBlank(message = "O nome de usuario nao pode ser em branco")
    private String usuario;
    @NotNull(message = "A senha de usuario nao pode ser nulo")
    @NotBlank(message = "A senha de usuario nao pode ser em branco")
    private String senha;

    public ControleLogin() {
    }

    public String paginaLogin() {
        return "/login?faces-redirect=true";
    }

    public String efetuarLogin() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.
                    getCurrentInstance().getExternalContext().getRequest();
            request.login(this.usuario, this.senha);
            if (request.getUserPrincipal() != null) {
                usuarioAutenticado = dao.localizaPorNomeUsuario(request.getUserPrincipal().getName());
                Util.mensagemInformacao("Login realizado com sucesso!");
                usuario = "";
                senha = "";
            }
            return "/index";
        } catch (Exception e) {
            Util.mensagemErro("Usuário ou senha inválidos!!! " + Util.getMensagemErro(e));
            return "/login";
        }
    }

    public String efetuarLogout() {
        try {
            usuarioAutenticado = null;
            HttpServletRequest request = (HttpServletRequest) FacesContext.
                    getCurrentInstance().getExternalContext().getRequest();
            request.logout();
            return "/index";
        } catch (ServletException e) {
            Util.mensagemErro("Erro: " + Util.getMensagemErro(e));
            return "/index";
        }
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
