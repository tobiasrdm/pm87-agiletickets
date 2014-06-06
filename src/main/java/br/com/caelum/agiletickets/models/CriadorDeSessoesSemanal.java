package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public class CriadorDeSessoesSemanal implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim,
			LocalTime horario, Periodicidade periodicidade,
			Espetaculo espetaculo) {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		if (inicio.isBefore(fim) || inicio.isEqual(fim)) {
			int numeroDeSemanas = Weeks.weeksBetween(inicio, fim).getWeeks() + 1;
			for (int i = 0; i < numeroDeSemanas; i++) {
				Sessao sessao = new Sessao();
				sessao.setEspetaculo(espetaculo);
				sessao.setInicio(inicio.plusWeeks(i).toDateTime(horario));
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

}
