package es.cursosprhib.mediosdepago.vista.swing;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import es.cursosprhib.mediosdepago.modelo.Cliente;
import es.cursosprhib.mediosdepago.modelo.Cuenta;
import es.cursosprhib.mediosdepago.modelo.Extracto;
import es.cursosprhib.mediosdepago.modelo.Movimiento;
import es.cursosprhib.mediosdepago.modelo.Tarjeta;
import es.cursosprhib.mediosdepago.servicio.GeneraExtracto;

public class VExtracto extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblTotal;
	private JButton bConsultar;
	private JButton bSalir;
	private JLabel nombre;
	private JTable tabla;
	private JComboBox<Cuenta> comboCuenta;
	private JFormattedTextField txtAnyo;
	private JComboBox<Integer> comboMes;
	
	private final Font labelFont = new Font(Font.DIALOG, Font.BOLD, 16);
	private final Font textFont = new Font(Font.DIALOG_INPUT, Font.ITALIC, 16);
	private final Font listaFont = new Font(Font.DIALOG, Font.PLAIN, 16);
	private final Font tituloLista = new Font(Font.DIALOG, Font.BOLD, 24);
	private DefaultTableModel modelo;
	
	private static final String TITULO = "Clientes"; 
	
	private String[][] datosTabla;
	private String[] titulosTabla = { "Tarjeta", "Fecha", "Proveedor", "Importe", "Totales"};
	
	
	private GeneraExtracto neg;
	private Extracto ext;
	private Cuenta cuentaElegida;
	private int anyoElegido;
	private int mesElegido;

	private VInicial padre;
	

	VExtracto(GeneraExtracto neg, Cliente clienteActual, VInicial padre) {
		super(TITULO);
		setResizable(false);
		setBounds(100, 10, 918, 746);
		getContentPane().setLayout(null);

		this.neg = neg;
		this.padre = padre;
		bloquearPadre();
		ManejaEventos manejador = new ManejaEventos();

		nombre = new JLabel(clienteActual.getNombreYApellidos());
		nombre.setBounds(10, 20, 400, 25);
		nombre.setFont(tituloLista);

		JLabel lblCuenta = new JLabel("Cuenta: ");
		lblCuenta.setBounds(420, 5, 120, 20);
		lblCuenta.setFont(labelFont);

		comboCuenta = new JComboBox<>(clienteActual.getCuentas().toArray(new Cuenta[] {}));
		comboCuenta.setBounds(420, 30, 315, 25);
		comboCuenta.setFont(textFont);
		comboCuenta.addActionListener(manejador);

		JLabel lblAnyo = new JLabel("Año: ");
		lblAnyo.setBounds(745, 5, 50, 20);
		lblAnyo.setFont(labelFont);

		txtAnyo = new JFormattedTextField (4);
		txtAnyo.setBounds(745, 30, 60, 25);
		txtAnyo.setFont(textFont);
		txtAnyo.setValue(new GregorianCalendar().get(Calendar.YEAR));
		
		anyoElegido = new GregorianCalendar().get(Calendar.YEAR);
		
		JLabel lblMes = new JLabel("Mes: ");
		lblMes.setBounds(815, 5, 50, 20);
		lblMes.setFont(labelFont);
		
		comboMes = new JComboBox<Integer>(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12});
		comboMes.setBounds(815, 30, 50, 25);
		comboMes.setFont(textFont);
		comboMes.setSelectedIndex(new GregorianCalendar().get(Calendar.MONTH));
		comboMes.addActionListener(manejador);

		JPanel panelIngreso = new JPanel();
		panelIngreso.setBounds(10, 10, 890, 70);
		panelIngreso.setLayout(null);
		panelIngreso.setBackground(Color.LIGHT_GRAY);
		panelIngreso.add(nombre);
		panelIngreso.add(lblCuenta);
		panelIngreso.add(comboCuenta);
		panelIngreso.add(lblAnyo);
		panelIngreso.add(txtAnyo);
		panelIngreso.add(lblMes);
		panelIngreso.add(comboMes);

		add(panelIngreso);

		modelo = new DefaultTableModel(datosTabla, titulosTabla);
		tabla = new JTable(modelo);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.setFont(this.listaFont);
		tabla.addMouseListener(manejador);
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

		lblTotal = new JLabel();
		lblTotal.setBounds(200, 10, 450, 30);
		lblTotal.setFont(tituloLista);

		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(10, 630, 890, 50);
		panelBotones.setLayout(null);
		panelBotones.setBackground(Color.LIGHT_GRAY);
		panelBotones.add(bConsultar);
		panelBotones.add(lblTotal);
		panelBotones.add(bSalir);

		add(panelBotones);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				bSalir.doClick();
			}
		});
		
		comboCuenta.setSelectedIndex(0);
	}
	
	private void tablaSetEditableFalse(){
		for (int c = 0; c < tabla.getColumnCount(); c++)
		{
		    Class<?> col_class = tabla.getColumnClass(c);
		    tabla.setDefaultEditor(col_class, null);        // remove editor
		}
	}

	private void actulizaTabla() {
		DefaultTableCellRenderer alDerecha = new DefaultTableCellRenderer();
		alDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
		
		modelo.setDataVector(datosTabla, titulosTabla);

		tabla.getColumn("Tarjeta").setMinWidth(180);
		tabla.getColumn("Fecha").setMinWidth(100);
		tabla.getColumn("Proveedor").setPreferredWidth(250);
		tabla.getColumn("Importe").setMinWidth(120);
		tabla.getColumn("Totales").setMinWidth(120);

		tabla.getColumn("Importe").setCellRenderer(alDerecha);
		tabla.getColumn("Totales").setCellRenderer(alDerecha);
	}
	
	private void bloquearPadre() {
		padre.setEnabled(false);
		padre.setFocusableWindowState(false);
	}

	private void salir() {
		padre.setEnabled(true);
		padre.setFocusableWindowState(true);
		padre.bBuscar.doClick();
		padre.tabla.changeSelection(padre.filaActualTabla, 0, false, false);
		padre.tabla.requestFocus();
		padre.cargaTabla();
		dispose();
	}


	private class ManejaEventos extends MouseAdapter implements ActionListener{
		
		public void mouseClicked(MouseEvent eve) {
			if (eve.getSource() == tabla && eve.getClickCount() == 2) {
				bConsultar.doClick();
			}
		}
		private String[][] entidadToMatriz() {
			String[][] resultado = {{}};
			lblTotal.setText("TOTAL EXTRACTO: ");
			if (ext != null) {

				Set<Tarjeta> tarjetas = ext.getCuenta().getTarjetas();
//				Set<Tarjeta> tarjetas = neg.getTarjetas(ext); 
				
				int cantTarjetas = tarjetas.size();
				int cantMovs = ext.getMovimientos().size();

				// 1 fila por tarjeta para titulo y 1 para total y una intermedia + 1 al final y una total
				int totFilas = cantTarjetas * 3  + cantMovs + 1;

				resultado = new String[totFilas][];
				
				double totalExtracto = 0;
				int i = 0;
				for (Tarjeta tjta : tarjetas) {
					double totalTarjeta = 0;
					List<Movimiento> movs = neg.getMovimientos(ext, tjta);
					resultado[i] = new String[] {"","","","",""};
					resultado[i++][0] = tjta.getPan();
					for (Movimiento mov : movs) {
						resultado[i++] = entidadToArray(mov);
						totalTarjeta += mov.getImporte();
					}
					resultado[i] = new String[] {"","","","",""};
					resultado[i++][4] =  String.format("%,.2f", totalTarjeta);
					resultado[i++] = new String[] {"","","",""};
					totalExtracto += totalTarjeta;
				}
				resultado[i] = new String[] {"","","","",""};
				resultado[i][2] = "          TOTAL EXTRACTO";
				resultado[i][4] =  String.format("%,.2f", totalExtracto);
				lblTotal.setText("TOTAL EXTRACTO: " + resultado[i][4]);
			}
			return resultado;
		}
		
		private String[] entidadToArray(Movimiento mov){
			String[] res = new String[] {"","","","",""};
			res[1] = mov.getFechaFormat();
			res[2] = mov.getProveedor();
			res[3] = String.format("%,.2f", mov.getImporte());
			return res;
		}

		private void actualizaFiltros() {
			cuentaElegida = comboCuenta.getItemAt(comboCuenta.getSelectedIndex());
			anyoElegido = (Integer)txtAnyo.getValue();
			mesElegido = comboMes.getSelectedIndex()+1;
		}
		
		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == comboCuenta || ev.getSource() == comboMes) {
				actualizaFiltros();
				ext = neg.generaExtracto(cuentaElegida, anyoElegido, mesElegido);
				datosTabla = entidadToMatriz();
				actulizaTabla();
			}
			if (ev.getSource() == bSalir) {
				salir();
//				if (JOptionPane.showConfirmDialog(null,
//						"Desea abandonar la consulta ", "Salir de " + ENTIDAD, 2) == 0){
//					salir();
//				}
			}
		}
		
	}
}
