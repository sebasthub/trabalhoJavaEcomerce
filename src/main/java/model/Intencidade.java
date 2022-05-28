package model;

public enum Intencidade {
	NIVEL1(1, "nivel 1"), NIVEL2(2, "nivel 2"), NIVEL3(3, "nivel 3"), NIVEL4(4, "nivel 4");
	
	int id;
	String label;
	
	private Intencidade(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public static Intencidade valueOf(int id) {
		for (Intencidade inte : Intencidade.values()) {
			if (id == inte.getId())
				return inte;
		}
		return null;
	}
}
