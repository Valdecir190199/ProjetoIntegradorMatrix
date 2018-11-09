package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.MidiaLoc;

@ManagedBean
@ViewScoped
public class MidiaLocMB {
	private MidiaLoc midiaLoc = new MidiaLoc();
	private List<MidiaLoc> midiaLocs = new ArrayList<>();
	private DAOGenerico<MidiaLoc> dao = new DAOGenerico<>(MidiaLoc.class);
	
	public MidiaLocMB(){
		midiaLocs = dao.buscarTodos();
	}

	public void inserir() {
		if (midiaLoc.getId() == null) {
			dao.salvar(midiaLoc);
		} else {
			dao.alterar(midiaLoc);
		}
		midiaLoc = new MidiaLoc();
		midiaLocs = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		midiaLocs = dao.buscarTodos();
	}

	public MidiaLoc getMidiaLoc() {
		return midiaLoc;
	}

	public void setMidiaLoc(MidiaLoc midiaLoc) {
		this.midiaLoc = midiaLoc;
	}

	public List<MidiaLoc> getMidiaLocs() {
		return midiaLocs;
	}

	public void setMidiaLocs(List<MidiaLoc> midiaLocs) {
		this.midiaLocs = midiaLocs;
	}

	

}
