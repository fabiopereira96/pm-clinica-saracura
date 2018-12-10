package br.com.pm.clinicasaracura;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;
import java.util.*;
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
	private static AgendamentoWindow agendamentoWindow = new AgendamentoWindow();
	
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
		voltarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
			}
		});
		voltarButton.setBounds(12, 234, 117, 25);
		voltarButton.addMouseListener(new MouseAdapter() {
		   @Override
		   public void mouseReleased(MouseEvent e) {
		   try {
			   if(mode == 1) {
				   frame.dispose();
			       initialize();
			       setVisible(true);
			   } else {
				   setVisible(false);
				   frame.dispose();
			   }
		   } catch (Exception f) {
			     System.exit(0);
		   }}});
		frame.getContentPane().add(voltarButton);
		
		JLabel selecioneLabel = new JLabel("Selecione uma especialidade.");
		selecioneLabel.setBounds(12, 36, 221, 15);
		frame.getContentPane().add(selecioneLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 63, 416, 159);
		frame.getContentPane().add(scrollPane_1);
		
		List<Especialidade> rows = EspecialidadeDAO.getInstance().findAll();
		
		JList especialidadesList = new JList(rows.toArray());
		especialidadesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(especialidadesList);
		especialidadesList.setDragEnabled(true);
		
		JButton selecionarButton = new JButton("Selecionar");
		selecionarButton.setEnabled(false);
		selecionarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!selecionarButton.isEnabled()) {
					return;
				}
				if (mode == 0) {
					mode = 1;
					selecionarButton.setEnabled(false);
					selecioneLabel.setText("Selecione um médico.");
					
					Especialidade especialidadeSelected = (Especialidade) especialidadesList.getSelectedValue();
					
					DefaultListModel medicoListModel = new DefaultListModel();
					List<Medico> rows = MedicoDAO.getInstance().getNamesByEspecialidade(especialidadeSelected.getIdEspecialidade());

					for (Medico row : rows) {
						medicoListModel.addElement(row);
					}
					
					especialidadesList.setModel(medicoListModel);
				} else if (mode == 1) {
					Medico medico = (Medico) especialidadesList.getSelectedValue();
					agendamentoWindow.setVisible(true, medico.getCrm());
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
