package br.com.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.dao.TelefoneDAO;
import br.com.model.Telefone;
import br.com.model.Usuario;
import br.com.util.Mensagens;

@ManagedBean(name = "telefoneBean")
@SessionScoped
public class TelefoneBean {

	private TelefoneDAO dao;

	private Telefone telefoneSelecionado;

	private Usuario usuario;
	
	private Mensagens msg = new Mensagens();

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
	
	public void cadastrarTelefone() {
		try {
			this.dao = new TelefoneDAO();
			this.telefoneSelecionado.setUsuario(this.usuario);
			dao.inserir(this.telefoneSelecionado);
			this.usuario.setTelefones(dao.listarTelefones(this.usuario.getId()));
			this.telefoneSelecionado = new Telefone();
			
			msg.addMensagemSucesso("Telefone salvo com sucesso!");
			
		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível realizar o cadastro.");
			e.printStackTrace();
		}

	}
	
	public void excluirTelefone() {
		 try {
			dao.remover(this.telefoneSelecionado.getId());
			msg.addMensagemSucesso("Registro removido com sucesso!");
			this.usuario.getTelefones().remove(this.telefoneSelecionado);
			this.telefoneSelecionado = new Telefone();
		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível atualizar o registro.");
			e.printStackTrace();
		}
		
	}
	
	public void alterarTelefone()  {
		
		try {
			dao.atualizar(this.telefoneSelecionado);
			this.usuario.setTelefones(dao.listarTelefones(this.usuario.getId()));
			msg.addMensagemSucesso("Telefone alterado coom sucesso!");
			
		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível alterar!");
			e.printStackTrace();
		}
	}
	
	public String voltar() {
		this.telefoneSelecionado = new Telefone();
		return "homeUsuario";
	}
	
	public String telaAdicionarTelefone(){
		this.telefoneSelecionado = new Telefone();
		return "adicionarTelefone";
	}
	
	public String telaAtualizarTelefone() {
		return "atualizarTelefone";
		
	}
}
