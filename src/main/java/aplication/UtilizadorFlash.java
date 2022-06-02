package aplication;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.unitins.livros.model.Livro;

public class UtilizadorFlash<T> {
	public void inserir(String chave,T objeto) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put(chave, objeto);
	}
	
	public T pegar(String chave) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep(chave);
		try{
			return (T)flash.get(chave);
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
