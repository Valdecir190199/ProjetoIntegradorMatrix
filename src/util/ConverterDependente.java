package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import banco.DAOGenerico;
import modelo.Dependente;

@FacesConverter("converterDependente")
public class ConverterDependente implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	
        if(value != null && value.trim().length() > 0) {
        	System.out.println("Dentro do IFFF");
        DAOGenerico<Dependente> daoDependente = new DAOGenerico<>(Dependente.class);
        	try {
                Dependente dependente = daoDependente.buscarPorId(Long.parseLong(value));
                return dependente;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "Estado Inválido"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Dependente) object).getId());
        }
        else {
            return null;
        }
    }
}