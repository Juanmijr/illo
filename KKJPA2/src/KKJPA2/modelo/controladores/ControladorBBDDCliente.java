package KKJPA2.modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import KKJPA2.modelo.Cliente;
import KKJPA2.modelo.Cliente;

public class ControladorBBDDCliente extends Controlador {

	public static List<Cliente> findAll() {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM cliente;", Cliente.class);

		List<Cliente> entidades = new ArrayList<Cliente>();
		try {
			entidades = (List<Cliente>) q.getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		em.close();

		return entidades;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public static Cliente findFirst () {
	
			EntityManager em = getEntityManager();

			Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.cliente order by id limit 1", Cliente.class);
			Cliente cliente = null;
			try {
				cliente = (Cliente) q.getSingleResult();
			}
			catch (NoResultException ex) {
				
			}
			em.close();
			
			return cliente;
		}
	/**
	 * 
	 * @return
	 */
	public static Cliente findLast () {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.coche order by id desc limit 1", Cliente.class);
		Cliente cliente = null;
		try {
			cliente = (Cliente) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return cliente;
	}
	/**
	 * 
	 * @param clienteActual
	 * @return
	 */
	public static Cliente findNext(Cliente clienteActual) {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.cliente where id > ? order by id limit 1", Cliente.class);
		q.setParameter(1, clienteActual.getId());
		Cliente cliente = null;
		try {
			cliente = (Cliente) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return cliente;
	}
	/**
	 * 
	 * @param clienteActual
	 * @return
	 */
	public static Cliente findPrevious(Cliente clienteActual) {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.coche where id < ? order by id desc limit 1", Cliente.class);
		q.setParameter(1, clienteActual.getId());
		Cliente cliente = null;
		try {
			
			cliente = (Cliente) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return cliente;
	}
	
	/**
	 * 
	 * @param cliente
	 */
	public static Cliente persist (Cliente cliente) {
		EntityManager em = getEntityManager();
		  
		try {
			em.getTransaction().begin();
			if (exist(cliente)) {
				cliente = em.merge(cliente);
			}
			else {
				em.persist(cliente);
			}
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		em.close();
		return cliente;
	}
	
	/**
	 * 
	 */
	public static boolean exist (Cliente cliente) {
		EntityManager em = getEntityManager();
		boolean ok = true;
		 try {
		Query q = em.createNativeQuery("", Cliente.class);
			em.find(Cliente.class, cliente.getId());
				
		}
		 catch(NoResultException ex) {
			 ok = false;
			 
		 }
		 em.close();
		 return ok; 
	}

	
	/**
	 * 
	 */
	public static boolean remove (Cliente coche) {
		 EntityManager em = getEntityManager();
		boolean ok = true;
		 try {
			em.getTransaction().begin();
			if (exist(coche)) {
				coche = em.merge(coche);
				em.remove(coche);
				em.getTransaction().commit();
			}
		 }
		 catch(NoResultException ex) {
			 ex.printStackTrace();
			 ok = false;
			 
		 }
		 em.close();
		 return ok; 
	}
	
}
