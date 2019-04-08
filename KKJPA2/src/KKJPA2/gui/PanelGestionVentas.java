package KKJPA2.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KKJPA2.modelo.Cliente;
import KKJPA2.modelo.Coche;
import KKJPA2.modelo.Concesionario;
import KKJPA2.modelo.Fabricante;
import KKJPA2.modelo.Venta;
import KKJPA2.modelo.controladores.ControladorBBDDCliente;
import KKJPA2.modelo.controladores.ControladorBBDDCoche;
import KKJPA2.modelo.controladores.ControladorBBDDVenta;
import KKJPA2.modelo.controladores.ControladorBBDDConcesionario;
import KKJPA2.modelo.controladores.ControladorBBDDFabricante;
import KKJPA2.modelo.controladores.ControladorBBDDVenta;

public class PanelGestionVentas extends JPanel {

	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	JTextField jtfId = new JTextField();
	JComboBox<Cliente>jcbIdCliente = new JComboBox<Cliente>();
	JComboBox<Coche>jcbIdCoche = new JComboBox<Coche>();
	JComboBox<Concesionario>jcbIdConcesionario = new JComboBox<Concesionario>();
	JTextField jtfFecha = new JTextField();
	JTextField jtfPrecioVenta = new JTextField();
	JButton jbtNavPrimero = new JButton();
	JButton jbtNavUltimo = new JButton();
	JButton jbtNavAnterior = new JButton();
	JButton jbtNavSiguiente = new JButton();
	JButton jbtGuardar = new JButton();
	JButton jbtNuevo = new JButton();
	JButton jbtEliminar = new JButton();
	
	Venta venta = new Venta ();
	
	/**
	 * 
	 */
	public PanelGestionVentas () {
		
		this.maquetarVentana();
		
		// Manejo de la navegaci�n de registros por rueda de rat�n
		this.controlRuedaRaton();
		
		// Finalmente, despu�s de la construcci�n, cargamos el primer registro
		navegaAPrimero();
	}
	
	
	/**
	 * 
	 */
	private void controlRuedaRaton () {
		this.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getUnitsToScroll() < 0) {
					if (ControladorBBDDVenta.findNext(venta) != null) {
						navegaASiguiente();
					}
				}
				else {
					if (ControladorBBDDVenta.findPrevious(venta) != null) {
						navegaAAnterior();
					}
				}
			}
		});
	}
	
	/**
	 * 
	 */
	private void maquetarVentana() {
		double pesoCol1 = 0.15, pesoCol2 = 1;
		
		this.setLayout(new GridBagLayout());

		gridBagConstraints.insets = new Insets(5, 5, 5, 5);

		// Incorporamos los components del Id
		colocaComponente(0, 0, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Id:"), gridBagConstraints);
		
		colocaComponente(1, 0, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.jtfId.setEnabled(false);
		this.add(jtfId, gridBagConstraints);
		
		// Incorporamos el fabricante
		colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("IdCliente:"), gridBagConstraints);
		
		colocaComponente(1, 1, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		inicializaComboBoxCliente();
		this.add(jcbIdCliente, gridBagConstraints);
		
		// Incorporamos el bastidor
		colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("IdCoche:"), gridBagConstraints);
		
		colocaComponente(1, 2, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		inicializaComboBoxCoche();
		this.add(jcbIdCoche, gridBagConstraints);
		
		// Incorporamos el Modelo
		colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("IdConcesionario:"), gridBagConstraints);
		
		colocaComponente(1, 3, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		inicializaComboBoxConcesionario();
		this.add(jcbIdConcesionario, gridBagConstraints);
		
		// Incorporamos el Color
		colocaComponente(0, 4, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Fecha:"), gridBagConstraints);
		
		colocaComponente(1, 4, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtfFecha, gridBagConstraints);
		
		// Incorporamos el Color
		colocaComponente(0, 5, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Precio Venta:"), gridBagConstraints);
				
		colocaComponente(1, 5, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtfPrecioVenta, gridBagConstraints);
		
		// Incorporamos fila botones
		colocaComponente(0, 7, GridBagConstraints.NORTH, 1, 1, GridBagConstraints.BOTH);
		gridBagConstraints.gridwidth = 2;
		this.add(getBotonera(), gridBagConstraints);		
	}
	
	
	/**
	 * 
	 */
	private void inicializaComboBoxCoche() {
		List<Coche> coches = ControladorBBDDCoche.findAll();
		for (Coche coche : coches) {
			jcbIdCoche.addItem(coche);
		}
	}
	
	/**
	 * 
	 */
	private void inicializaComboBoxCliente() {
		List<Cliente> clientes = ControladorBBDDCliente.findAll();
		for (Cliente cliente : clientes) {
			jcbIdCliente.addItem(cliente);
		}
	}
	
	/**
	 * 
	 */
	private void inicializaComboBoxConcesionario() {
		List<Concesionario> concesionarios = ControladorBBDDConcesionario.findAll();
		for (Concesionario concesionario : concesionarios) {
			jcbIdConcesionario.addItem(concesionario);
		}
	}
	
	/**
	 * 
	 */
	private JPanel getBotonera() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		panel.setBackground(Color.yellow);
		
		// Configuramos los botones
		configuraBoton(jbtNavPrimero, "gotostart.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				navegaAPrimero();
			}
		});
		configuraBoton(jbtNavAnterior, "previous.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				navegaAAnterior();
			}
		});
		configuraBoton(jbtNavSiguiente, "next.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				navegaASiguiente();
			}
		});
		configuraBoton(jbtNavUltimo, "gotoend.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				navegaAUltimo();
			}
		});
		configuraBoton(jbtGuardar, "guardar.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		configuraBoton(jbtNuevo, "nuevo.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				nuevoCoche();
			}
		});
		configuraBoton(jbtEliminar, "eliminar.png", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		
		//Inclu�mos los botones
		panel.add(jbtNavPrimero);
		panel.add(jbtNavAnterior);
		panel.add(jbtNavSiguiente);
		panel.add(jbtNavUltimo);
		panel.add(jbtGuardar);
		panel.add(jbtNuevo);
		panel.add(jbtEliminar);
		
		return panel;
	}

	
//	/**
//	 * 
//	 */
	private void eliminar() {
//		// Por regla general, siempre que eliminemos un coche navegaremos al siguiente
//		// registro
		
		Venta ventaAEliminar = this.venta;
		
		// Compruebo si el coche actual es el �ltimo coche
		if (ControladorBBDDVenta.findLast().getId() == this.venta.getId()) {
			navegaAAnterior();
		}
		else {
			navegaASiguiente();
		}
		
		// Finalmente elimino el coche
		ControladorBBDDVenta.remove(ventaAEliminar);
		
		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
//	
	
	/**
	 * 
	 */
	private void nuevoCoche () {
		this.venta = new Venta();
		this.jtfId.setText("");
		this.jcbIdCliente.setSelectedIndex(0);
		this.jcbIdCoche.setSelectedIndex(0);
		this.jcbIdConcesionario.setSelectedIndex(0);
		this.jtfFecha.setText("");
		this.jtfPrecioVenta.setText("");

		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 * @param jbt
	 * @param icono
	 */
	private void configuraBoton (JButton jbt, String icono, ActionListener actionListener) {
		jbt.setText((icono));
		jbt.addActionListener(actionListener);
	}
	
	
	

	private void guardar() {
		// Es un alta nueva o una modificaci�n
		cargaVentaDesdeComponentesVisuales();
		this.venta = ControladorBBDDVenta.persist(this.venta);
		cargaVentaEnComponentesVisuales();
		if (!ControladorBBDDVenta.exist(this.venta)) {
			this.navegaAUltimo();
		}

		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAPrimero () {
		venta = ControladorBBDDVenta.findFirst();
		cargaVentaEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAUltimo () {
	venta = ControladorBBDDVenta.findLast();
		cargaVentaEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaASiguiente () {
		venta = ControladorBBDDVenta.findNext(this.venta);
		cargaVentaEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAAnterior () {
		venta = ControladorBBDDVenta.findPrevious(this.venta);
		cargaVentaEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	
	
//	/**
//	 * 
//	 */
private void actualizaEstadoBotonera () {
		if (ControladorBBDDVenta.findPrevious(this.venta) == null) {
			jbtNavPrimero.setEnabled(false);
			jbtNavAnterior.setEnabled(false);
		}
		else {
			jbtNavPrimero.setEnabled(true);
			jbtNavAnterior.setEnabled(true);
		}
		if (ControladorBBDDVenta.findNext(this.venta) == null) {
			jbtNavSiguiente.setEnabled(false);
			jbtNavUltimo.setEnabled(false);
		}
		else {
			jbtNavSiguiente.setEnabled(true);
			jbtNavUltimo.setEnabled(true);
		}
		if (this.venta.getId() != -1) {
			jbtEliminar.setEnabled(true);
		}
		else {
			jbtEliminar.setEnabled(false);
		}
	}
	
	/**
	 * 
	 */
	private void cargaVentaEnComponentesVisuales () {
		this.jtfId.setText("" + venta.getId());
		 //Cargo el valor del JComboBox del cliente
		for (int i = 0; i < this.jcbIdCliente.getItemCount(); i++) {
			if (venta.getCliente().getId() == this.jcbIdCliente.getItemAt(i).getId()) {
				this.jcbIdCliente.setSelectedIndex(i);
				break;
			}
		}		
		//Cargo el valor del JComboBox del concesionario
				for (int i = 0; i < this.jcbIdConcesionario.getItemCount(); i++) {
					if (venta.getConcesionario().getId() == this.jcbIdConcesionario.getItemAt(i).getId()) {
						this.jcbIdConcesionario.setSelectedIndex(i);
						break;
					}
				}		
				//Cargo el valor del JComboBox del coche
				for (int i = 0; i < this.jcbIdCoche.getItemCount(); i++) {
					if (venta.getCoche().getId() == this.jcbIdCoche.getItemAt(i).getId()) {
						this.jcbIdCoche.setSelectedIndex(i);
						break;
					}
				}		
				SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy");	
			jtfFecha.setText(sdf2.format(venta.getFecha()));
			jtfPrecioVenta.setText("" + venta.getPrecioVenta());
			
	}
	
	/**
	 * 
	 */
	private void cargaVentaDesdeComponentesVisuales () {
		this.venta.setCliente((Cliente)this.jcbIdCliente.getSelectedItem());
		SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy");
		this.venta.setCoche((Coche)this.jcbIdCoche.getSelectedItem());
		try {
			this.venta.setFecha(sdf2.parse(this.jtfFecha.getText()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.venta.setConcesionario(((Concesionario) this.jcbIdConcesionario.getSelectedItem()));
		this.venta.setPrecioVenta(Float.parseFloat(this.jtfPrecioVenta.getText()));
	}
	
	/**
	 * 
	 * @param gridX
	 * @param gridY
	 * @param pesoColumna
	 * @param pesoFila
	 * @param fill
	 */
	private void colocaComponente (int gridX, int gridY, int anchor, double pesoColumna, double pesoFila, int fill) {
		gridBagConstraints.gridx = gridX;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.anchor = anchor;
		gridBagConstraints.weightx = pesoColumna;
		gridBagConstraints.weighty = pesoFila;
		gridBagConstraints.fill = fill;
		
	}
	
}
