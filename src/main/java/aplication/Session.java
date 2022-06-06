package aplication;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
	
	private static Session session = null;
	
	private Session() {
		// nao permite uma instancia externa
	}
	
	public static Session getInstance() {
		if (session == null)
			session = new Session();
		return session;
	}
	
	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance() == null)
			throw new RuntimeException("Opa, vc não esta utilizando um servidor web.");
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public void set(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}
	
	public Object get(String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	public void invalidateSession() {
		getExternalContext().invalidateSession();
	}
	
}
