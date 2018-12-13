package br.com.pm.clinicasaracura;

import java.awt.EventQueue;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDayChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import br.com.pm.clinicasaracura.dao.ConvenioDAO;
import br.com.pm.clinicasaracura.dao.PacienteDAO;
import br.com.pm.clinicasaracura.dao.AgendaEquipamentoDAO;
import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;
import br.com.pm.clinicasaracura.dao.EquipamentoDAO;
import br.com.pm.clinicasaracura.dao.MedicoDAO;
import br.com.pm.clinicasaracura.entity.Convenio;
import br.com.pm.clinicasaracura.entity.Medico;
import br.com.pm.clinicasaracura.entity.Paciente;
import br.com.pm.clinicasaracura.entity.AgendaEquipamento;
import br.com.pm.clinicasaracura.entity.AgendaMedica;
import br.com.pm.clinicasaracura.entity.Equipamento;
import br.com.pm.clinicasaracura.entity.DiaAtendimento;

import com.toedter.calendar.JCalendar;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendamentoExameWindow {

	private JFrame frame;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;

	private PagamentoWindow pagamentoWindow = new PagamentoWindow<AgendaEquipamento>();
	private List<Medico> allDoctors;
	private Map<String, List<Medico>> doctorsPerTime = new HashMap<String, List<Medico>>();
	private List<Equipamento> allEquips;
	private DefaultComboBoxModel currentDoctors;
	private DefaultComboBoxModel workingEquips;
	private DefaultListModel horariosModel = new DefaultListModel();
	private Date chosenDate;
	private int tipoExame;
	private final int tempoExame = 20;
    private final ListSelectionListener timeChanged = new ListSelectionListener() {
        public void valueChanged(ListSelectionEvent event) {
        	if (event.getValueIsAdjusting())
        		return;
        	
            JList list = (JList)event.getSource();
            
            @SuppressWarnings("deprecation")
			Object selectionValues[] = list.getSelectedValues();
            
            currentDoctors.removeAllElements();
            
            if (selectionValues.length != 0) {
	            String timeStr = (String)selectionValues[0];
	            List<Medico> doctors = doctorsPerTime.get(timeStr);
	            for (Medico doctor : doctors) {
	            	currentDoctors.addElement(doctor);
	            }
            }
        }
    };
    
	/**
	 * Create the application.
	 */
	public AgendamentoExameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setResizable(false);
		this.frame.setBounds(100, 100, 535, 409);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		this.allDoctors = MedicoDAO.getInstance().findAll();

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
		dinheiroRadio.setSelected(true);

		buttonGroup_1.add(dinheiroRadio);
		dinheiroRadio.setFont(new Font("Dialog", Font.BOLD, 11));
		dinheiroRadio.setBounds(8, 116, 149, 23);
		particularPanel.add(dinheiroRadio);
		
		JPanel convenioPanel = new JPanel();
		convenioPanel.setBounds(247, 184, 273, 155);
		frame.getContentPane().add(convenioPanel);
		convenioPanel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Selecione o convenio");
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_4.setBounds(12, 12, 249, 15);
		convenioPanel.add(lblNewLabel_4);
		
		List<Convenio> convenios = ConvenioDAO.getInstance().findAll();

		JComboBox comboBoxConvenios = new JComboBox(convenios.toArray());
		comboBoxConvenios.setEditable(false);
		comboBoxConvenios.setEnabled(true);
		comboBoxConvenios.setBounds(12, 30, 249, 19);
		convenioPanel.add(comboBoxConvenios);
	
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 223, 155);
		
		JList horariosList = new JList();
		horariosList.addListSelectionListener(timeChanged);
	    horariosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		horariosList.setModel(horariosModel);
		scrollPane.setViewportView(horariosList);
		
		frame.getContentPane().add(scrollPane);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				horariosModel.removeAllElements();
				chosenDate = calendar.getDate();
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(chosenDate);
				int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
				
				for (int hora = 7; hora < 20; hora++) {
					for (int m = 0; m < 3; m++) {
						cal.set(Calendar.HOUR_OF_DAY, hora);
						cal.set(Calendar.MINUTE, m*20);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);
						Date finalDate = cal.getTime();

						List<Medico> availableDoctors = new ArrayList<Medico>();
						for (Medico doctor : allDoctors) {
							List<DiaAtendimento> dias = doctor.getDiaAtendimento();
							
							boolean atende = false;	
							for (DiaAtendimento dia : dias) {
								if (dia.getIdDia() == dayofweek) {
									atende = true;
									break;
								}
							}
							
							if (!atende) continue;
							
							int crm = doctor.getCrm();
							List<AgendaEquipamento> conflitosEquip = AgendaEquipamentoDAO.getInstance().getByDateAndCrm(finalDate, crm);
							List<AgendaMedica> conflitosConsulta = AgendaMedicaDAO.getInstance().isConflict(finalDate, doctor);

							if (conflitosEquip.size() == 0 && conflitosConsulta.size() == 0) {
								availableDoctors.add(doctor);
							}
						}

						if (availableDoctors.size() != 0) {
							String timeStr = String.format("%02d", hora) + ":" + String.format("%02d", tempoExame*m);
							horariosModel.addElement(timeStr);
							
							doctorsPerTime.put(timeStr, availableDoctors);
						}
					}
				}

				horariosList.setSelectedIndex(0);
			}
		});
		calendar.setBounds(12, 34, 223, 138);
		frame.getContentPane().add(calendar);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(80, 184, 20, 155);
		frame.getContentPane().add(scrollPane_2);
		
		JLabel lblNewLabel = new JLabel("Selecione uma data");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 12, 223, 25);
		frame.getContentPane().add(lblNewLabel);
		
		List<Paciente> pacientes = PacienteDAO.getInstance().findAll();
		
		JComboBox comboBoxPacientes = new JComboBox(pacientes.toArray());
		comboBoxPacientes.setEditable(false);
		comboBoxPacientes.setEnabled(true);
		comboBoxPacientes.setBounds(247, 34, 273, 19);
		
		frame.getContentPane().add(comboBoxPacientes);
		
		JLabel lblNewLabel_5 = new JLabel("Selecione o paciente");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_5.setBounds(247, 16, 187, 15);
		frame.getContentPane().add(lblNewLabel_5);

		JComboBox comboBoxMedicos = new JComboBox();
		this.currentDoctors = (DefaultComboBoxModel)comboBoxMedicos.getModel();
		comboBoxMedicos.setEditable(false);
		comboBoxMedicos.setEnabled(true);
		comboBoxMedicos.setBounds(247, 74, 273, 19);
		frame.getContentPane().add(comboBoxMedicos);
		
		JLabel lblNewLabel_7 = new JLabel("Selecione o médico");
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_7.setBounds(247, 56, 187, 15);
		frame.getContentPane().add(lblNewLabel_7);

		JComboBox comboBoxEquips = new JComboBox();
		this.workingEquips = (DefaultComboBoxModel)comboBoxEquips.getModel();	
		comboBoxEquips.setEditable(false);
		comboBoxEquips.setEnabled(true);
		comboBoxEquips.setBounds(247, 114, 273, 19);
	
		frame.getContentPane().add(comboBoxEquips);
		
		JLabel lblNewLabel_8 = new JLabel("Selecione o equipamento");
		lblNewLabel_8.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_8.setBounds(247, 96, 187, 15);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		JLabel lblNewLabel_3 = new JLabel("Forma de atendimento");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_3.setBounds(247, 147, 187, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JRadioButton convenioRadio = new JRadioButton("Convênio");
		convenioRadio.setSelected(true);
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
		voltarButton.setBounds(12, 354, 117, 25);
		voltarButton.addMouseListener(new MouseAdapter() {
		   @Override
		   public void mouseReleased(MouseEvent e) {
			try {
				setVisible(false);
				frame.dispose();
			} catch (Exception f) {
			 System.exit(0);
			}
		   }
		});

		frame.getContentPane().add(voltarButton);
		
		JButton agendarButton = new JButton("Agendar");
		agendarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if( horariosList.isSelectionEmpty()
			     || comboBoxPacientes.getSelectedItem().toString() == ""
			     || comboBoxMedicos.getSelectedItem().toString() == ""
			     || comboBoxEquips.getSelectedItem().toString() == ""
			     ) {
					JOptionPane.showMessageDialog(null, "Selecione todos os campos!");
					return;
				}
		
				AgendaEquipamento agenda = new AgendaEquipamento();
				
				int horaAgendamento   = Integer.parseInt(horariosList.getSelectedValue().toString().substring(0,2));
				int minutoAgendamento = Integer.parseInt(horariosList.getSelectedValue().toString().substring(3,5));
			
				Date diaAgendamento = calendar.getDate();
				diaAgendamento.setHours(horaAgendamento);
				diaAgendamento.setMinutes(minutoAgendamento);
				diaAgendamento.setSeconds(0);

				agenda.setDataAgendamento(diaAgendamento);
				agenda.setMedico(MedicoDAO.getInstance().getById(Integer.parseInt(comboBoxMedicos.getSelectedItem().toString().split(" ")[0])));			
				agenda.setPaciente(PacienteDAO.getInstance().getById(Integer.parseInt(comboBoxPacientes.getSelectedItem().toString().split(" ")[0])));
				agenda.setEquipamento(EquipamentoDAO.getInstance().getById(Integer.parseInt(comboBoxEquips.getSelectedItem().toString().split(" ")[0])));
				String convenio = "";
				String procedimento = "";
				String paciente = "";
				
				int mode = -1; 
				
				if (convenioRadio.isSelected()) {
					mode = 4;
					convenio = comboBoxConvenios.getSelectedItem().toString().split("-")[1].trim();
					procedimento = comboBoxEquips.getSelectedItem().toString();
					paciente = PacienteDAO.getInstance().getById(Integer.parseInt(comboBoxPacientes.getSelectedItem().toString().split(" ")[0])).getNome();
				}
				else if (chequeRadio.isSelected())
					mode = 0;
				else if (creditoRadio.isSelected())
					mode = 1;
				else if (debitoRadio.isSelected())
					mode = 2;
				else if (dinheiroRadio.isSelected())
					mode = 3;
			
				pagamentoWindow.setVisible(true, mode, agenda, convenio, procedimento, paciente);
				frame.dispose();
			}
		});
		agendarButton.setBounds(403, 354, 117, 25);
		frame.getContentPane().add(agendarButton);
	}

	private void fetchEquips() {
		this.allEquips = EquipamentoDAO.getInstance().findByExamId(this.tipoExame);
	}
	
	public void setVisible(boolean t) {
		frame.setVisible(t);
	}

	public void setTipoExame(int tipo) {
		this.tipoExame = tipo;
		this.fetchEquips();

		workingEquips.removeAllElements();
		for (Equipamento equip : this.allEquips) {
			if (equip.getStatusFuncionamento())
				workingEquips.addElement(equip);
		}
	}
}
