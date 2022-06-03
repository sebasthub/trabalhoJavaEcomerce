package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import aplication.Util;
import aplication.UtilizadorFlash;
import dao.CafeDAO;
import model.Cafe;

@Named
@RequestScoped
public class CadastroCafeController {
	
	private Cafe cafe;
	
	private CadastroCafeController() {
		UtilizadorFlash<Cafe> flash = new UtilizadorFlash<Cafe>();
		cafe = flash.pegar("cafe");
	}
	
	public void criar() {
		CafeDAO d = new CafeDAO();
		if (d.insert(cafe)) {
			Util.addMessageInfo("produto adicionado");
		}else {
			Util.addMessageError("erro na inserção");
		}
		Util.redirect("cafe.xhtml");
	}
	
	public void limpar() {
		cafe = null;
	}
	
	public void update() {
		CafeDAO c = new CafeDAO();
		if (c.update(cafe)) {
			Util.addMessageInfo("atualizado");
		}else {
			Util.addMessageError("erro no update");
		}
		Util.redirect("cafe.xhtml");
	}
	
	public void delete() {
		CafeDAO c = new CafeDAO();
		if (c.delete(cafe.getId())) {
			Util.addMessageInfo("atualizado");
		}else {
			Util.addMessageError("erro no update");
		}
		Util.redirect("cafe.xhtml");
	}
	
	public Cafe getCafe() {
		if(cafe == null) {
			cafe = new Cafe();
		}
		return cafe;
	}
	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}
}
