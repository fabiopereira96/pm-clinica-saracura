package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;
import java.util.*;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class EspecialidadesWindow {

	private JFrame frame;
	
	/* My vars IM SOOO CONFUSED MANN*/
	private int mode; // 0 - Especialidades, 1 - Medicos
	
	/**
	 * Create the application.
	 */
	public EspecialidadesWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		mode = 0;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.setBounds(12, 234, 117, 25);
		frame.getContentPane().add(voltarButton);
		
		JLabel selecioneLabel = new JLabel("Selecione uma especialidade.");
		selecioneLabel.setBounds(12, 36, 221, 15);
		frame.getContentPane().add(selecioneLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 63, 416, 159);
		frame.getContentPane().add(scrollPane_1);
		
		// Lista de especialidades
		DefaultListModel especialidadesListModel = new DefaultListModel();
		List<Especialidade> rows = EspecialidadeDAO.getInstance().findAll();
		
		for (Especialidade row : rows) {
		    especialidadesListModel.addElement(row.getNome());
		}
		JList especialidadesList = new JList(especialidadesListModel);
		especialidadesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(especialidadesList);
		especialidadesList.setDragEnabled(true);
		
		JButton selecionarButton = new JButton("Selecionar");
		selecionarButton.setEnabled(false);
		selecionarButton.addMouseListener(new MouseAdapter() {
			@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!selecionarButton.isEnabled()) {
					return;
				}
				if (mode == 0) {
					mode = 1;
					selecionarButton.setEnabled(false);
					selecioneLabel.setText("Selecione um médico.");
					
					DefaultListModel medicoListModel = new DefaultListModel();
					String[] rows = MedicoDAO.getInstance().getNamesByEspecialidade(especialidadesList.getSelectedIndex()+1);

					for (String row : rows) {
						medicoListModel.addElement(row);
					}
					
					especialidadesList.setModel(medicoListModel);
				} else if (mode == 1) {
					// Abre a agenda
				}
			}
				
		});
		selecionarButton.setBounds(317, 234, 117, 25);
		frame.getContentPane().add(selecionarButton);
		
		especialidadesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selecionarButton.setEnabled(true);
			}
		});
	}
	
	public void setVisible(boolean t) {
		frame.setVisible(t);
	}
}
