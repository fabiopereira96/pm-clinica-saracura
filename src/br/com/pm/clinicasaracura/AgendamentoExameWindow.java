package br.com.pm.clinicasaracura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import com.toedter.calendar.JDayChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import br.com.pm.clinicasaracura.dao.ConvenioDAO;
import br.com.pm.clinicasaracura.dao.PacienteDAO;
import br.com.pm.clinicasaracura.dao.EquipamentoDAO;
import br.com.pm.clinicasaracura.entity.Convenio;
import br.com.pm.clinicasaracura.entity.Paciente;
import br.com.pm.clinicasaracura.entity.Equipamento;

import com.toedter.calendar.JCalendar;
import javax.swing.JList;
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
	private JTextField nomePacienteTxtField;
	private JTextField telefoneTxtField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private int tipoExame;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgendamentoExameWindow window = new AgendamentoExameWindow();
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
	public AgendamentoExameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 535, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		calendar.getDayChooser().addPropertyChangeListener(new PropertyChangeListener() {
		    public void propertyChange(PropertyChangeEvent evt) {
				// Mostrar as agendas de todos os aparelhos disponíveis para o tipo de exame.
		    }
		});
		calendar.setBounds(12, 34, 223, 138);
		frame.getContentPane().add(calendar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 184, 223, 155);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Selecione uma data");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel.setBounds(12, 12, 223, 25);
		frame.getContentPane().add(lblNewLabel);
		
		List<Paciente> pacientes = PacienteDAO.getInstance().findAll();
		
		JComboBox comboBoxPacientes = new JComboBox(pacientes.toArray());
		comboBoxPacientes.setEditable(true);
		comboBoxPacientes.setBounds(247, 34, 273, 19);
		
		frame.getContentPane().add(comboBoxPacientes);
		
		JLabel lblNewLabel_3 = new JLabel("Forma de atendimento");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 11));
		lblNewLabel_3.setBounds(247, 137, 187, 15);
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
		agendarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		agendarButton.setBounds(403, 354, 117, 25);
		frame.getContentPane().add(agendarButton);
	}
	
	public void setVisible(boolean t) {
		frame.setVisible(t);
	}
	
	public void setTipoExame(int tipo) {
		this.tipoExame = tipo;
		
		List<Equipamento> equips = EquipamentoDAO.getInstance().findByExamId(this.tipoExame);
		DefaultListModel horariosModel = new DefaultListModel();
	
		String[] minsPossiveis = new String[] {"00", "20", "40"};
		for (int hora = 7; hora < 20; hora++) {
			for (int m = 0; m < 3; m++) {
				for (Equipamento equip : equips) {
					if (equip.getStatusFuncionamento()) {
						horariosModel.addElement(hora + ":" + minsPossiveis[m] + " - " + equip);
					}
				}
			}
		}
		
		JList horariosList = new JList();
		horariosList.setModel(horariosModel);
		
		scrollPane.setViewportView(horariosList);
	}
}
