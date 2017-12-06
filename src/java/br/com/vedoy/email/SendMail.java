package br.com.vedoy.email;

import br.com.vedoy.modelo.Ordem_Servicos;
import br.com.vedoy.util.Util;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@ViewScoped
@Named
public class SendMail {
    
    

	@Resource(name="mailSession")
	private Session session;

        public void send(Ordem_Servicos objeto){
        System.out.println("objeto" +objeto.toString());
        String status;
            System.out.println("Chegou no send" +objeto.getId());
        if(objeto.isStatus()== true){
            status = "Finalizado";
        }else{
            status = "Em andamento";
        }
        Calendar dataInicio = objeto.getInicio();
        Calendar dataFim = objeto.getFim();
        Date date = new Date();
        dataInicio.setTime(date);
        dataFim.setTime(date);
        
        StringBuilder texto  =  new StringBuilder();
        
        texto.append("Segue os dados de sua Ordem de serviço: ").append('\n');
        texto.append("Nome: ").append(objeto.getCliente().getNome()).append('\n');
        texto.append("Data Abertura OS: ").append(dataInicio.getTime()).append('\n');
        texto.append("Tecnico Responsavel: ").append(objeto.getTecnico().getNome()).append('\n');
        texto.append("Produto: ").append(objeto.getProduto().getNome()).append('\n');
        texto.append("Tipo OS: ").append(objeto.getTipo_OS().getNome()).append('\n');
        texto.append("Sintoma principal do Produto: ").append(objeto.getSintomaPrincipal().getNome()).append('\n');
        texto.append("Descrição OS: ").append(objeto.getDescricao_servico()).append('\n');
        texto.append("Criado Por: ").append(objeto.getAtendente().getNome()).append('\n');
        texto.append('\n');
        texto.append('\n');
        texto.append("Segue o status de sua Ordem de serviço: ").append('\n');
        texto.append("Nome: ").append(objeto.getCliente().getNome()).append('\n');
        texto.append("Status: ").append(status).append('\n'); 
        texto.append("Data Fim: ").append(dataFim.getTime()).append('\n');
        texto.append("Finalizado por: ").append(objeto.getAtendente().getNome()).append('\n');
    
         System.out.println("Send start");
		Message msg = new MimeMessage(session);
		try {
                        
                        
			msg.setSubject("OS aberta por: "+objeto.getCliente().getNome());
			msg.setText(texto.toString());
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(objeto.getCliente().getEmail()));
			
			Transport.send(msg);
                        
		} catch (MessagingException e) {
			e.printStackTrace();
                        Util.getMensagemErro(e);
		}
		System.out.println("Envio Finalizado");   
    }
}

