package br.com.pm.clinicasaracura;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.com.pm.clinicasaracura.dao.AgendaMedicaDAO;
import br.com.pm.clinicasaracura.dao.ConvenioDAO;
import br.com.pm.clinicasaracura.dao.DiaAtendimentoDAO;
import br.com.pm.clinicasaracura.dao.EspecialidadeDAO;
import br.com.pm.clinicasaracura.dao.MedicoDAO;
import br.com.pm.clinicasaracura.dao.PacienteDAO;
import br.com.pm.clinicasaracura.entity.AgendaMedica;
import br.com.pm.clinicasaracura.entity.Convenio;
import br.com.pm.clinicasaracura.entity.DiaAtendimento;
import br.com.pm.clinicasaracura.entity.Especialidade;
import br.com.pm.clinicasaracura.entity.Medico;
import br.com.pm.clinicasaracura.entity.Paciente;

public class ClinicaSaracura {

	public static void main(String[] args) throws ParseException {
		//Testes de carga
		
		Paciente p1 = new Paciente();
		Paciente p2 = new Paciente();
		Convenio c1 = new Convenio();
		Convenio c2 = new Convenio();
		Especialidade ep1 = new Especialidade();
		Especialidade ep2 = new Especialidade();
		DiaAtendimento da1 = new DiaAtendimento();
		DiaAtendimento da2 = new DiaAtendimento();
		DiaAtendimento da3 = new DiaAtendimento();
		DiaAtendimento da4 = new DiaAtendimento();
		DiaAtendimento da5 = new DiaAtendimento();
		DiaAtendimento da6 = new DiaAtendimento();
		DiaAtendimento da7 = new DiaAtendimento();
		Medico m1 = new Medico();
		Medico m2 = new Medico();
		AgendaMedica am1 = new AgendaMedica();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date data = formato.parse("23/11/2015");
		
		p1.setNome("Joao Carlos");
		p1.setSexo('M');
		p2.setNome("Maria Carla");
		p2.setSexo('F');
		
		p1.setDataNascimento(data);
		p2.setDataNascimento(data);
		
		c1.setNome("Unimed");
		c2.setNome("Ipsemg");
		
		p1.setConvenio(c1);
		p2.setConvenio(c2);
		
		ep1.setNome("Pediatria");
		ep2.setNome("Dermatologia");
		
		da1.setIdDia(1);
		da1.setNomeDia("Domingo");
		
		da2.setIdDia(2);
		da2.setNomeDia("Segunda-feira");
		
		da3.setIdDia(3);
		da3.setNomeDia("Terça-feira");
		
		da4.setIdDia(4);
		da4.setNomeDia("Quarta-feira");
		
		da5.setIdDia(5);
		da5.setNomeDia("Quinta-feira");
		
		da6.setIdDia(6);
		da6.setNomeDia("Sexta-feira");
		
		da7.setIdDia(7);
		da7.setNomeDia("Sabado");
		
		List<DiaAtendimento> das = new ArrayList<>();
		das.addAll(Arrays.asList(da1, da2, da3));
		
		m1.setCrm(123456);
		m1.setCelular("73377337");
		m1.setEmail("123456@teste.com");
		m1.setEspecialidade(ep1);
		m1.setHorarioAtendimento("10:00");
		m1.setIntervaloAtendimento("00:15");
		m1.setNome("Dr. Felisbino");
		m1.setDiaAtendimento(das);
		
		m2.setCrm(123321);
		m2.setCelular("93931317");
		m2.setEmail("123321@teste.com");
		m2.setEspecialidade(ep2);
		m2.setHorarioAtendimento("08:00");
		m2.setIntervaloAtendimento("00:30");
		m2.setNome("Dra. Felicia");
		m1.setDiaAtendimento(das);
		
		am1.setDiaAgendamento(new Date());
		am1.setMedico(m1);
		
		
		//Persist
		ConvenioDAO.getInstance().persist(c1);
		ConvenioDAO.getInstance().persist(c2);
		
		PacienteDAO.getInstance().persist(p1);
		am1.setPaciente(p1);
		
		PacienteDAO.getInstance().persist(p2);
		EspecialidadeDAO.getInstance().persist(ep1);
		EspecialidadeDAO.getInstance().persist(ep2);
		DiaAtendimentoDAO.getInstance().persist(da1);
		DiaAtendimentoDAO.getInstance().persist(da2);
		DiaAtendimentoDAO.getInstance().persist(da3);
		DiaAtendimentoDAO.getInstance().persist(da4);
		DiaAtendimentoDAO.getInstance().persist(da5);
		DiaAtendimentoDAO.getInstance().persist(da6);
		DiaAtendimentoDAO.getInstance().persist(da7);
		MedicoDAO.getInstance().persist(m1);
		MedicoDAO.getInstance().persist(m2);
		AgendaMedicaDAO.getInstance().persist(am1);
	}

}
