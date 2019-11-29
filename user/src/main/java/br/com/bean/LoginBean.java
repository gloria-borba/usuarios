package br.com.bean;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;
import br.com.util.Mensagens;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean {

	private Usuario usuarioSessao;
	private String email;
	private String senha;
	Mensagens msg = new Mensagens();

	public String logar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			this.usuarioSessao = usuarioDAO.buscar(email);

		} catch (Exception e) {
			msg.addMensagemErro("Ocorreu um erro, não foi possível realizar o login!");
			e.printStackTrace();
			return null;
		}

		if (this.usuarioSessao != null && (this.email.equals(this.usuarioSessao.getEmail())) && (this.senha
				.equals(this.usuarioSessao.getSenha()))) {
			return "homePaciente";
		} else {
			msg.addMensagemErro("Não foi possível realizar o login. Verifique o CPF e senha!");
			return null;
		}

	}

    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }

    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
