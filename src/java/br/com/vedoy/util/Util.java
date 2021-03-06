package br.com.vedoy.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br Instituto Federal
 * Sul-Rio-Grandense Campus Passo Fundo
 */
public class Util {

    public static void mensagemInformacao(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        facesContext.addMessage(null, mensagem);
    }

    public static void mensagemErro(String msg) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        facesContext.addMessage(null, mensagem);
    }

    public static String getMensagemErro(Exception e) {
        while (e.getCause() != null) {
            e = (Exception) e.getCause();
        }
        String retorno = e.getMessage();
        if(retorno.contains("viola restrição de chave estrangeira")){
            retorno = "Registro nao pode ser excluido por estar sendo usado por outra classe do sistema!!";
        }
        return retorno;
    }

}
