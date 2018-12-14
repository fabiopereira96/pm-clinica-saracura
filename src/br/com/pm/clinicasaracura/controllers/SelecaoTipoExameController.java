package br.com.pm.clinicasaracura.controllers;

import java.util.List;

import br.com.pm.clinicasaracura.AgendamentoExameWindow;
import br.com.pm.clinicasaracura.dao.TipoExameDAO;

import br.com.pm.clinicasaracura.entity.TipoExame;

public class SelecaoTipoExameController implements SelectionController<TipoExame> {
	public String getTitle() {
		return "Agendamento de exame (selecione o tipo)";
	}
	
	public String getLabel() {
		return "Selecione o tipo do exame";
	}
	
	public String getBtnText() {
		return "Selecionar";
	}
	
	public List<TipoExame> getElements() {
		return TipoExameDAO.getInstance().findAll();
	}
	
	public void selectedElement(TipoExame e) {
		AgendamentoExameWindow agendamentoExameWindow = new AgendamentoExameWindow();

		agendamentoExameWindow.setTipoExame(e.getIdTipoExame());
		agendamentoExameWindow.setVisible(true);
	}
}
