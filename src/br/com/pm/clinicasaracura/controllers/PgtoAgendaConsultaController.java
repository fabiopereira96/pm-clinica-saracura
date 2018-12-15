package br.com.pm.clinicasaracura.controllers;

import br.com.pm.clinicasaracura.entity.*;
import br.com.pm.clinicasaracura.dao.*;

public class PgtoAgendaConsultaController implements PaymentController<AgendaMedica> {
	public String getTitle() {
		return "Pagamento (agendamento de consulta)";
	}
	
	public void paymentFinished(boolean status, AgendaMedica item) {
		if (status) {
			AgendaMedicaDAO.getInstance().persist(item);
		}
	}
}