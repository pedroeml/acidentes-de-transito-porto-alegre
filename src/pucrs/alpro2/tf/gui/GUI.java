package pucrs.alpro2.tf.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pucrs.alpro2.tf.Main;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;

import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

public class GUI extends JFrame {

	private static JPanel contentPane;
	private final Action actionHora = new ActionHora();
	private static JPanel panelMapa;
	private static JTextPane txtpnSample;
	private final Action actionData = new ActionData();
	private final Action actionDiaDaSemana = new ActionDia();
	private final Action actionDiaNoite = new ActionTurno();
	private final Action actionClima = new ActionClima();
	private final Action actionBuscar_Logradouro_DiaDaSemana = new ActionBuscar_Logradouro_DiaDaSemana();
	private final Action actionBuscar_DataInicial_DataFinal_TipoAcidente = new ActionBuscar_Periodo_TipoAcid();
	
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException  {
		EventQueue.invokeAndWait(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				String txt = "";
				try {
					txt = Main.boot();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				txtpnSample.setForeground(Color.BLACK);
				txtpnSample.setText(txt);
				GUI.update_JPanel_panelMapa();
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 */
	public GUI() throws IOException, IllegalAccessException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 710);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpnSample = new JTextPane();
		txtpnSample.setForeground(Color.RED);
		txtpnSample.setBackground(new Color(240, 240, 240));
		txtpnSample.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtpnSample.setEditable(false);
		txtpnSample.setText("AGUARDE ENQUANTO O PROGRAMA INICIALIZA...");
		txtpnSample.setBounds(174, 560, 600, 100);
		contentPane.add(txtpnSample);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(174, 50, 600, 500);
		contentPane.add(panelMapa);
		
		JButton btnHora = new JButton("Hora");
		btnHora.setAction(actionHora);
		btnHora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHora.setBounds(174, 11, 89, 23);
		contentPane.add(btnHora);
		
		JButton btnData = new JButton("Data");
		btnData.setAction(actionData);
		btnData.setBounds(273, 11, 89, 23);
		contentPane.add(btnData);
		
		JButton btnDiaDaSemana = new JButton("Dia");
		btnDiaDaSemana.setAction(actionDiaDaSemana);
		btnDiaDaSemana.setBounds(372, 11, 89, 23);
		contentPane.add(btnDiaDaSemana);
		
		JButton btnTurno = new JButton("Turno");
		btnTurno.setAction(actionDiaNoite);
		btnTurno.setBounds(472, 11, 89, 23);
		contentPane.add(btnTurno);
		
		JButton btnClima = new JButton("Clima");
		btnClima.setAction(actionClima);
		btnClima.setBounds(571, 11, 89, 23);
		contentPane.add(btnClima);
		
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.setBounds(10, 11, 89, 23);
		contentPane.add(btnAbrir);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(30, 637, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblSelecioneOTipo = new JLabel("Tipo de acidente:");
		lblSelecioneOTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelecioneOTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelecioneOTipo.setBounds(10, 317, 154, 14);
		contentPane.add(lblSelecioneOTipo);
		
		JLabel lblDataFinal = new JLabel("Data final:");
		lblDataFinal.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataFinal.setBounds(10, 261, 154, 14);
		contentPane.add(lblDataFinal);
		
		JLabel lblDataInicial = new JLabel("Data inicial:");
		lblDataInicial.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataInicial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInicial.setBounds(10, 196, 154, 14);
		contentPane.add(lblDataInicial);
		
		JComboBox comboBoxDiaSemana = new JComboBox();
		comboBoxDiaSemana.setToolTipText("Abalroamento\r\nAtropelamento\r\nCapotagem\r\nChoque\r\nColisão\r\nEventual\r\nIncêndio\r\nQueda\r\nTomboamento");
		comboBoxDiaSemana.setBounds(10, 131, 109, 20);
		contentPane.add(comboBoxDiaSemana);
		comboBoxDiaSemana.addItem("Domingo");
		comboBoxDiaSemana.addItem("Segunda-Feira");
		comboBoxDiaSemana.addItem("Terça-Feira");
		comboBoxDiaSemana.addItem("Quarta-Feira");
		comboBoxDiaSemana.addItem("Quinta-Feira");
		comboBoxDiaSemana.addItem("Sexta-Feira");
		comboBoxDiaSemana.addItem("Sábado");
		
		JSpinner spinnerDataInicial = new JSpinner();
		spinnerDataInicial.setModel(new SpinnerDateModel(new Date(1230775200000L), new Date(1230775200000L), new Date(1357005599000L), Calendar.DAY_OF_YEAR));
		spinnerDataInicial.setBounds(10, 221, 109, 20);
		contentPane.add(spinnerDataInicial);
		
		JSpinner spinnerDataFinal = new JSpinner();
		spinnerDataFinal.setModel(new SpinnerDateModel(new Date(1230775200000L), new Date(1230775200000L), new Date(1357005599000L), Calendar.DAY_OF_YEAR));
		spinnerDataFinal.setBounds(10, 286, 109, 20);
		contentPane.add(spinnerDataFinal);
		
		JComboBox comboBoxTipoAcidente = new JComboBox();
		comboBoxTipoAcidente.setModel(new DefaultComboBoxModel(new String[] {"Acidente"}));
		comboBoxTipoAcidente.setToolTipText("Acidente");
		comboBoxTipoAcidente.setBounds(10, 342, 109, 20);
		contentPane.add(comboBoxTipoAcidente);
		
		JLabel lblDiaDaSemana = new JLabel("Dia da semana:");
		lblDiaDaSemana.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaDaSemana.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiaDaSemana.setBounds(10, 106, 154, 14);
		contentPane.add(lblDiaDaSemana);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Logradouro"}));
		comboBox.setToolTipText("Logradouro");
		comboBox.setBounds(10, 75, 109, 20);
		contentPane.add(comboBox);
		
		JLabel lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogradouro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLogradouro.setBounds(10, 50, 154, 14);
		contentPane.add(lblLogradouro);
		
		JButton buttonBuscar_Logradouro_DiaDaSemana = new JButton("Buscar");
		buttonBuscar_Logradouro_DiaDaSemana.setAction(actionBuscar_Logradouro_DiaDaSemana);
		buttonBuscar_Logradouro_DiaDaSemana.setBounds(30, 162, 89, 23);
		contentPane.add(buttonBuscar_Logradouro_DiaDaSemana);
		
		JButton buttonBuscar_DataInicial_DataFinal_TipoAcidente = new JButton("Buscar");
		buttonBuscar_DataInicial_DataFinal_TipoAcidente.setAction(actionBuscar_DataInicial_DataFinal_TipoAcidente);
		buttonBuscar_DataInicial_DataFinal_TipoAcidente.setBounds(30, 373, 89, 23);
		contentPane.add(buttonBuscar_DataInicial_DataFinal_TipoAcidente);
	}
	
	private static void update_JPanel_panelMapa() {
		panelMapa.setVisible(false);
		contentPane.remove(panelMapa);
		panelMapa = Main.gui();
		contentPane.add(panelMapa);
	}
	
	private class ActionHora extends AbstractAction {
		public ActionHora() {
			putValue(NAME, "Hora");
			putValue(SHORT_DESCRIPTION, "Pesquisa o horário do dia que ocorrem mais acidentes");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnSample.setText(Main.opcao2());
			
			GUI.update_JPanel_panelMapa();
		}
	}
	private class ActionData extends AbstractAction {
		public ActionData() {
			putValue(NAME, "Data");
			putValue(SHORT_DESCRIPTION, "Pesquisa a data que ocorreu mais acidentes");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnSample.setText(Main.opcao5());
			
			GUI.update_JPanel_panelMapa();
		}
	}
	private class ActionDia extends AbstractAction {
		public ActionDia() {
			putValue(NAME, "Dia");
			putValue(SHORT_DESCRIPTION, "Pesquisa o dia da semana que ocorre mais acidentes");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnSample.setText(Main.opcao6());
			
			GUI.update_JPanel_panelMapa();
		}
	}
	private class ActionTurno extends AbstractAction {
		public ActionTurno() {
			putValue(NAME, "Turno");
			putValue(SHORT_DESCRIPTION, "Pesquisa se ocorrem mais acidentes de dia ou a noite");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnSample.setText(Main.opcao7());
			
			GUI.update_JPanel_panelMapa();
		}
	}
	private class ActionClima extends AbstractAction {
		public ActionClima() {
			putValue(NAME, "Clima");
			putValue(SHORT_DESCRIPTION, "Pesquisa qual situação climática ocorrem mais acidentes");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnSample.setText(Main.opcao8());
			
			GUI.update_JPanel_panelMapa();
		}
	}
	private class ActionBuscar_Logradouro_DiaDaSemana extends AbstractAction {
		public ActionBuscar_Logradouro_DiaDaSemana() {
			putValue(NAME, "Buscar");
			putValue(SHORT_DESCRIPTION, "Pesquisa acidentes em um determinado logradouro com restrição de dia da semana");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class ActionBuscar_Periodo_TipoAcid extends AbstractAction {
		public ActionBuscar_Periodo_TipoAcid() {
			putValue(NAME, "Buscar");
			putValue(SHORT_DESCRIPTION, "Pesquisa acidentes em um determinado período com restrição do tipo de acidente");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
