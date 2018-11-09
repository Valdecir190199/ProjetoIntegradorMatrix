package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import banco.DAOGenerico;
import modelo.MidiaLoc;

@FacesConverter("converterMidiaLoc")
public class ConverterMidiaLoc implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
    	
        if(value != null && value.trim().length() > 0) {
        	System.out.println("Dentro do IFFF");
        DAOGenerico<MidiaLoc> daoMidiaLoc = new DAOGenerico<>(MidiaLoc.class);
        	try {
                MidiaLoc midiaLoc = daoMidiaLoc.buscarPorId(Long.parseLong(value));
                return midiaLoc;
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ", "MidiaLoc Inv�lido"));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((MidiaLoc) object).getId());
        }
        else {
            return null;
        }
    }
}