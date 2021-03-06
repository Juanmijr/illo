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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import KKJPA2.modelo.Cliente;
import KKJPA2.modelo.controladores.ControladorBBDDCliente;


public class PanelGestionCliente extends JPanel {
	public class PanelGestionConcesionarios extends JPanel {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		JTextField jtfId = new JTextField();
		JTextField jtfActivo = new JTextField();
		JTextField jtfNombre = new JTextField();
		JTextField jtfApellidos = new JTextField();
		JTextField jtfLocalidad = new JTextField();
		JTextField jtfFechaNac = new JTextField();
		JTextField jtfDniE = new JTextField();
		JButton jbtNavPrimero = new JButton();
		JButton jbtNavUltimo = new JButton();
		JButton jbtNavAnterior = new JButton();
		JButton jbtNavSiguiente = new JButton();
		JButton jbtGuardar = new JButton();
		JButton jbtNuevo = new JButton();
		JButton jbtEliminar = new JButton();
		
		Cliente cliente = new Cliente(); // Coche mostrado en pantalla
		


		/**
		 * 
		 */
		public  PanelGestionConcesionarios() {
			
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
						if (ControladorBBDDCliente.findNext(cliente) != null) {
							navegaASiguiente();
						}
					}
					else {
						if (ControladorBBDDCliente.findPrevious(cliente) != null) {
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
			
			// Incorporamos el cif
			colocaComponente(0, 1, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("Nombre:"), gridBagConstraints);
					
			colocaComponente(1, 1, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfNombre, gridBagConstraints);
			
			// Incorporamos el nombre
			colocaComponente(0, 2, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("Apellidos:"), gridBagConstraints);
			
			colocaComponente(1, 2, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfApellidos, gridBagConstraints);
			
			// Incorporamos el localidad
			colocaComponente(0, 3, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("Localidad:"), gridBagConstraints);
						
			colocaComponente(1, 3, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfLocalidad, gridBagConstraints);
			
			// Incorporamos el localidad
			colocaComponente(0, 4, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("Fecha Nacimiento:"), gridBagConstraints);
			
			colocaComponente(1, 4, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfFechaNac, gridBagConstraints);
			
			// Incorporamos el localidad
			colocaComponente(0, 5, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("Activo:"), gridBagConstraints);
						
			colocaComponente(1, 5, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfActivo, gridBagConstraints);
						
			// Incorporamos el localidad
			colocaComponente(0, 6, GridBagConstraints.EAST, pesoCol1, 0, GridBagConstraints.NONE);
			this.add(new JLabel("DNI:"), gridBagConstraints);
						
			colocaComponente(1, 6, GridBagConstraints.EAST, pesoCol2, 0, GridBagConstraints.HORIZONTAL);
			this.add(jtfDniE, gridBagConstraints);
		
			// Incorporamos fila botones
			colocaComponente(0, 9, GridBagConstraints.NORTH, 1, 1, GridBagConstraints.BOTH);
			gridBagConstraints.gridwidth = 2;
			this.add(getBotonera(), gridBagConstraints);		
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
					nuevo();
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

		
		/**
		 * 
		 */
		private void eliminar() {
			// Por regla general, siempre que eliminemos un coche navegaremos al siguiente
			// registro
			Cliente clienteAEliminar = this.cliente;
			
			// Compruebo si el coche actual es el �ltimo coche
			if (ControladorBBDDCliente.findLast().getId() == this.cliente.getId()) {
				navegaAAnterior();
			}
			else {
				navegaASiguiente();
			}
			
			// Finalmente elimino el coche
			ControladorBBDDCliente.remove(clienteAEliminar);
			
			// Actualizo la botonera
			this.actualizaEstadoBotonera();
		}
		
		
		/**
		 * 
		 */
		private void nuevo () {
			this.cliente = new Cliente();
			this.jtfId.setText("");
			this.jtf.setText("");
			this.jtfNombre.setText("");
			this.jtfLocalidad.setText("");


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
					cargaConcesionarioDesdeComponentesVisuales();
					this.cliente = ControladorBBDDCliente.persist(this.cliente);
					cargaConcesionarioEnComponentesVisuales();
					if (!ControladorBBDDCliente.exist(this.cliente)) {
						this.navegaAUltimo();
					}

					// Actualizo la botonera
					this.actualizaEstadoBotonera();
						
		}
		
		
		/**
		 * 
		 */
		private void navegaAPrimero () {
			cliente = ControladorBBDDCliente.findFirst();
			cargaConcesionarioEnComponentesVisuales();
			actualizaEstadoBotonera();
		}
		
		/**
		 * 
		 */
		private void navegaAUltimo () {
			cliente = ControladorBBDDCliente.findLast();
			cargaConcesionarioEnComponentesVisuales();
			actualizaEstadoBotonera();
		}
		
		/**
		 * 
		 */
		private void navegaASiguiente () {
			cliente = ControladorBBDDCliente.findNext(this.cliente);
			cargaConcesionarioEnComponentesVisuales();
			actualizaEstadoBotonera();
		}
		
		/**
		 * 
		 */
		private void navegaAAnterior () {
			cliente = ControladorBBDDCliente.findPrevious(this.cliente);
			cargaConcesionarioEnComponentesVisuales();
			actualizaEstadoBotonera();
		}
		
		
		
		/**
		 * 
		 */
		private void actualizaEstadoBotonera () {
			if (ControladorBBDDCliente.findPrevious(this.cliente) == null) {
				jbtNavPrimero.setEnabled(false);
				jbtNavAnterior.setEnabled(false);
			}
			else {
				jbtNavPrimero.setEnabled(true);
				jbtNavAnterior.setEnabled(true);
			}
			if (ControladorBBDDCliente.findNext(this.cliente) == null) {
				jbtNavSiguiente.setEnabled(false);
				jbtNavUltimo.setEnabled(false);
			}
			else {
				jbtNavSiguiente.setEnabled(true);
				jbtNavUltimo.setEnabled(true);
			}
			if (this.cliente.getId() != -1) {
				jbtEliminar.setEnabled(true);
			}
			else {
				jbtEliminar.setEnabled(false);
			}
		}
		
		/**
		 * 
		 */
		private void cargaConcesionarioEnComponentesVisuales () {
			this.jtfId.setText("" + cliente.getId());
			this.jtfCif.setText(cliente.getCif());
			this.jtfNombre.setText(cliente.getNombre());
			this.jtfLocalidad.setText(cliente.getLocalidad());
		}
		
		/**
		 * 
		 */
		private void cargaConcesionarioDesdeComponentesVisuales () {
			this.cliente.setCif(this.jtfCif.getText());
			this.cliente.setNombre(this.jtfNombre.getText());
			this.cliente.setLocalidad(this.jtfLocalidad.getText());

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
}
