package es.cursosprhib.mediosdepago.persistencia;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@Repository
public class ExtractoDaoImpl implements ExtractoDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Autowired
	public ExtractoDaoImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Extracto save(Extracto entidad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Extracto> saveAll(Collection<Extracto> entidades) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Extracto entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Collection<Extracto> entidades) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Extracto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Extracto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Extracto> findAllById(Collection<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Extracto findExtracto(Cuenta cuenta, int anyo, int mes) {
		em = emf.createEntityManager();
		Extracto resu;
		String jpql = 
			"select e from Extracto e "
			+ "join fetch e.movimientos m "
			+ "join fetch m.tipo tm "
			+ "join fetch e.cuenta c "
			+ "join fetch c.tarjetas t "
			+ "where e.cuenta = :cuenta and e.anyo = :anyo and e.mes = :mes ";
		TypedQuery<Extracto> q = em.createQuery(jpql, Extracto.class);
		q.setParameter("cuenta", cuenta);
		q.setParameter("anyo", anyo);
		q.setParameter("mes", mes);
		try {
			resu = q.getSingleResult();
		} catch(NoResultException e) {
			resu = null;
		}
		em.close();
		return resu;
	}

}
