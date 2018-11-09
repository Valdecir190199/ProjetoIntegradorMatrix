package banco;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAOGenerico<T> {

	private static EntityManager entityManager;
	private Class<T> classe;

	public DAOGenerico(Class<T> classe) {
		this.classe = classe;
	}

	public T salvar(T objeto) {
		entityManager = Fabrica.get().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(objeto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return objeto;
	}

	public T alterar(T objeto) {
		entityManager = Fabrica.get().createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(objeto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return objeto;
	}

	public boolean excluir(Long id) {
		entityManager = Fabrica.get().createEntityManager();
		try {			
			T objeto = entityManager.find(classe, id);
			if (objeto != null) {
				entityManager.getTransaction().begin();
				entityManager.remove(objeto);
				entityManager.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			entityManager.close();
		}
		return false;
	}

	public List<T> buscarTodos() {
		entityManager = Fabrica.get().createEntityManager();
		Query query = null;
		try {
			query = entityManager.createQuery("from " + classe.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query.getResultList();
	}

	public T buscarPorId(Long id) {
		entityManager = Fabrica.get().createEntityManager();
		T retornando = null;
		try {
			retornando = entityManager.find(classe, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return retornando;
	}

	public List<T> buscarCondicao(String consulta) {
		entityManager = Fabrica.get().createEntityManager();
		Query query = null;
		try {
			query = entityManager.createQuery("from " + classe.getSimpleName() + " where " + consulta);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return query.getResultList();
	}

}