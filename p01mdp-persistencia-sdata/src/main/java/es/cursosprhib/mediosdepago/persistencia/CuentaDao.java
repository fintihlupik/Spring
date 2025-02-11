package es.cursosprhib.mediosdepago.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import es.cursosprhib.mediosdepago.modelo.Cuenta;

public interface CuentaDao extends JpaRepository<Cuenta, Integer>{

}
