package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.FornecedorDAO;
import model.Fornecedor;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter<Fornecedor>{

	@Override
	public Fornecedor getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		FornecedorDAO dao = new FornecedorDAO();
		return dao.getById(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Fornecedor fornecedor) {
		if (fornecedor == null || fornecedor.getId() == null) {
			return null;
		}
		return fornecedor.getId().toString();
	}
	
}
