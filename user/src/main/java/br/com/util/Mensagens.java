package br.com.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagens {

	public void addMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, null));
	}

	public void addMensagemSucesso(String mensagem) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
}
