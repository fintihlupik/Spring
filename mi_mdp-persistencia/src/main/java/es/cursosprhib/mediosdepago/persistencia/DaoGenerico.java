package es.cursosprhib.mediosdepago.persistencia;

import java.util.Collection;
import java.util.List;

public interface DaoGenerico<T , ID> {

	public T save(T entidad);
	public List<T> saveAll(Collection<T> entidades);

	public long count();

	public void delete(T entidad);
	public void deleteAll(Collection<T> entidades);

	public boolean existsById(ID id);

	public T findById(ID id);
	
	public default T findByIdEager(ID id) {
		return findById(id);
	};
	
	public List<T> findAll();
	public List<T> findAllById(Collection<ID> ids);

	public default List<T> findAllEager(){
		return findAll();
	};
}
