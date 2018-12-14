package br.com.pm.clinicasaracura;

import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.pm.clinicasaracura.controllers.SelectionController;

import javax.swing.JList;
import javax.swing.DefaultListModel;

@SuppressWarnings({ "serial", "rawtypes" })
public class SelecaoWindow<TItem, TController extends SelectionController> extends JFrame {
	
	private TController ctl;
	private List<TItem> listData;
	private DefaultListModel listModel;

	public SelecaoWindow(TController ctl) {
		this.ctl = ctl;
		this.listModel = new DefaultListModel();
		
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		setTitle(ctl.getTitle());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		listData = ctl.getElements();
		
		JList myJList = new JList();
		for (TItem agenda : listData) {
		    listModel.addElement(agenda);
		}
		myJList.setModel(listModel);
		myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/* Botões */
		JLabel lblAgendaMedica = new JLabel(ctl.getLabel());
		lblAgendaMedica.setBounds(12, 12, 130, 25);
		getContentPane().add(lblAgendaMedica);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(12, 237, 98, 25);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				dispose();
			}
		});

		getContentPane().add(btnVoltar);
		
		JButton btnSelect = new JButton(ctl.getBtnText());
		btnSelect.setBounds(340, 237, 98, 25);
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					int idx = myJList.getSelectedIndex();
					ctl.selectedElement(listData.get(idx));
				} catch (Exception f) {
					System.exit(0);
				}
			}
		});
		myJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnSelect.setEnabled(true);
			}
		});
		getContentPane().add(btnSelect);
		
		/* Scrollpane de seleção */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 37, 416, 175);
		getContentPane().add(scrollPane);
		
		if (listData.size() > 0)
			myJList.setSelectedIndex(0);
		else
			btnSelect.setEnabled(false);
	
		scrollPane.setViewportView(myJList);
	}
}
