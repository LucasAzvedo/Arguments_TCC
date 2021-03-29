package br.com.arguments.converter;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.util.jsf.SessionUtil;

@FacesConverter("usuarioConverter")
@ManagedBean
public class UsuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		} else {
			Long id = Long.parseLong(value);
			List<UsuarioEntity> ls = (List<UsuarioEntity>) SessionUtil.getParam("listaAlunosSelecionados");
			
			if(ls != null){
				for(UsuarioEntity usuario : ls){
					if(usuario.getId().equals(id)){
						return usuario;
					}
				}
			}
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		UsuarioEntity usuario = (UsuarioEntity) value;
		if (usuario != null) {
			return String.valueOf(usuario.getId());
		} else {
			return null;
		}

	}

}
