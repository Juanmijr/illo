package KKJPA2.modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import KKJPA2.modelo.Coche;
import KKJPA2.modelo.Venta;

public class ControladorBBDDVenta extends Controlador {
	
	public static List<Venta> findAll () {
				
			EntityManager em = getEntityManager();
			
			Query q = em.createNativeQuery("SELECT * FROM venta;", Venta.class);
			
			List<Venta> entidades = new ArrayList<Venta>();
			try {
				entidades = (List<Venta>) q.getResultList();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			em.close();
			
			return entidades;
		}
	
	/**
	 * 
	 * @return
	 */
	
	public static Venta findFirst () {
	
			EntityManager em = getEntityManager();

			Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.venta order by id limit 1", Venta.class);
			Venta fabricante = null;
			try {
				fabricante = (Venta) q.getSingleResult();
			}
			catch (NoResultException ex) {
				
			}
			em.close();
			
			return fabricante;
		}
	/**
	 * 
	 * @return
	 */
	public static Venta findLast () {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.venta order by id desc limit 1", Venta.class);
		Venta venta = null;
		try {
			venta = (Venta) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return venta;
	}
	/**
	 * 
	 * @param cocheActual
	 * @return
	 */
	public static Venta findNext(Venta ventaActual) {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.venta where id > ? order by id limit 1", Venta.class);
		q.setParameter(1, ventaActual.getId());
		Venta venta = null;
		try {
			venta = (Venta) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return venta;
	}
	/**
	 * 
	 * @param cocheActual
	 * @return
	 */
	public static Venta findPrevious(Venta ventaActual) {
		
		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.venta where id < ? order by id desc limit 1", Venta.class);
		q.setParameter(1, ventaActual.getId());
		Venta venta = null;
		try {
			
			venta = (Venta) q.getSingleResult();
		}
		catch (NoResultException ex) {
			
		}
		em.close();
		
		return venta;
	}
	
	/**
	 * 
	 * @param coche
	 */
	public static Venta persist (Venta venta) {
		EntityManager em = getEntityManager();
		  
		try {
			em.getTransaction().begin();
			if (exist(venta)) {
				venta = em.merge(venta);
			}
			else {
				em.persist(venta);
			}
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		em.close();
		return venta;
	}
	
	/**
	 * 
	 */
	public static boolean exist (Venta venta) {
		EntityManager em = getEntityManager();
		boolean ok = true;
		 try {
		Query q = em.createNativeQuery("", Venta.class);
			em.find(Coche.class, venta.getId());
				
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
	public static boolean remove (Venta venta) {
		 EntityManager em = getEntityManager();
		boolean ok = true;
		 try {
			em.getTransaction().begin();
			if (exist(venta)) {
				venta = em.merge(venta);
				em.remove(venta);
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
