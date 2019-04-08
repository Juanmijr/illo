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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KKJPA2.modelo.Coche;
import KKJPA2.modelo.Fabricante;
import KKJPA2.modelo.controladores.ControladorBBDDCoche;
import KKJPA2.modelo.controladores.ControladorBBDDFabricante;


public class PanelGestionCoche extends JPanel {

	GridBagConstraints gridBagConstraints = new GridBagConstraints();
	JTextField jtfId = new JTextField();
	JComboBox<Fabricante> jcbFabricante = new JComboBox<Fabricante>();
	JTextField jtfBastidor = new JTextField();
	JTextField jtfModelo = new JTextField();
	JTextField jtfColor = new JTextField();
	JButton jbtNavPrimero = new JButton();
	JButton jbtNavUltimo = new JButton();
	JButton jbtNavAnterior = new JButton();
	JButton jbtNavSiguiente = new JButton();
	JButton jbtGuardar = new JButton();
	JButton jbtNuevo = new JButton();
	JButton jbtEliminar = new JButton();
	
	Coche coche = new Coche(); // Coche mostrado en pantalla
	
	/**
	 * 
	 */
	public PanelGestionCoche () {
		
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
					if (ControladorBBDDCoche.findNext(coche) != null) {
						navegaASiguiente();
					}
				}
				else {
					if (ControladorBBDDCoche.findPrevious(coche) != null) {
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
		this.add(new JLabel("Fabricante:"), gridBagConstraints);
		
		colocaComponente(1, 1, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		inicializaComboBoxFabricante();
		this.add(jcbFabricante, gridBagConstraints);
		
		// Incorporamos el bastidor
		colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("IdBastidor:"), gridBagConstraints);
		
		colocaComponente(1, 2, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtfBastidor, gridBagConstraints);
		
		// Incorporamos el Modelo
		colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Modelo:"), gridBagConstraints);
		
		colocaComponente(1, 3, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtfModelo, gridBagConstraints);
		
		// Incorporamos el Color
		colocaComponente(0, 4, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
		this.add(new JLabel("Color:"), gridBagConstraints);
		
		colocaComponente(1, 4, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
		this.add(jtfColor, gridBagConstraints);
		
		// Incorporamos fila botones
		colocaComponente(0, 5, GridBagConstraints.NORTH, 1, 1, GridBagConstraints.BOTH);
		gridBagConstraints.gridwidth = 2;
		this.add(getBotonera(), gridBagConstraints);		
	}
	
	
	/**
	 * 
	 */
	private void inicializaComboBoxFabricante () {
		List<Fabricante> fabricantes = ControladorBBDDFabricante.findAll();
		for (Fabricante fabricante : fabricantes) {
			jcbFabricante.addItem(fabricante);
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
		
		Coche cocheAEliminar = this.coche;
		
		// Compruebo si el coche actual es el �ltimo coche
		if (ControladorBBDDCoche.findLast().getId() == this.coche.getId()) {
			navegaAAnterior();
		}
		else {
			navegaASiguiente();
		}
		
		// Finalmente elimino el coche
		ControladorBBDDCoche.remove(cocheAEliminar);
		
		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
//	
	
	/**
	 * 
	 */
	private void nuevoCoche () {
		this.coche = new Coche();
		this.jtfId.setText("");
		this.jcbFabricante.setSelectedIndex(0);
		this.jtfBastidor.setText("");
		this.jtfModelo.setText("");
		this.jtfColor.setText("");

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
		cargaCocheDesdeComponentesVisuales();
		this.coche = ControladorBBDDCoche.persist(this.coche);
		cargaCocheEnComponentesVisuales();
		if (!ControladorBBDDCoche.exist(this.coche)) {
			this.navegaAUltimo();
		}

		// Actualizo la botonera
		this.actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAPrimero () {
		coche = ControladorBBDDCoche.findFirst();
		cargaCocheEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAUltimo () {
	coche = ControladorBBDDCoche.findLast();
		cargaCocheEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaASiguiente () {
		coche = ControladorBBDDCoche.findNext(this.coche);
		cargaCocheEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	/**
	 * 
	 */
	private void navegaAAnterior () {
		coche = ControladorBBDDCoche.findPrevious(this.coche);
		cargaCocheEnComponentesVisuales();
		actualizaEstadoBotonera();
	}
	
	
	
//	/**
//	 * 
//	 */
private void actualizaEstadoBotonera () {
		if (ControladorBBDDCoche.findPrevious(this.coche) == null) {
			jbtNavPrimero.setEnabled(false);
			jbtNavAnterior.setEnabled(false);
		}
		else {
			jbtNavPrimero.setEnabled(true);
			jbtNavAnterior.setEnabled(true);
		}
		if (ControladorBBDDCoche.findNext(this.coche) == null) {
			jbtNavSiguiente.setEnabled(false);
			jbtNavUltimo.setEnabled(false);
		}
		else {
			jbtNavSiguiente.setEnabled(true);
			jbtNavUltimo.setEnabled(true);
		}
		if (this.coche.getId() != -1) {
			jbtEliminar.setEnabled(true);
		}
		else {
			jbtEliminar.setEnabled(false);
		}
	}
	
	/**
	 * 
	 */
	private void cargaCocheEnComponentesVisuales () {
		this.jtfId.setText("" + coche.getId());
		this.jtfBastidor.setText(coche.getBastidor());
		this.jtfModelo.setText(coche.getModelo());
		this.jtfColor.setText(coche.getColor());
		 //Cargo el valor del JComboBox del fabricante
		for (int i = 0; i < this.jcbFabricante.getItemCount(); i++) {
			if (coche.getFabricante().getId() == this.jcbFabricante.getItemAt(i).getId()) {
				this.jcbFabricante.setSelectedIndex(i);
				break;
			}
		}		
	}
	
	/**
	 * 
	 */
	private void cargaCocheDesdeComponentesVisuales () {
		this.coche.setBastidor(this.jtfBastidor.getText());
		this.coche.setModelo(this.jtfModelo.getText());
		this.coche.setColor(this.jtfColor.getText());
		this.coche.setFabricante(((Fabricante) this.jcbFabricante.getSelectedItem()));
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
