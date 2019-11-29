package br.com.dao;

import br.com.conexao.Conexao;
import br.com.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO {
    /**
     * Insere um usuário
     * @param usuario
     * @throws Exception
     */
    public void inserir(Usuario usuario) throws Exception {
        Connection conn = Conexao.getConnection();

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO USUARIO (nome,email,senha) VALUES(?,?,?)");
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());

            statement.execute();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            Conexao.close(conn);
        }

    }

    /**
     * Remove o usuário pelo email
     * @param email
     * @throws Exception
     */
    public void remove(String email) throws Exception {
        Connection conn = Conexao.getConnection();

        try{

            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM USUARIO WHERE USUARIO.EMAIL = ?");
            statement.setString(1, email);

            statement.execute();
            conn.commit();

        }catch(Exception e){
            conn.rollback();
            throw e;

        }finally{
            Conexao.close(conn);
        }


    }
}
