package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {
	
	/*
	 * CRUD 
	 * c create
	 * r read
	 * u update
	 * d delete
	 */
	

	public void save(Contato contato) {


		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar uma conexao com o banco 
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//preparedstatement para executar uma query 
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			// add os valores da query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//executar a query
			
			pstm.execute();
			
			System.out.println("contato salvo com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			//fechar as conexoes
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//class que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//recuperar o id
				contato.setId(rset.getInt("id"));
				//recuperar o nome
				contato.setNome(rset.getString("nome"));
				//recuperar idade
				contato.setIdade(rset.getInt("idade"));
				//recuperar data de cadastro
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				contatos.add(contato);
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return contatos;
		}	
}