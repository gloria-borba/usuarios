package br.com.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.dao.TelefoneDAO;
import br.com.model.Telefone;
import br.com.model.Usuario;

@ManagedBean(name = "telefoneBean")
@SessionScoped
public class TelefoneBean {

	private TelefoneDAO dao;

	private Telefone telefoneSelecionado;

	private Usuario usuario;

	@ManagedProperty(value= "#{loginBean}")
	private LoginBean loginBean;

	// metodos
	@PostConstruct
	public void inicializar() {
		dao = new TelefoneDAO();
		this.usuario = loginBean.getUsuarioSessao();
		telefoneSelecionado = new Telefone();
		try {
			this.usuario.setTelefones(dao.listarTelefones(this.usuario.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String telaAdicionarTelefone(){
		this.telefoneSelecionado = new Telefone();
		return "adicionarTelefone";
	}
}
