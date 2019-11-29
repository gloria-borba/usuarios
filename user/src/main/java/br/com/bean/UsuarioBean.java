package br.com.bean;

import br.com.dao.UsuarioDAO;
import br.com.model.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "pacienteBean")
@ViewScoped
public class UsuarioBean {

    private UsuarioDAO dao;
    private Usuario usuario;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    //todo
//    @PostConstruct
//    public void init() {
//        this.dao = new UsuarioDAO();
//        if (loginBean.getUsuarioSessao() == null) {
//            paciente = new Paciente();
//        } else {
//            paciente = loginBean.getUsuarioSessao();
//            try {
//                this.paciente = dao.buscar(this.paciente.getCpf());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//
//
//    }

//    todo
//    public void cadastrarUsuario() {
//        try {
//            if () {
//                dao.inserir(this.u);
//                this.paciente = new Paciente();
//                msg.addMensagemSucesso("Paciente cadastrado com sucesso!");
//            } else {
//                msg.addMensagemErro("CPF já cadastrado");
//            }
//
//        } catch (Exception e) {
//            msg.addMensagemErro("Não foi possível realizar o cadastro!");
//            e.printStackTrace();
//        }
//    }
}
