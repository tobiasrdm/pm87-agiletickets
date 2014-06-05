package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	@Test
	public void deveCriarUmaSessaoParaDatasDeInicioEFimIguaisEPeriodicidadeDiaria() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario,
				Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario), sessoes.get(0)
				.getInicio());
	}

	@Test
	public void deveCriarUmaSessaoParaCadaDiaQuandoDataDeInicioForMenorQueDataFimEPeriodicidadeDiaria() {
		final int NUMERO_DE_DIAS = 3;
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(NUMERO_DE_DIAS - 1);
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario,
				Periodicidade.DIARIA);
		Assert.assertEquals(NUMERO_DE_DIAS, sessoes.size());
		for (int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(show, sessoes.get(i).getEspetaculo());
			Assert.assertEquals(inicio.toDateTime(horario).plusDays(i), sessoes
					.get(i).getInicio());
		}
	}
	
	@Test
	public void deveCriarUmaSessaoParaCadaSemanaQuandoDataDeInicioForMenorQueDataFimEPeriodicidadeSemanal() {
		final int NUMERO_DE_DIAS = 15;
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(NUMERO_DE_DIAS - 1);
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario,
				Periodicidade.SEMANAL);
		Assert.assertEquals(Weeks.weeksBetween(inicio, fim), sessoes.size());
		for (int i = 0; i < sessoes.size(); i++) {
			Assert.assertEquals(show, 
					sessoes.get(i).getEspetaculo());			
			Assert.assertEquals(inicio.toDateTime(horario).plusWeeks(i), sessoes
					.get(i).getInicio());
		}
	}

	@Test
	public void deveCriarUmaSessaoParaDatasDeInicioEFimIguaisEPeriodicidadeSemanal() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio;
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario,
				Periodicidade.SEMANAL);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(show, sessoes.get(0).getEspetaculo());
		Assert.assertEquals(inicio.toDateTime(horario), sessoes.get(0)
				.getInicio());
	}

	@Test
	public void naoDeveCriarUmaSessaoParaDatasDeInicioMaiorQueDataFim() {
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.minusDays(1);
		LocalTime horario = new LocalTime();
		Espetaculo show = new Espetaculo();
		List<Sessao> sessoes = show.criaSessoes(inicio, fim, horario,
				Periodicidade.DIARIA);
		Assert.assertEquals(0, sessoes.size());
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

}
