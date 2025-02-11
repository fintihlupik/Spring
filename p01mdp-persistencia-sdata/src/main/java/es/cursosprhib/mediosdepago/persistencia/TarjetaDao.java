package es.cursosprhib.mediosdepago.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cursosprhib.mediosdepago.modelo.Tarjeta;



public interface TarjetaDao extends JpaRepository<Tarjeta, Integer>{

}
