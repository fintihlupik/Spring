package es.cursosprhib.mediosdepago.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.cursosprhib.mediosdepago.modelo.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Integer>, ClienteDaoCustom{
	
	Cliente findByNif(String nif);
	
	@Query("select c from Cliente c where c.nombre like %?1% or c.apellido1 like %?1% or c.apellido2 like %?1% ")
	public List<Cliente> findByNombre(String nombre);
	
	@Query("select c from Cliente c left join fetch c.cuentas where c.idPersona = ?1")
	public Cliente findByIdEager(Integer id);
	
	default Optional<Cliente> findById(Integer id){
		return Optional.ofNullable(findByIdEager(id));
	}
	
	@Query(nativeQuery = true, value = "select * from clientes join person ...")
	public List<Cliente> noUsar();
	
}
