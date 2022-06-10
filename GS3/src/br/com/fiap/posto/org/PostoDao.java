package br.com.fiap.posto.org;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import br.com.fiap.posto.model.Posto;

public class PostoOrg {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("posto");
	
	EntityManager manager = factory.createEntityManager();
	
	public void inserir(Posto posto) {

		manager.getTransaction().begin();
		manager.persist(posto);
		manager.getTransaction().commit();
		
	}
	
	public List<Posto> ordemDes(){
		
		String jpql = "SELECT p FROM Posto p order by p.estado DESC";
		TypedQuery<Posto> query = manager.createQuery(jpql, Posto.class);
		return query.getResultList();
	} 
	
	public List<Posto> ordemAsc(){
		
		String jpql = "SELECT p FROM Posto p order by p.estado asc";
		TypedQuery<Posto> query = manager.createQuery(jpql, Posto.class);
		return query.getResultList();
	} 
	
	public List<Posto> listarTodos(){
		
		String jpql = "SELECT p FROM Posto p";
		TypedQuery<Posto> query = manager.createQuery(jpql, Posto.class);
		
		return query.getResultList();
	}
}
