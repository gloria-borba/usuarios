package br.com.dao;

import br.com.conexao.Conexao;
import br.com.model.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelefoneDAO {

    public void inserir(Telefone telefone) throws Exception {
        Connection conn = Conexao.getConnection();

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO TELEFONE (ddd,numero,tipo) VALUES(?,?,?)");
            statement.setLong(1, telefone.getDdd());
            statement.setString(2, telefone.getNumero());
            statement.setString(3, telefone.getTipo());

            statement.execute();

            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            Conexao.close(conn);
        }

    }
}
