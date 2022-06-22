package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {
	
	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Rhei Delas");
		contato.setIdade(31);
		contato.setDataCadastro(new Date());
			
		//contatoDao.save(contato);
		
		//atualizar o contato.
		Contato c1 = new Contato();
		c1.setNome("Maria gabriela Dias orlando");
		c1.setIdade(37);
		c1.setDataCadastro(new Date());
		c1.setId(1); //numero que esta no banco de dados da PK 
		
		contatoDao.update(c1);
		
		//visualizacao dos registros do banco TODOS
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("contato: "+c.getNome());
		}
	}
	
}
