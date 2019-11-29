package br.com.bean;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean", eager= true)
@SessionScoped
public class LoginBean {

    private Usuario usuarioSessao;

    //TODO
//    public String logar(){
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//
//        try {
//            this.usuarioSessao = usuarioDAO.buscar(cpf);
//
//        } catch (Exception e) {
//            msg.addMensagemErro("Ocorreu um erro, não foi possível realizar o login!");
//            e.printStackTrace();
//            return null;
//        }
//
//        if (this.usuarioSessao != null && (this.cpf.equals(this.usuarioSessao.getCpf())) && (this.senha.equals(this.usuarioSessao.getSenha()))) {
//            return "homePaciente";
//        } else {
//            msg.addMensagemErro("Não foi possível realizar o login. Verifique o CPF e senha!");
//            return null;
//        }
//
//    }

}
