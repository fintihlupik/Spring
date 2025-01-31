package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cDao") //Si no ponemos nombre se llamará bancoDaoImpl
public class BancoDaoImpl implements BancoDao {
	
	@Autowired
	private DataSource dataSource;

	public BancoDaoImpl() {
	}

	public void actualizaSaldo(long dni, double incrementoSaldo) {
		String sql = "update clientes set saldo = saldo + ? where dni = ?";

		try (Connection con = dataSource.getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, incrementoSaldo);
			ps.setLong(2, dni);
			
			int n = ps.executeUpdate();
			if (n == 1) {
				System.out.println("Se ha incrementado el saldo de " + dni
						+ " en " + incrementoSaldo + " euros");
			} else {
				System.out.println("No existe el cliente con el dni " + dni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}