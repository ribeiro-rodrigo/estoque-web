package com.inove.estoqueweb.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope("prototype")
@Repository
public class GenericoDAO<T> {

	protected Session session; 
	
	@Autowired
	public GenericoDAO(FabricaDeSessao fabrica){
		
		this.session = fabrica.getSession(); 
	}
	
	
	public void salvar(T objeto)throws DAOException{
		
		try{
			
			session.save(objeto); 
			
			
		}catch(Exception e){
			  
			throw new DAOException(e.getMessage(),e.getCause()); 
		}
		
		
				
	}
	
	public void remover(T objeto)throws DAOException{
		
		try{
			
			session.delete(objeto);
			
		}catch(Exception e){
			
			throw new DAOException(e.getMessage(),e.getCause()); 
		}
		
		
	}
	
	public T buscar(Class classe,Long id)throws DAOException{
		
		try{
			
			return (T) session.get(classe, id);
			
		}
		catch(Exception e){
			 
			throw new DAOException(e.getMessage(),e.getCause());
		}
			
	}
	
	public void alterar(T objeto)throws DAOException{
		
		try{
			
			session.merge(objeto);
			
		}
		catch(Exception e){
			
			throw new DAOException(e.getMessage(),e.getCause());
		}
		
		
	}
	
	public void iniciarTransacao(){
		
		if(!session.getTransaction().isActive())
			session.beginTransaction(); 
	}
	
	public void finalizarTransacao(){
		
		if(session.getTransaction().isActive()){
			
			session.getTransaction().commit();
			session.flush();

		}
		
	}
	
	public void finalizarTransacaoEFecharConexao(){
		
		finalizarTransacao();
		fecharConexao(); 
	}
	
	public void fecharConexao(){
		
		if(session.isOpen())			
			session.close(); 

	}
	
	public void reverterTransacao(){
		
		if(session.getTransaction().isActive())
			session.getTransaction().rollback();

		
		
		
	}
	
	
}
