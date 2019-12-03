package br.com.dao;

import br.com.util.Conexao;
import br.com.model.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {

    /**
     * Insere um telefone
     * @param telefone
     * @throws Exception
     */
    public void inserir(Telefone telefone) throws Exception {
        Connection conn = Conexao.getConnection();

        try {
            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO TELEFONE (cd_telefone ,ddd,numero,tipo, usuario) VALUES(?,?,?,?,?)");
			// TODO ADICIONAR PELA SEQUENCE (conn.prepareStatement("SEQ_TELEFONE.nextval"));
            statement.setLong(1, telefone.getDdd());
            statement.setString(2, telefone.getNumero());
            statement.setString(3, telefone.getTipo());
            statement.setLong(4, telefone.getUsuario().getId());

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
     * Atualiza o telefone
     * @param telefone
     * @throws Exception
     */
    public void atualizar(Telefone telefone) throws Exception {
        Connection conn = Conexao.getConnection();
        try{
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE TELEFONE SET DDD = ?, NUMERO = ?, TIPO = ? WHERE CD_TELEFONE = ?");

            statement.setLong(1, telefone.getDdd());
            statement.setString(2, telefone.getNumero());
            statement.setString(3, telefone.getTipo());
            statement.setLong(4, telefone.getId());

            statement.execute();
            conn.commit();

        } catch(Exception e){
            conn.rollback();
            throw e;

        }finally{
            Conexao.close(conn);
        }
    }

    /**
     * Exclui um telefone
     * @param numero
     * @throws Exception
     */
    public void remover(Long numero) throws Exception {
        Connection conn = Conexao.getConnection();

        try{
            PreparedStatement statement = conn.prepareStatement(
                    "DELETE FROM TELEFONE WHERE NUMERO = ?");
            statement.setLong(1, numero);

            statement.execute();
            conn.commit();

        }catch(Exception e){
            conn.rollback();
            throw e;

        }finally{
            Conexao.close(conn);
        }

    }

    /**
     * Lista os telefones pelo usuário
     * @param id
     * @return List<Telefone>
     * @throws Exception
     */
    public List<Telefone> listarTelefones(Long id) throws Exception {
        Connection conn = Conexao.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM TELEFONE WHERE CD_USUARIO = ?");
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();

        ArrayList listaTelefones = new ArrayList();
        while (rs.next()) {
            Telefone telefone = new Telefone();
            telefone.setDdd(rs.getLong("DDD"));
            telefone.setNumero(rs.getString("NUMERO"));
            telefone.setTipo(rs.getString("TIPO"));

            listaTelefones.add(telefone);
        }

        Conexao.close(conn);
        return listaTelefones;
    }
}
