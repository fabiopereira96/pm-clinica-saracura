package br.com.pm.clinicasaracura.controllers;

import java.util.List;

import br.com.pm.clinicasaracura.EdicaoAgendaConsultaWindow;
import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;

import br.com.pm.clinicasaracura.entity.AgendaMedica;

public class SelecaoAgendaConsultaController implements SelectionController<AgendaMedica> {
	public String getTitle() {
		return "Atualização de agenda (selecione a consulta)";
	}
	
	public String getLabel() {
		return "Selecione a consulta";
	}
	
	public String getBtnText() {
		return "Editar";
	}
	
	public List<AgendaMedica> getElements() {
		return AgendaMedicaDAO.getInstance().findAll();
	}

	public void selectedElement(AgendaMedica agenda) {
		EdicaoAgendaConsultaWindow edicaoAgendaConsultaWindow = new EdicaoAgendaConsultaWindow(agenda);
		edicaoAgendaConsultaWindow.setVisible(true);
	}
}
