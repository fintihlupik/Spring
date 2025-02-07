package persistencia.jpa;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.TypedQuery;
import persistencia.IDaoGenerico;

public abstract class DaoGenericoImpl<T extends Comparable<T>, ID> implements IDaoGenerico<T, ID>{
	
	protected EntityManager em;
	private  Class<T> claseEntidad;
	
	public DaoGenerico(Class<T> entidad){
		this.claseEntidad = entidad;
	}
	
	@SuppressWarnings("unchecked")
	public DaoGenerico() {
		claseEntidad = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	@Override
	public T save(T entidad) {
		T nueva = null;
		em = EMFactory.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			nueva = em.merge(entidad);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return nueva;
	}

	@Override
	public T findById(ID primaryKey) {
		em = EMFactory.getEmf().createEntityManager();
		T entidad = em.find(claseEntidad, primaryKey);
		em.close();
		return entidad;
	}

	@Override
	public List<T> saveAll(Collection<T> entidades) {
		List<T> actualizados = new ArrayList<>();
		em = EMFactory.getEmf().createEntityManager();
		em.getTransaction().begin();
		try {
			for (T t : entidades) {
				actualizados.add(em.merge(t));
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		return actualizados;
	}

	@Override
	public long count() {
		int cant = 0;
		em = EMFactory.getEmf().createEntityManager();
		cant = (int)em.createQuery("select count(e) from " + claseEntidad.getName() + " e", Long.class).getSingleResult().longValue();
		em.close();
		return cant;
	}

	@Override
	public void delete(T entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Collection<T> entidades) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(ID id) {
		T entidad = findById(id);
		return entidad != null;  
	}

	@Override
	public List<T> findAll() {
		List<T> entidades;
		em = EMFactory.getEmf().createEntityManager();
		entidades = em.createQuery("select e from " + claseEntidad.getName() + " e", claseEntidad).getResultList();
		em.close();
		Collections.sort(entidades);
		return entidades;
	}

	@Override
	public List<T> findAllById(Collection<ID> ids) {
		List<T> entidades;
		em = EMFactory.getEmf().createEntityManager();
		String query = "select e from " + claseEntidad.getName() + " e where e." + findNombreId() + " in :ids";
		TypedQuery<T> tq = em.createQuery(query, claseEntidad);
		tq.setParameter("ids", ids);
		entidades = tq.getResultList();
		Collections.sort(entidades);
		em.close();
		return entidades;
	}
	
	// Por reflexion, devuelve el nombre de la propiedad (att o metodo) anotada con @Id
	private String findNombreId() {
		Field f = null;
	    Method m = null;
	    Class<?> c = claseEntidad;
	    while (c != null) {
	        for (Field field : c.getDeclaredFields()) {
	            if (field.isAnnotationPresent(Id.class)) {
	                f = field;
	            }
	        }
	        c = c.getSuperclass();
	    }
	    c = claseEntidad;
	    while (c != null) {
	        for (Method metodo : c.getDeclaredMethods()) {
	            if (metodo.isAnnotationPresent(Id.class)) {
	                m = metodo;
	            }
	        }
	        c = c.getSuperclass();
	    }
	    if (f != null) {
			return f.getName();
		} else {
			String inicial = m.getName().substring(3, 4).toLowerCase();
			String nombreMetodoSinInicial = m.getName().substring(4);
			return inicial+nombreMetodoSinInicial;
		}
	}


//	private static Set<Field> findFields(Class<?> classs, Class<? extends Annotation> ann) {
//		Set<Field> set = new HashSet<>();
//		Class<?> c = classs;
//		while (c != null) {
//			for (Field field : c.getDeclaredFields()) {
//				if (field.isAnnotationPresent(ann)) {
//					set.add(field);
//				}
//			}
//			c = c.getSuperclass();
//		}
//		return set;
//	}
}
