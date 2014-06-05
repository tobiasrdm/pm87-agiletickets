package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	
	DIARIA {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesDiaria();
		}
	}, SEMANAL {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesSemanal();
		}
	};
	
	public abstract CriadorDeSessoes getCriadorDeSessoes();
	
}
