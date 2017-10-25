/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.CategoriasDAO;
import br.com.vedoy.dao.PessoaDAO;
import br.com.vedoy.dao.TipoUsuarioDAO;
import br.com.vedoy.modelo.Categorias;
import br.com.vedoy.modelo.Tipo_Usuario;
import br.com.vedoy.modelo.Pessoas;
import br.com.vedoy.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Fabio Vedoy
 */
@Named(value = "controlePessoas")
@SessionScoped
public class ControlePessoas implements Serializable {

    @EJB
    private PessoaDAO<Pessoas> dao;
    private Pessoas objeto;
    private Boolean editando;
    @EJB
    private CategoriasDAO<Categorias> daoCategorias;
    @EJB
    private TipoUsuarioDAO<Tipo_Usuario> daoPermissao;
    private Tipo_Usuario permissao;
    private Boolean editandoPermissao;
    private boolean exibePainelX = false;
    private Boolean editandoEdit;
    private Boolean aux = false;

    public ControlePessoas() {
        editando = false;
        editandoEdit = false;
        editandoPermissao = false;
    }

    public String listar() {
        editando = false;
        editandoPermissao = false;
        return "/privado/pessoas/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        editandoPermissao = false;
        aux = false;
        objeto = new Pessoas();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando= true;
            editandoPermissao = false;
            aux= true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao Recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void remover(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao Recuperar objeto " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao Recuperar objeto " + Util.getMensagemErro(e));

        }
    }

    public void novaPermissao() {
        editandoPermissao = true;

    }

    public void salvarPermissao() {
        if (!objeto.getTipos_usuario().contains(permissao)) {
            objeto.getTipos_usuario().add(permissao);
            Util.mensagemInformacao("Permissao adicionada com sucesso");
        } else {
            Util.mensagemErro("Usuario ja possui permissao!!");
        }
        editandoPermissao = false;
    }

    public void removerPermissao(Tipo_Usuario obj) {
        objeto.getTipos_usuario().remove(obj);
        Util.mensagemInformacao("Permissao removida com sucesso!!");
    }

    public void enviaTipo(String tipo) {
        Util.mensagemInformacao("tipo" + tipo);
    }

    public PessoaDAO<Pessoas> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoas> dao) {
        this.dao = dao;
    }

    public Pessoas getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoas objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
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

    public CategoriasDAO<Categorias> getDaoCategorias() {
        return daoCategorias;
    }

    public void setDaoCategorias(CategoriasDAO<Categorias> daoCategorias) {
        this.daoCategorias = daoCategorias;
    }

    public boolean isExibePainelX() {
        return exibePainelX;
    }

    public void setExibePainelX(boolean exibePainelX) {
        this.exibePainelX = exibePainelX;
    }

    public void mostraCampoComplemento(String tipo) {
        if (tipo.equals("TECNICO")) {
            this.setExibePainelX(true);
            Util.mensagemInformacao("tipo pessoa" + tipo + " valor rendered " + exibePainelX);
        } else {
            this.setExibePainelX(false);
            Util.mensagemInformacao("tipo pessoa" + tipo + " valor rendered " + exibePainelX);
        }
    }

    public Boolean getEditandoEdit() {
        return editandoEdit;
    }

    public void setEditandoEdit(Boolean editandoEdit) {
        this.editandoEdit = editandoEdit;
    }

    public Boolean getAux() {
        return aux;
    }

    public void setAux(Boolean aux) {
        this.aux = aux;
    }
    
}
