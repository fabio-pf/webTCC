/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vedoy.controle;

import br.com.vedoy.dao.CategoriasDAO;
import br.com.vedoy.dao.CausasDAO;
import br.com.vedoy.dao.OsDAO;
import br.com.vedoy.dao.PessoaDAO;
import br.com.vedoy.dao.ProdutosDAO;
import br.com.vedoy.dao.SintomasDAO;
import br.com.vedoy.dao.UsuarioDAO;
import br.com.vedoy.email.SendMail;
import br.com.vedoy.modelo.Categorias;
import br.com.vedoy.modelo.Causas;
import br.com.vedoy.modelo.Ordem_Servicos;
import br.com.vedoy.modelo.Pessoas;
import br.com.vedoy.modelo.Produtos;
import br.com.vedoy.modelo.Sintomas;
import br.com.vedoy.modelo.Usuarios;
import br.com.vedoy.relatorios.Relatorio;
import br.com.vedoy.util.Util;
import br.com.vedoy.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import net.bootsfaces.component.alert.Alert;

/**
 *
 * @author Fabio Vedoy
 */
@Named(value = "controleOS")
@SessionScoped
public class ControleOS implements Serializable {

    @EJB
    private OsDAO<Ordem_Servicos> dao;
    @EJB
    private PessoaDAO<Pessoas> daoPessoas;
    @EJB
    private UsuarioDAO<Usuarios> daoUsuarios;
    @EJB
    private CausasDAO<Causas> daoCausa;
    @EJB
    private SintomasDAO<Sintomas> daoSintomas;
    @EJB
    private ProdutosDAO<Produtos> daoProdutos;
    private Ordem_Servicos objeto;
    private SendMail send;
    private Boolean editando;
    @EJB
    private CategoriasDAO<Categorias> daoTipo;
    private Causas causa;
    private Sintomas sintoma;
    private Boolean editandoCausa;
    private Boolean editandoSintoma;
    private Boolean aux = false;
    private String usuarioLogado;
    private Integer tipoUsu;
    private Calendar data =Calendar.getInstance();
    private List<Ordem_Servicos> lista = new ArrayList<Ordem_Servicos>();
    

    public ControleOS() {
        editando = false;
        editandoCausa = false;
        editandoSintoma = false;
    }
    
    public String listar() {
        editando = false;
        editandoCausa = false;
        editandoSintoma = false;
        aux = false;
        return "/privado/os/listar?faces-redirect=true";
    }
    
    public String listarCliente() {
        editando = false;
        editandoCausa = false;
        editandoSintoma = false;
        aux = false;
        return "/privado/os/listarCliente?faces-redirect=true";
    }
    
    public String listarTecnico() {
        editando = false;
        editandoCausa = false;
        editandoSintoma = false;
        aux = false;
        return "/privado/os/listarTecnico?faces-redirect=true";
    }
    
    public String listarTecnicoTodas() {
        editando = false;
        editandoCausa = false;
        editandoSintoma = false;
        aux = false;
        return "/privado/os/listarTecnicoTodas?faces-redirect=true";
    }
    
    

    public void novo() {
        editando = true;
        objeto = new Ordem_Servicos();
        SetAtendente();
    }

    public void alterar(Integer id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            aux = true;
            SetAtendente();
        } catch (Exception e) {
            Util.mensagemErro("Erro ao Recuperar objeto " + Util.getMensagemErro(e));
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

    public void novaCausa() {
        editandoCausa = true;

    }

    public void novoSintoma() {
        editandoSintoma = true;

    }

    public void salvarCausa() {
        if (!objeto.getOs_causas().contains(causa)) {
            objeto.getOs_causas().add(causa);
            Util.mensagemInformacao("Causa adicionada com sucesso");
        } else {
            Util.mensagemErro("OS ja possui esta causa!!");
        }
        editandoCausa = false;
    }

    public void salvarSintoma() {
        if (!objeto.getOs_sintomas().contains(sintoma)) {
            objeto.getOs_sintomas().add(sintoma);
            Util.mensagemInformacao("Sintoma adicionado com sucesso");
        } else {
            Util.mensagemErro("OS ja possui este sintoma!!");
        }
        editandoSintoma = false;
    }

    public void removerCausa(Causas obj) {
        objeto.getOs_causas().remove(obj);
        Util.mensagemInformacao("Causa removida com sucesso!!");
    }

    public void removerSintoma(Sintomas obj) {
        objeto.getOs_sintomas().remove(obj);
        Util.mensagemInformacao("Sintoma removido com sucesso!!");
    }

    
    
    public void enviaTipo(String tipo) {
        Util.mensagemInformacao("tipo" + tipo);
    }

    public OsDAO<Ordem_Servicos> getDao() {
        return dao;
    }

    public void setDao(OsDAO<Ordem_Servicos> dao) {
        this.dao = dao;
    }

    public CausasDAO<Causas> getDaoCausa() {
        return daoCausa;
    }

    public void setDaoCausa(CausasDAO<Causas> daoCausa) {
        this.daoCausa = daoCausa;
    }

    public SintomasDAO<Sintomas> getDaoSintomas() {
        return daoSintomas;
    }

    public void setDaoSintomas(SintomasDAO<Sintomas> daoSintomas) {
        this.daoSintomas = daoSintomas;
    }

    public ProdutosDAO<Produtos> getDaoProdutos() {
        return daoProdutos;
    }

    public void setDaoProdutos(ProdutosDAO<Produtos> daoProdutos) {
        this.daoProdutos = daoProdutos;
    }

    public Ordem_Servicos getObjeto() {
        return objeto;
    }

    public void setObjeto(Ordem_Servicos objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public CategoriasDAO<Categorias> getDaoTipo() {
        return daoTipo;
    }

    public void setDaoTipo(CategoriasDAO<Categorias> daoTipo) {
        this.daoTipo = daoTipo;
    }

    public Causas getCausa() {
        return causa;
    }

    public void setCausa(Causas causa) {
        this.causa = causa;
    }

    public Sintomas getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintomas sintoma) {
        this.sintoma = sintoma;
    }

    public Boolean getEditandoCausa() {
        return editandoCausa;
    }

    public void setEditandoCausa(Boolean editandoCausa) {
        this.editandoCausa = editandoCausa;
    }

    public Boolean getEditandoSintoma() {
        return editandoSintoma;
    }

    public void setEditandoSintoma(Boolean editandoSintoma) {
        this.editandoSintoma = editandoSintoma;
    }

    public PessoaDAO<Pessoas> getDaoPessoas() {
        return daoPessoas;
    }

    public void setDaoPessoas(PessoaDAO<Pessoas> daoPessoas) {
        this.daoPessoas = daoPessoas;
    }

    public UsuarioDAO<Usuarios> getDaoUsuarios() {
        return daoUsuarios;
    }

    public void setDaoUsuarios(UsuarioDAO<Usuarios> daoUsuarios) {
        this.daoUsuarios = daoUsuarios;
    }

    public Boolean getAux() {
        return aux;
    }

    public void setAux(Boolean aux) {
        this.aux = aux;
    }

    public String SetAtendente() {
        ControleLogin log = new ControleLogin();
        usuarioLogado = log.getUsuarioAutenticado().getUsuario();
        System.out.println("UsuarioLogado = " + usuarioLogado);
        return usuarioLogado;

    }

    public Integer SetUsuarioLogado() {
        ControleLogin log = new ControleLogin();
        tipoUsu = log.getUsuarioAutenticado().getId();
        System.out.println("UsuarioLogado ID: = " + usuarioLogado);
        return tipoUsu;

    }

    public String getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(String usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Integer getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(Integer tipoUsu) {
        this.tipoUsu = tipoUsu;
    }
    
   public void setaFim(Boolean estado, Integer id){
        try {
            objeto = dao.getObjectById(id);
        } catch (Exception ex) {
            Logger.getLogger(ControleOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar data =Calendar.getInstance();
        Date date = new Date();
        data.setTime(date);
        if(estado==false){
           objeto.setFim(data);
            //Util.mensagemInformacao("data: "+data+" estado: "+estado+" ID "+id);
        }
   }
        public void setaInicio(){
        Date date = new Date();
        data.setTime(date);
        objeto.setInicio(data);
        
        }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    
   
    
    
}
