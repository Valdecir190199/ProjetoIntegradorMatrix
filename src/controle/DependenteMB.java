package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Dependente;

@ManagedBean
@ViewScoped
public class DependenteMB {
	private Dependente dependente = new Dependente();
	private List<Dependente> dependentes = new ArrayList<>();
	private DAOGenerico<Dependente> dao = new DAOGenerico<>(Dependente.class);
	
	public DependenteMB(){
		dependentes = dao.buscarTodos();
	}

	public void inserir() {
		if (dependente.getId() == null) {
			dao.salvar(dependente);
		} else {
			dao.alterar(dependente);
		}
		dependente = new Dependente();
		dependentes = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		dependentes = dao.buscarTodos();
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}


	

}
