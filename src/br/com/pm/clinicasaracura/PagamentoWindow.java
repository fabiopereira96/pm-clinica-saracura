package br.com.pm.clinicasaracura;

import java.awt.EventQueue;
import java.util.Random;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;
import br.com.pm.clinicasaracura.entity.AgendaMedica;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class PagamentoWindow {

	private JFrame frame;
	private JTextField credito_nCartaoField;
	private JTextField credito_cvcTextField;
	private JTextField credito_nomeCartaoTextField;
	private JTextField credito_valorField;
	private JTextField cheque_numeroContaTxtField;
	private JTextField cheque_valorCobradoTextField;
	private JTextField debito_numeroCartaoTxtField;
	private JTextField debito_valorTxtField;
	private JTextField cheque_numeroChequeTextField;

	private JPanel creditoPanel = new JPanel();
	private JPanel debitoPanel  = new JPanel();
	private JPanel chequePanel = new JPanel();
	private JLabel lblProcessando = new JLabel("Processando...");
	
	private JComboBox mesValidadeComboBox = new JComboBox();
	private JComboBox anoValidadeComboBox = new JComboBox();
	
	private boolean pagamentoAutorizado;
	private AgendaMedica agenda;
	private JPasswordField debito_senhaField;
	
	private int mode;

	/**
	 * Create the application.
	 */
	public PagamentoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setType(Type.NORMAL);
		frame.setResizable(false);
		frame.getContentPane().setSize(new Dimension(500, 500));
		frame.getContentPane().setLayout(null);
		chequePanel.setVisible(false);
		debitoPanel.setVisible(false);
		creditoPanel.setVisible(false);
		creditoPanel.setBounds(0, 0, 440, 223);
		frame.getContentPane().add(creditoPanel);
		creditoPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Número do cartão");
		lblNewLabel.setBounds(12, 12, 132, 15);
		creditoPanel.add(lblNewLabel);
		
		credito_nCartaoField = new JTextField();
		credito_nCartaoField.setBounds(12, 34, 217, 19);
		creditoPanel.add(credito_nCartaoField);
		credito_nCartaoField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Validade");
		lblNewLabel_1.setBounds(12, 65, 70, 15);
		creditoPanel.add(lblNewLabel_1);
		
		//JComboBox mesValidadeComboBox = new JComboBox();
		mesValidadeComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		mesValidadeComboBox.setBounds(12, 85, 66, 15);
		creditoPanel.add(mesValidadeComboBox);
		
		//JComboBox anoValidadeComboBox = new JComboBox();
		anoValidadeComboBox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		anoValidadeComboBox.setBounds(102, 85, 66, 15);
		creditoPanel.add(anoValidadeComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("/");
		lblNewLabel_2.setBounds(90, 85, 14, 15);
		creditoPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CVC");
		lblNewLabel_3.setBounds(12, 112, 32, 15);
		creditoPanel.add(lblNewLabel_3);
		
		credito_cvcTextField = new JTextField();
		credito_cvcTextField.setBounds(12, 131, 70, 19);
		creditoPanel.add(credito_cvcTextField);
		credito_cvcTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Nome impresso no cartão");
		lblNewLabel_4.setBounds(12, 162, 217, 15);
		creditoPanel.add(lblNewLabel_4);
		
		credito_nomeCartaoTextField = new JTextField();
		credito_nomeCartaoTextField.setBounds(12, 189, 217, 19);
		creditoPanel.add(credito_nomeCartaoTextField);
		credito_nomeCartaoTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Valor cobrado");
		lblNewLabel_5.setBounds(301, 12, 127, 15);
		creditoPanel.add(lblNewLabel_5);
		
		credito_valorField = new JTextField();
		credito_valorField.setBounds(301, 34, 127, 19);
		creditoPanel.add(credito_valorField);
		credito_valorField.setColumns(10);
		
		//JPanel chequePanel = new JPanel();
		debitoPanel.setLayout(null);
		debitoPanel.setBounds(0, 0, 440, 223);
		frame.getContentPane().add(debitoPanel);
		
		JLabel label_1 = new JLabel("Número do cartão");
		label_1.setBounds(12, 12, 132, 15);
		debitoPanel.add(label_1);
		
		debito_numeroCartaoTxtField = new JTextField();
		debito_numeroCartaoTxtField.setColumns(10);
		debito_numeroCartaoTxtField.setBounds(12, 34, 217, 19);
		debitoPanel.add(debito_numeroCartaoTxtField);
		
		JLabel lblSenha_1 = new JLabel("Senha");
		lblSenha_1.setBounds(12, 65, 217, 15);
		debitoPanel.add(lblSenha_1);
		
		JLabel label_8 = new JLabel("Valor cobrado");
		label_8.setBounds(301, 12, 127, 15);
		debitoPanel.add(label_8);
		
		debito_valorTxtField = new JTextField();
		debito_valorTxtField.setColumns(10);
		debito_valorTxtField.setBounds(301, 34, 127, 19);
		debitoPanel.add(debito_valorTxtField);
		
		debito_senhaField = new JPasswordField();
		debito_senhaField.setBounds(12, 92, 217, 19);
		debitoPanel.add(debito_senhaField);
		
		//JPanel debitoPanel = new JPanel();
		chequePanel.setLayout(null);
		chequePanel.setBounds(0, 0, 440, 223);
		frame.getContentPane().add(chequePanel);
		
		JLabel lblNmeroDaConta = new JLabel("Número da conta");
		lblNmeroDaConta.setBounds(12, 12, 132, 15);
		chequePanel.add(lblNmeroDaConta);
		
		cheque_numeroContaTxtField = new JTextField();
		cheque_numeroContaTxtField.setColumns(10);
		cheque_numeroContaTxtField.setBounds(12, 34, 217, 19);
		chequePanel.add(cheque_numeroContaTxtField);
		
		JLabel lblSenha = new JLabel("Número do cheque");
		lblSenha.setBounds(14, 62, 217, 15);
		chequePanel.add(lblSenha);
		
		JLabel label_5 = new JLabel("Valor cobrado");
		label_5.setBounds(12, 120, 127, 15);
		chequePanel.add(label_5);
		
		cheque_valorCobradoTextField = new JTextField();
		cheque_valorCobradoTextField.setColumns(10);
		cheque_valorCobradoTextField.setBounds(12, 147, 127, 19);
		chequePanel.add(cheque_valorCobradoTextField);
		
		cheque_numeroChequeTextField = new JTextField();
		cheque_numeroChequeTextField.setBounds(12, 89, 217, 19);
		chequePanel.add(cheque_numeroChequeTextField);
		cheque_numeroChequeTextField.setColumns(10);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				switch (mode) {
				case (0) :
					if (  cheque_numeroContaTxtField.getText().equals("")
					   || cheque_numeroChequeTextField.getText().equals("")
					   || cheque_valorCobradoTextField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Complete todos os campos!");
						return;
					}
					break;
				case (1) :
					if (  credito_nCartaoField.getText().equals("")
					   || credito_cvcTextField.getText().equals("")
					   || credito_nomeCartaoTextField.getText().equals("")
					   || credito_valorField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Complete todos os campos!");
						return;
					}
					break;
				case (2) :
					if (  debito_numeroCartaoTxtField.getText().equals("")
					   || debito_valorTxtField.getText().equals("")
					   || debito_senhaField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Complete todos os campos!");
						return;
					}
					break;
				}
				
				lblProcessando.setVisible(true);
				
			    ActionListener listener = new ActionListener(){
			        public void actionPerformed(ActionEvent event){
						processPagamentoInstituicao();
						resetWindow();
						frame.dispose();
			        }
			    };
			    Timer timer = new Timer(3000, listener);
			    timer.setRepeats(false);
			    timer.start();
			}
		});
		btnNewButton.setBounds(323, 245, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(10, 245, 98, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		lblProcessando.setVisible(false);
		lblProcessando.setForeground(Color.RED);
		lblProcessando.setBounds(12, 224, 181, 15);
		frame.getContentPane().add(lblProcessando);
		frame.setSize(new Dimension(444, 289));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void processPagamentoInstituicao () {
		Random r = new Random();
		int dice = r.nextInt(10);
				
		if (dice == 9) {
			JOptionPane.showMessageDialog(null, "Pagamento não autorizado pela instituição!");
		} else {
			AgendaMedicaDAO.getInstance().persist(agenda);
			JOptionPane.showMessageDialog(null, "Agendado!");
		}
	}
	
	public void processPagamentoConvenio (String convenio, String procedimento, String paciente) {
		Random r = new Random();
		int dice = r.nextInt(12);
				
		if (dice == 11) {
			JOptionPane.showMessageDialog(null, "O convenio " + convenio 
					+ " não autorizou o procedimento " + procedimento
					+ " para o paciente " + paciente 
					+ ".");
		} else {
			AgendaMedicaDAO.getInstance().persist(agenda);
			JOptionPane.showMessageDialog(null, "Agendado!");
		}
	}
	
	public void processPagamentoDinheiro () {
		//emitir recibo
	}
	
	public void setVisible( boolean b, int mode, AgendaMedica agenda
			              , String convenio, String procedimento
			              , String paciente) {
		this.agenda = agenda;		
		this.mode = mode;
		
		switch (mode) {
		case (0) :
			chequePanel.setVisible(true);
			break;
		case (1) :
			creditoPanel.setVisible(true);				
			break;
		case (2) :
			debitoPanel.setVisible(true);			
			break;
		case (3) :
			processPagamentoDinheiro();
			return;
		case (4) :
			processPagamentoConvenio(convenio, procedimento, paciente);
			return;
		}
		
		frame.setVisible(b);
	}
	
	public void resetWindow() {
		lblProcessando.setVisible(false);
		cheque_numeroContaTxtField.setText("");
		cheque_numeroChequeTextField.setText("");
		cheque_valorCobradoTextField.setText("");
		credito_nCartaoField.setText("");
		credito_cvcTextField.setText("");
		credito_nomeCartaoTextField.setText("");
		credito_valorField.setText("");
		debito_numeroCartaoTxtField.setText("");
		debito_valorTxtField.setText("");
		debito_senhaField.setText("");
		mesValidadeComboBox.setSelectedIndex(0);
		anoValidadeComboBox.setSelectedIndex(0);
	}
	
	public void emitirRecibo() {
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.FILES_ONLY);
		Integer opt = j.showOpenDialog(null);
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(j.getSelectedFile());
			writer.println("Das ist ein Test");
			writer.println("Das ist ein anderer Test");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
