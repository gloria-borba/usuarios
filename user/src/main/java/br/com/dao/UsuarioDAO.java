package br.com.dao;

import br.com.util.Conexao;
import br.com.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	/**
	 * Insere um usuário
	 *
	 * @param usuario
	 * @throws Exception
	 */
	public void inserir(Usuario usuario) throws Exception {
		Connection conn = Conexao.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO USUARIO (cd_usuario,nome,email,senha) VALUES(?,?,?,?)");
			// TODO ADICIONAR PELA SEQUENCE (conn.prepareStatement("SEQ_USUARIO.nextval"));
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
	 * Remove o usuário pelo id
	 *
	 * @param id
	 * @throws Exception
	 */
	public void remove(Long id) throws Exception {
		Connection conn = Conexao.getConnection();

		try {

			PreparedStatement statement = conn.prepareStatement("DELETE FROM USUARIO WHERE CD_USUARIO = ?");
			statement.setLong(1, id);

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
	 * Buscar o usuário pelo email
	 *
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public Usuario buscar(String email) throws Exception {
		Usuario usuario = new Usuario();
		Connection conn = Conexao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT * FROM USUARIO WHERE EMAIL = ?");
		statement.setString(1, email);
		ResultSet result = statement.executeQuery();

		while (result.next()) {
			usuario.setEmail(result.getString("EMAIL"));
			usuario.setNome(result.getString("NOME"));
			usuario.setSenha(result.getString("SENHA"));
		}

		Conexao.close(conn);

		return usuario;
	}

	/**
	 * Atualizar usuário
	 *
	 * @param usuario
	 * @throws Exception
	 */
	public void atualizar(Usuario usuario) throws Exception {
		Connection conn = Conexao.getConnection();

		try {

			PreparedStatement statement = conn
					.prepareStatement("UPDATE USUARIO SET EMAIL = ?,NOME = ?,SENHA = ? WHERE CD_USUARIO = ?");

			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNome());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getId());

			
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
	 * Listar todos os usuários
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Usuario> listar() throws Exception {

		Connection conn = Conexao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT * FROM USUARIO");
		ResultSet rs = statement.executeQuery();

		List<Usuario> lista = new ArrayList<Usuario>();

		while (rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(rs.getString("EMAIL"));
			usuario.setNome(rs.getString("NOME"));
			usuario.setSenha(rs.getString("SENHA"));

			lista.add(usuario);
		}

		Conexao.close(conn);

		return lista;
	}
}

