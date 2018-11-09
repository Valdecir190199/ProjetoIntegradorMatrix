package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Cidade;

@ManagedBean
@ViewScoped
public class CidadeMB {
	private Cidade cidade = new Cidade();
	private List<Cidade> cidades = new ArrayList<>();
	private DAOGenerico<Cidade> dao = new DAOGenerico<>(Cidade.class);
	
	public CidadeMB(){
		cidades = dao.buscarTodos();
	}

	public void inserir() {
		if (cidade.getId() == null) {
			dao.salvar(cidade);
		} else {
			dao.alterar(cidade);
		}
		cidade = new Cidade();
		cidades = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		cidades = dao.buscarTodos();
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	

}
