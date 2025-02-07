package es.cursosprhib.mediosdepago.vista.swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.cursosprhib.mediosdepago.modelo.Cliente;
import es.cursosprhib.mediosdepago.servicio.GeneraExtracto;
import jakarta.annotation.PostConstruct;

@Component
@Profile({"grafico"})
public class VInicial extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private JTextField tBusca;
	JButton bBuscar;
	private JLabel cantidad;
	private JButton bConsultar;
	private JButton bSalir;
	private JLabel nombre;
	JTable tabla;
	private Font labelFont = new Font(Font.DIALOG, Font.BOLD, 16);
	private Font textFont = new Font(Font.DIALOG_INPUT, Font.ITALIC, 16);
	private Font listaFont = new Font(Font.DIALOG, Font.PLAIN, 16);
	private DefaultTableModel modelo;
	
	private final String APP = "Medios de Pago";
	private final String VERSION = "v 1.0";
	private final String ENTIDAD = "Cliente";
	private final String ENTIDADES = "Clientes";
	private static final String TITULO = "Clientes"; 
	
	int filaActualTabla;
	private String[][] datosTabla;
	private String[] titulosTabla = { "Nombre", "Apellidos", "NIF", "Sexo", "Municipio", "Provincia" };
	
	
	private GeneraExtracto neg;
	private List<Cliente> clientes;
	private Cliente clienteActual;
	
	public VInicial(GeneraExtracto neg) {
		super(TITULO);
		this.neg = neg;
		System.out.println("vista");
	}

	@PostConstruct
	private void init() {
		setResizable(false);
		setBounds(100, 10, 918, 746);
		getContentPane().setLayout(null);

		
		ManejaEventos manejador = new ManejaEventos();

		armaMenu();
		
		nombre = new JLabel(ENTIDAD + ": ");
		nombre.setBounds(10, 10, 80, 30);
		nombre.setFont(labelFont);
		add(nombre);

		tBusca = new JTextField();
		tBusca.setBounds(10, 40, 400, 30);
		tBusca.setFont(textFont);
		add(tBusca);
		tBusca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bBuscar.doClick();
			}
		});

		bBuscar = new JButton("Buscar");
		bBuscar.setBounds(420, 40, 80, 30);
		add(bBuscar);
		bBuscar.addActionListener(manejador);
		
		cantidad = new JLabel();
		cantidad.setBounds(600, 40, 250, 30);
		cantidad.setFont(labelFont);
		add(cantidad);

		modelo = new DefaultTableModel(datosTabla, titulosTabla);
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setFont(this.listaFont);
		tabla.addMouseListener(manejador);
		tabla.addKeyListener(manejador);
		tabla.setAutoCreateRowSorter(true);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaSetEditableFalse();
		actulizaTabla();

		JScrollPane barras = new JScrollPane(tabla,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		barras.setBounds(10, 90, 890, 530);
		add(barras);

		bConsultar = new JButton("Detalle");
		bConsultar.setBounds(10, 10, 80, 30);
		bConsultar.addActionListener(manejador);
		bConsultar.setEnabled(false);

		bSalir = new JButton("Salir");
		bSalir.setBounds(800, 10, 80, 30);
		bSalir.addActionListener(manejador);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 630, 890, 50);
		panelBotones.setLayout(null);
		panelBotones.setBackground(Color.LIGHT_GRAY);
		panelBotones.add(bConsultar);
		panelBotones.add(bSalir);

		add(panelBotones);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				bSalir.doClick();
			}
		});

	}
	
	private void armaMenu(){
		JMenu menuArchivo = new JMenu( "Archivo" ); 
		menuArchivo.setMnemonic( 'A' ); 

		JMenuItem importar = new JMenuItem( "Importar..." );
		importar.setMnemonic( 'I' );
		menuArchivo.add(importar);
		importar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importar();
			}
		});

		JMenuItem exportar = new JMenuItem( "Exportar..." );
		importar.setMnemonic( 'E' );
		menuArchivo.add(exportar);
		exportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportar();
			}
		});

		JMenuItem opciones = new JMenuItem( "Opciones..." );
		opciones.setMnemonic( 'O' );
		menuArchivo.add(opciones);
		opciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				opciones();
			}
		});

		JMenuItem elementoAcercaDe = new JMenuItem( "Acerca de..." );
		elementoAcercaDe.setMnemonic( 'c' );
		menuArchivo.add( elementoAcercaDe );
		elementoAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, APP + " " + VERSION + "\n", "Acerca de", JOptionPane.PLAIN_MESSAGE);		
			}
		});
		
		JMenuItem elementoSalir = new JMenuItem("Salir");
		elementoSalir.setMnemonic('S');
		menuArchivo.add(elementoSalir);
		elementoSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bSalir.doClick();
			}
		});
		
		JMenuBar barra = new JMenuBar();
		setJMenuBar( barra );
		barra.add( menuArchivo );
		
		
	}
	
	private void tablaSetEditableFalse(){
		for (int c = 0; c < tabla.getColumnCount(); c++)
		{
		    Class<?> col_class = tabla.getColumnClass(c);
		    tabla.setDefaultEditor(col_class, null);        // remove editor
		}
	}

	private void actulizaTabla() {
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		modelo.setDataVector(datosTabla, titulosTabla);

		tabla.getColumn("Nombre").setMinWidth(170);
		tabla.getColumn("Apellidos").setMinWidth(180);
		tabla.getColumn("NIF").setPreferredWidth(110);
		tabla.getColumn("Sexo").setMinWidth(20);
		tabla.getColumn("Municipio").setMinWidth(170);
		tabla.getColumn("Provincia").setMinWidth(165);

		tabla.getColumn("NIF").setCellRenderer(dtc);
		tabla.getColumn("Sexo").setCellRenderer(dtc);
	}
	
	void cargaTabla(){
		if (datosTabla.length != 0) {
			clienteActual = clientes.get(tabla
				.convertRowIndexToModel(tabla.getSelectedRow()));
		}else{
			clienteActual = null;
			bConsultar.setEnabled(false);
			tBusca.requestFocus();
		}
	}
	
	private void importar(){
	}

	private void exportar(){
	}

	private void opciones(){
	}

	private class ManejaEventos extends MouseAdapter implements ActionListener,
			KeyListener {

		public void keyPressed(KeyEvent arg0) {
		}

		public void keyReleased(KeyEvent arg0) {
		}
		
		public void mouseClicked(MouseEvent eve) {
			if (eve.getSource() == tabla && eve.getClickCount() == 2) {
				bConsultar.doClick();
			}
		}

		public void mousePressed(MouseEvent eve) {
			if (eve.getSource() == tabla) {
				clienteActual = clientes.get(tabla
						.convertRowIndexToModel(tabla.getSelectedRow()));
				bConsultar.setEnabled(true);
				bConsultar.requestFocus();
			}
		}

		public void keyTyped(KeyEvent eve) {
			if (eve.getSource() == tabla) {
				if (eve.getKeyChar() == KeyEvent.VK_ENTER) {
					clienteActual = clientes.get(tabla
							.convertRowIndexToModel(tabla.getSelectedRow()));
					bConsultar.setEnabled(true);
					bConsultar.requestFocus();
				} else if (eve.getKeyChar() == KeyEvent.VK_TAB) {
					bConsultar.setEnabled(true);
					bConsultar.requestFocus();
				}
			}
		}

		private String[][] entidadToMatriz() {
			String[][] resultado = new String[clientes.size()][];
			for (int i = 0; i < resultado.length; i++) {
				resultado[i] = entidadToArray(clientes.get(i));
			}
			return resultado;
		}
		
		private String[] entidadToArray(Cliente c){
			String[] res = new String[6];
			res[0] = c.getNombre();
			res[1] = c.getApellidos();
			res[2] = c.getNif();
			res[3] = c.getSexo().name();
			res[4] = c.getMunicipio();
			res[5] = c.getProvincia();
			return res;
		}

		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == bBuscar) {
				clientes = new ArrayList<>();
				if(!tBusca.getText().equals("")){
					clientes = neg.getClientes(tBusca.getText());
				}else{
					clientes = neg.getClientes("");
				}
				datosTabla = entidadToMatriz();
				cantidad.setText("Encontrados: " + datosTabla.length + " " + ENTIDADES);
				actulizaTabla();
				if (datosTabla.length == 0) {
					bConsultar.setEnabled(false);
				}
			}
			if (ev.getSource() == bConsultar) {
				filaActualTabla = tabla.getSelectedRow();
				new VExtracto(neg, neg.getClienteConCuentas(clienteActual), VInicial.this);
			}
			if (ev.getSource() == bSalir) {
				if (JOptionPane.showConfirmDialog(null, "Desea salir de la aplicacion", "Salir de " + APP, 2) == 0){
					System.exit(0);
				}
			}
		}
	}
}
