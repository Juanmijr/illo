package KKJPA2.modelo.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import KKJPA2.modelo.Concesionario;
import KKJPA2.modelo.Concesionario;

public class ControladorBBDDConcesionario extends Controlador {

	public static List<Concesionario> findAll() {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM concesionario;", Concesionario.class);

		List<Concesionario> entidades = new ArrayList<Concesionario>();
		try {
			entidades = (List<Concesionario>) q.getResultList();
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

	public static Concesionario findFirst() {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.concesionario order by id limit 1",
				Concesionario.class);
		Concesionario concesionario = null;
		try {
			concesionario = (Concesionario) q.getSingleResult();
		} catch (NoResultException ex) {

		}
		em.close();

		return concesionario;
	}

	/**
	 * 
	 * @return
	 */
	public static Concesionario findLast() {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery("SELECT * FROM tutorialjavacoches.concesionario order by id desc limit 1",
				Concesionario.class);
		Concesionario concesionario = null;
		try {
			concesionario = (Concesionario) q.getSingleResult();
		} catch (NoResultException ex) {

		}
		em.close();

		return concesionario;
	}

	/**
	 * 
	 * @param cocheActual
	 * @return
	 */
	public static Concesionario findNext(Concesionario concesionarioActual) {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery(
				"SELECT * FROM tutorialjavacoches.concesionario where id > ? order by id limit 1", Concesionario.class);
		q.setParameter(1, concesionarioActual.getId());
		Concesionario concesionario = null;
		try {
			concesionario = (Concesionario) q.getSingleResult();
		} catch (NoResultException ex) {

		}
		em.close();

		return concesionario;
	}

	/**
	 * 
	 * @param cocheActual
	 * @return
	 */
	public static Concesionario findPrevious(Concesionario concesionarioActual) {

		EntityManager em = getEntityManager();

		Query q = em.createNativeQuery(
				"SELECT * FROM tutorialjavacoches.concesionario where id < ? order by id desc limit 1",
				Concesionario.class);
		q.setParameter(1, concesionarioActual.getId());
		Concesionario concesionario = null;
		try {

			concesionario = (Concesionario) q.getSingleResult();
		} catch (NoResultException ex) {

		}
		em.close();

		return concesionario;
	}

	/**
	 * 
	 * @param coche
	 */
	public static Concesionario persist(Concesionario concesionario) {
		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			if (exist(concesionario)) {
				concesionario = em.merge(concesionario);
			} else {
				em.persist(concesionario);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		em.close();
		return concesionario;
	}

	/**
	 * 
	 */
	public static boolean exist(Concesionario concesionario) {
		EntityManager em = getEntityManager();
		boolean ok = true;
		try {
			Query q = em.createNativeQuery("", Concesionario.class);
			em.find(Concesionario.class, concesionario.getId());

		} catch (NoResultException ex) {
			ok = false;

		}
		em.close();
		return ok;
	}

	/**
	 * 
	 */
	public static boolean remove(Concesionario concesionario) {
		EntityManager em = getEntityManager();
		boolean ok = true;
		try {
			em.getTransaction().begin();
			if (exist(concesionario)) {
				concesionario = em.merge(concesionario);
				em.remove(concesionario);
				em.getTransaction().commit();
			}
		} catch (NoResultException ex) {
			ex.printStackTrace();
			ok = false;

		}
		em.close();
		return ok;
	}
}
