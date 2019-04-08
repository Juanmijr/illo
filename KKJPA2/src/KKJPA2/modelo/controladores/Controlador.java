package KKJPA2.modelo.controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controlador {

	
	private static EntityManagerFactory entityManagerFactory = null;
	
	protected static EntityManager getEntityManager () {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("KKJPA2");
		}
		return entityManagerFactory.createEntityManager();
	}
}
