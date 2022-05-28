package model;


public enum TipoUsuario {
	ADMINISTRADOR(1, "administrador"), CLIENTE(2, "cliente");
	
	int id;
	String label;
	
	private TipoUsuario(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}
	
	public static TipoUsuario valueOf(int id) {
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (id == tipo.getId())
				return tipo;
		}
		return null;
	}
}
