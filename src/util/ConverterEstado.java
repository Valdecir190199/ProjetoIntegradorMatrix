package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import banco.DAOGenerico;
import modelo.Estado;

@FacesConverter("converterEstado")
public class ConverterEstado implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	
        if(value != null && value.trim().length() > 0) {
        	System.out.println("Dentro do IFFF");
        DAOGenerico<Estado> daoEstado = new DAOGenerico<>(Estado.class);
        	try {
                Estado estado = daoEstado.buscarPorId(Long.parseLong(value));
                return estado;
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
            return String.valueOf(((Estado) object).getId());
        }
        else {
            return null;
        }
    }
}