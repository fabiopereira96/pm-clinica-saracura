package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import com.toedter.calendar.JDayChooser;

import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;
import br.com.pm.clinicasaracura.dao.ConvenioDAO;
import br.com.pm.clinicasaracura.dao.MedicoDAO;
import br.com.pm.clinicasaracura.dao.PacienteDAO;
import br.com.pm.clinicasaracura.entity.AgendaMedica;
import br.com.pm.clinicasaracura.entity.Convenio;
import br.com.pm.clinicasaracura.entity.DiaAtendimento;
import br.com.pm.clinicasaracura.entity.Medico;
import br.com.pm.clinicasaracura.entity.Paciente;

import com.toedter.calendar.JCalendar;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class AgendamentoWindow {

	private JFrame frame;
	private JTextField nomePacienteTxtField;
	private JTextField telefoneTxtField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	
	private int crmMedico;
	
	// The standard methods DON'T WORK
	private boolean datesAreEqual(Date a, Date b) {
		if (  a.getDate()     == b.getDate() 
	       && a.getMonth()   == b.getMonth()
	       && a.getYear()    == b.getYear()
	       && a.getSeconds() == b.getSeconds()
	       && a.getMinutes() == b.getMinutes()
	       && a.getHours()   == b.getHours()) {
			return true;
		}
		
		return false;
	}
	
	private int getDayId(String date) {
		String day = date.substring(0, 3);
		int dayid = 0;
		
		switch (day) {
			case "Sun" :
				dayid = 1;
				break;
			case "Mon" :
				dayid = 2;
				break;
			case "Tue" :
				dayid = 3;
				break;
			case "Wed" :
				dayid = 4;
				break;
			case "Thu" :
				dayid = 5;
				break;
			case "Fri" :
				dayid = 6;
				break;
			case "Sat" :
				dayid = 7;
				break;
		}
		
		return dayid;
	}
	
	private boolean thisHorarioIsFree (List<AgendaMedica> agenda, Date horario) {
		for (AgendaMedica a : agenda) {
			if(datesAreEqual(horario, a.getDiaAgendamento()))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Create the application.
	 */
	public AgendamentoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		crmMedico = -1;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 535, 409);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel particularPanel = new JPanel();
		particularPanel.setVisible(false);
		particularPanel.setBounds(247, 184, 273, 155);
		frame.getContentPane().add(particularPanel);
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
		frame.getContentPane().add(convenioPanel);
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
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 208, 223, 131);
		frame.getContentPane().add(scrollPane);
		
		JList horariosList = new JList();
		scrollPane.setViewportView(horariosList);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				boolean printHorarios = false;
				int selectedDiaId = getDayId(calendar.getDate().toString());
				Medico selectedMedico = MedicoDAO.getInstance().getById(crmMedico);
				List<DiaAtendimento> diasAtendimentoList = selectedMedico.getDiaAtendimento();
				int intervaloAtendimento = Integer.parseInt(selectedMedico.getIntervaloAtendimento().substring(3, 5));
				int horarioAtendimentoHora   = Integer.parseInt(selectedMedico.getHorarioAtendimento().substring(0, 2));
				int horarioAtendimentoMinuto = Integer.parseInt(selectedMedico.getHorarioAtendimento().substring(3, 5));

				List<AgendaMedica> selectedMedicoAgenda = AgendaMedicaDAO.getInstance().getByMedico(crmMedico);
				
				for (DiaAtendimento d : diasAtendimentoList) {
					if (d.getIdDia() == selectedDiaId) {
						printHorarios = true;
						break;
					}
				}
				
				if (printHorarios) {
					Date compareDate = calendar.getDate();
									
					int currentHora = horarioAtendimentoHora;
					int currentMin  = horarioAtendimentoMinuto;
					
					DefaultListModel listModel = new DefaultListModel();
														
					for (int i = 0; (i*intervaloAtendimento) <= 360; i++) {
						currentHora += (currentMin + intervaloAtendimento) / 60;
						currentMin = (horarioAtendimentoMinuto + i*intervaloAtendimento) % 60;
						
						compareDate.setHours(currentHora);
						compareDate.setMinutes(currentMin);
						compareDate.setSeconds(0);
						
						if (thisHorarioIsFree(selectedMedicoAgenda, compareDate))
							listModel.addElement(String.format("%02d", currentHora) + ":" + String.format("%02d", currentMin));
					}
					
					horariosList.setModel(listModel);
				}
				else {
					DefaultListModel listModel = new DefaultListModel();
					horariosList.setModel(listModel);
				}
			}
		});
		calendar.setBounds(12, 34, 223, 138);
		frame.getContentPane().add(calendar);
		
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
		
//		nomePacienteTxtField = new JTextField();
//		nomePacienteTxtField.setBounds(247, 85, 273, 19);
//		frame.getContentPane().add(nomePacienteTxtField);
//		nomePacienteTxtField.setColumns(10);
//		
//		JLabel lblNewLabel_1 = new JLabel("Nome do paciente");
//		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 11));
//		lblNewLabel_1.setBounds(247, 67, 187, 15);
//		frame.getContentPane().add(lblNewLabel_1);
		
//		JLabel lblNewLabel_2 = new JLabel("Telefone do paciente");
//		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 11));
//		lblNewLabel_2.setBounds(247, 67, 187, 15);
//		frame.getContentPane().add(lblNewLabel_2);
//		
//		telefoneTxtField = new JTextField();
//		telefoneTxtField.setBounds(247, 85, 273, 19);
//		frame.getContentPane().add(telefoneTxtField);
//		telefoneTxtField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Forma de atendimento");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_3.setBounds(247, 137, 187, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
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
		frame.getContentPane().add(convenioRadio);
		
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
		frame.getContentPane().add(particularRadio);
		
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		voltarButton.setBounds(12, 354, 117, 25);
		frame.getContentPane().add(voltarButton);
		
		JButton agendarButton = new JButton("Agendar");
		agendarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( horariosList.isSelectionEmpty()
			     || comboBoxPacientes.getSelectedItem().toString() == ""
			     || (!convenioRadio.isSelected() && !particularRadio.isSelected())
			     ) {
					JOptionPane.showMessageDialog(null, "Selecione todos os campos!");
					return;
				}
				
				AgendaMedica agenda = new AgendaMedica();
				
				int horaAgendamento   = Integer.parseInt(horariosList.getSelectedValue().toString().substring(0,2));
				int minutoAgendamento = Integer.parseInt(horariosList.getSelectedValue().toString().substring(3,5));
				
				Date diaAgendamento = calendar.getDate();
				diaAgendamento.setHours(horaAgendamento);
				diaAgendamento.setMinutes(minutoAgendamento);
				diaAgendamento.setSeconds(0);
				
				agenda.setDiaAgendamento(diaAgendamento);
				agenda.setMedico(MedicoDAO.getInstance().getById(crmMedico));
				
				agenda.setPaciente(PacienteDAO.getInstance().getById(Integer.parseInt(comboBoxPacientes.getSelectedItem().toString().split(" ")[0])));
				
				AgendaMedicaDAO.getInstance().persist(agenda);
				JOptionPane.showMessageDialog(null, "Agendado!");
				frame.dispose();
			}
		});
		agendarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		agendarButton.setBounds(403, 354, 117, 25);
		frame.getContentPane().add(agendarButton);
		
		JLabel lblNewLabel_1 = new JLabel("Horários disponíveis");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_1.setBounds(12, 181, 217, 15);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	public void setVisible(boolean t, final int crmMedico) {
		this.crmMedico     = crmMedico; 
		frame.setVisible(t);
	}
}
