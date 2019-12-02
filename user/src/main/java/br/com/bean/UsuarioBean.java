package br.com.bean;

import java.util.List;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
import br.com.util.Mensagens;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "pacienteBean")
@ViewScoped
public class UsuarioBean {

	private UsuarioDAO dao;
	private Usuario usuario;
	private Mensagens msg = new Mensagens();
	private Usuario usuarioSelecionado;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	@PostConstruct
	public void init() {
		this.dao = new UsuarioDAO();
		if (loginBean.getUsuarioSessao() == null) {
			usuario = new Usuario();
		} else {
			usuario = loginBean.getUsuarioSessao();
			try {
				this.usuario = dao.buscar(this.usuario.getEmail());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Cadastra um usuário
	 */
	public void cadastrarUsuario() {
		try {
			if (dao.buscar(usuario.getEmail()).getEmail() == null) {
				dao.inserir(this.usuario);
				this.usuario = new Usuario();
				msg.addMensagemSucesso("Usuário cadastrado com sucesso!");
			} else {
				msg.addMensagemErro("Email já cadastrado");
			}

		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível realizar o cadastro!");
			e.printStackTrace();
		}
	}

	/**
	 * Remover usuário
	 */
	public void remover() {
		try {
			dao.remove(this.usuario.getEmail());
			msg.addMensagemSucesso("Registro removido com sucesso!");
		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível atualizar o registro.");
			e.printStackTrace();
		}

	}

	/**
	 * Lista todos os usuários
	 * @return List<Usuario>
	 */
	public List<Usuario> listarUsuarios() {
		try {
			return dao.listar();
		} catch (Exception e) {
			msg.addMensagemErro("Não foi possível listar os usuários!");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Atualiza os dados do usuário
	 */
	public void alterarUsuario() {
		try {
			dao.atualizar(this.usuario);
		} catch (Exception e) {
			msg.addMensagemErro("Error! Nao foi possivel atualizar os dados!");
			e.printStackTrace();

		}
		msg.addMensagemSucesso("Dados atualizados com sucesso!");
	}

	public String cancelar() {
		this.usuario = new Usuario();
		return "window.history.back()";
	}

	public String telaAtualizarUsuario() {
		return "atualizarUsuario";
	}

}
