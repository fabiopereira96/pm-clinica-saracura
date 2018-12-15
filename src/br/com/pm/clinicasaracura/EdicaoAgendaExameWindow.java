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

@SuppressWarnings("serial")
public class EdicaoAgendaExameWindow extends JFrame {

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	
	private AgendaEquipamento agenda;
	
	public EdicaoAgendaExameWindow(AgendaEquipamento agenda) {
		this.agenda = agenda;
		initialize();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {		
		// TODO: Use this.agenda to fill initial values.
		
		setResizable(false);
		setBounds(100, 100, 535, 409);
		getContentPane().setLayout(null);
		
		JPanel particularPanel = new JPanel();
		particularPanel.setVisible(false);
		particularPanel.setBounds(247, 184, 273, 155);
		getContentPane().add(particularPanel);
		particularPanel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Forma de pagamento");
		lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_6.setBounds(12, 12, 249, 15);
		particularPanel.add(lblNewLabel_6);
		
		JRadioButton chequeRadio = new JRadioButton("Cheque");
		buttonGroup_1.add(chequeRadio);
		chequeRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		chequeRadio.setBounds(8, 35, 149, 23);
		particularPanel.add(chequeRadio);
		
		JRadioButton creditoRadio = new JRadioButton("Crédito");
		buttonGroup_1.add(creditoRadio);
		creditoRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		creditoRadio.setBounds(8, 62, 149, 23);
		particularPanel.add(creditoRadio);
		
		JRadioButton debitoRadio = new JRadioButton("Débito");
		buttonGroup_1.add(debitoRadio);
		debitoRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		debitoRadio.setBounds(8, 89, 149, 23);
		particularPanel.add(debitoRadio);
		
		JRadioButton dinheiroRadio = new JRadioButton("Dinheiro");
		buttonGroup_1.add(dinheiroRadio);
		dinheiroRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		dinheiroRadio.setBounds(8, 116, 149, 23);
		particularPanel.add(dinheiroRadio);
		
		JPanel convenioPanel = new JPanel();
		convenioPanel.setBounds(247, 184, 273, 155);
		getContentPane().add(convenioPanel);
		convenioPanel.setLayout(null);
		
//		JScrollPane scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(12, 30, 249, 55);
//		convenioPanel.add(scrollPane_1);
//		
//		JList conveniosList = new JList();
//		scrollPane_1.setColumnHeaderView(conveniosList);
		
		List<Convenio> convenios = ConvenioDAO.getInstance().findAll();
		
		JComboBox comboBoxConvenios = new JComboBox(convenios.toArray());
		comboBoxConvenios.setEditable(true);
		comboBoxConvenios.setBounds(12, 30, 249, 19);
		convenioPanel.add(comboBoxConvenios);

		JLabel lblNewLabel_4 = new JLabel("Selecione o convenio");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_4.setBounds(12, 12, 249, 15);
		convenioPanel.add(lblNewLabel_4);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().getDayPanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		calendar.setBounds(12, 34, 223, 138);
		getContentPane().add(calendar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 223, 155);
		getContentPane().add(scrollPane);
		
		JList horariosList = new JList();
		scrollPane.setViewportView(horariosList);
		
		JLabel lblNewLabel = new JLabel("Selecione uma data e um horário");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 12, 223, 25);
		getContentPane().add(lblNewLabel);
		
		List<Paciente> pacientes = PacienteDAO.getInstance().findAll();
		
		JComboBox comboBoxPacientes = new JComboBox(pacientes.toArray());
		comboBoxPacientes.setEditable(true);
		comboBoxPacientes.setBounds(247, 34, 273, 19);
		
		getContentPane().add(comboBoxPacientes);
		
		JLabel lblNewLabel_5 = new JLabel("Matricula do Paciente");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_5.setBounds(247, 16, 187, 15);
		getContentPane().add(lblNewLabel_5);
	
		JLabel lblNewLabel_3 = new JLabel("Forma de atendimento");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_3.setBounds(247, 137, 187, 15);
		getContentPane().add(lblNewLabel_3);
		
		JRadioButton convenioRadio = new JRadioButton("Convênio");
		convenioRadio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (convenioRadio.isSelected()) {
					convenioPanel.setVisible(true);
					particularPanel.setVisible(false);
				}
			}
		});
		convenioRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		buttonGroup.add(convenioRadio);
		convenioRadio.setBounds(243, 160, 90, 23);
		getContentPane().add(convenioRadio);
		
		JRadioButton particularRadio = new JRadioButton("Particular");
		particularRadio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(particularRadio.isSelected()) {
					particularPanel.setVisible(true);
					convenioPanel.setVisible(false);
				}
			}
		});
		particularRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		buttonGroup.add(particularRadio);
		particularRadio.setBounds(375, 160, 149, 23);
		getContentPane().add(particularRadio);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});
		voltarButton.setBounds(12, 354, 117, 25);
		getContentPane().add(voltarButton);
		
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
		getContentPane().add(agendarButton);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(215, 351, 98, 25);
		getContentPane().add(btnDeletar);
		
		JLabel lblMedicoResponsavel = new JLabel("Medico Responsavel");
		lblMedicoResponsavel.setBounds(247, 61, 130, 15);
		getContentPane().add(lblMedicoResponsavel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(247, 75, 273, 19);
		getContentPane().add(comboBox);
	}
}