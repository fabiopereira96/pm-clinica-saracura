package br.com.pm.clinicasaracura;

import java.util.List;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JCalendar;

import br.com.pm.clinicasaracura.dao.*;
import br.com.pm.clinicasaracura.entity.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class EdicaoAgendaExameWindow {

	private JFrame frame;

	private AgendaEquipamento agenda;
	
	public EdicaoAgendaExameWindow(AgendaEquipamento agenda) {
		this.agenda = agenda;
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {		
		// TODO: Use this.agenda to fill initial values.
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 535, 409);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Atualização de agenda (exame)");
//		JScrollPane scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(12, 30, 249, 55);
//		convenioPanel.add(scrollPane_1);
//		
//		JList conveniosList = new JList();
//		scrollPane_1.setColumnHeaderView(conveniosList);
		
		List<Convenio> convenios = ConvenioDAO.getInstance().findAll();
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(12, 34, 223, 138);
		frame.getContentPane().add(calendar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 223, 155);
		frame.getContentPane().add(scrollPane);
		
		JList horariosList = new JList();
		scrollPane.setViewportView(horariosList);
		
		JLabel lblNewLabel = new JLabel("Selecione uma data e um horário");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 12, 223, 25);
		frame.getContentPane().add(lblNewLabel);
		
		List<Paciente> pacientes = PacienteDAO.getInstance().findAll();
		
		JComboBox comboBoxPacientes = new JComboBox(pacientes.toArray());
		comboBoxPacientes.setEditable(true);
		comboBoxPacientes.setBounds(247, 34, 273, 19);
		
		frame.getContentPane().add(comboBoxPacientes);
		
		JLabel lblNewLabel_5 = new JLabel("Matricula do Paciente");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_5.setBounds(247, 16, 187, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
			}
		});
		voltarButton.setBounds(12, 354, 117, 25);
		frame.getContentPane().add(voltarButton);
		
		JButton agendarButton = new JButton("Atualizar");
		agendarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgendaEquipamentoDAO.getInstance().remove(agenda);
				
				AgendaEquipamento novaAgenda = new AgendaEquipamento();
				
				// TODO: fill novaAgenda with new data.
				
				AgendaEquipamentoDAO.getInstance().persist(novaAgenda);
			}
		});
		agendarButton.setBounds(403, 354, 117, 25);
		frame.getContentPane().add(agendarButton);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(215, 351, 98, 25);
		frame.getContentPane().add(btnDeletar);
		
		JLabel lblMedicoResponsavel = new JLabel("Medico Responsavel");
		lblMedicoResponsavel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblMedicoResponsavel.setBounds(247, 61, 273, 15);
		frame.getContentPane().add(lblMedicoResponsavel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(247, 75, 273, 19);
		frame.getContentPane().add(comboBox);
	}

	public void showFrame() {
		frame.setVisible(true);
	}
}