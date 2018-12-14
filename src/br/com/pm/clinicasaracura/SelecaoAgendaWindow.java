package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import br.com.pm.clinicasaracura.dao.AgendaEquipamentoDAO;
import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;
import br.com.pm.clinicasaracura.dao.PacienteDAO;
import br.com.pm.clinicasaracura.entity.AgendaEquipamento;
import br.com.pm.clinicasaracura.entity.AgendaMedica;
import br.com.pm.clinicasaracura.entity.Paciente;

import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JRadioButton;

public class SelecaoAgendaWindow {

	private JFrame frame;
	private static AtualizacaoWindow atualizacaoWindow = new AtualizacaoWindow();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelecaoAgendaWindow window = new SelecaoAgendaWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelecaoAgendaWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		int agendaid = -1;
		
		List<AgendaMedica> agendas = AgendaMedicaDAO.getInstance().findAll();
		List<AgendaEquipamento> agendaeq = AgendaEquipamentoDAO.getInstance().findAll();
		
		/* Botões */
		JLabel lblSelecioneUmaAgenda = new JLabel("Selecione uma agenda");
		lblSelecioneUmaAgenda.setBounds(12, 12, 160, 25);
		frame.getContentPane().add(lblSelecioneUmaAgenda);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(330, 237, 98, 25);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					atualizacaoWindow.setVisible(true, agendaid);
				} catch (Exception f) {
					System.exit(0);
				}
			}
		});
		frame.getContentPane().add(btnEditar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(12, 237, 98, 25);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnVoltar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 416, 155);
		frame.getContentPane().add(scrollPane);
		
		@SuppressWarnings("unchecked")
		JList agendaList = new JList(agendas.toArray());
		JList agendaeqList = new JList(agendaeq.toArray());

		
		String[] options = {"Consulta", "Equipamento"};
		@SuppressWarnings("unchecked")
		JComboBox comboBox = new JComboBox(options);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String)comboBox.getSelectedItem() == "Consulta") {
					scrollPane.setViewportView(agendaList);
				}
				else {
					scrollPane.setViewportView(agendaeqList);
				}
			}
		});
		comboBox.setBounds(22, 38, 406, 24);
		frame.getContentPane().add(comboBox);


	}
	public void setVisible(boolean t) { 
		frame.setVisible(t);
	}
}
