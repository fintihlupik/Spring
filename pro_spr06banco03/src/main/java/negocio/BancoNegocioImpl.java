package negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import persistencia.BancoDao;

@Service("neg")
public class BancoNegocioImpl implements BancoNegocio {

	private BancoDao bancoDao;

	@Autowired
	public BancoNegocioImpl(@Qualifier("cDao") BancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	public BancoNegocioImpl(BancoDao bancoDao, boolean xx) {
		this.bancoDao = bancoDao;
	}
	
	public void transferencia(long dni1, long dni2, double cantidad) {
		bancoDao.actualizaSaldo(dni1, cantidad);
		bancoDao.actualizaSaldo(dni2, -cantidad);
	}

	public BancoDao getBancoDao() {
		return bancoDao;
	}

	public void setBancoDao(BancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

}
