package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Fornecedor;
import validacoes.ValidaCNPJ;

@ManagedBean
@ViewScoped
public class FornecedorMB {
	private Fornecedor fornecedor = new Fornecedor();
	private List<Fornecedor> fornecedores = new ArrayList<>();
	private DAOGenerico<Fornecedor> dao = new DAOGenerico<>(Fornecedor.class);
	 ValidaCNPJ cnpj = new ValidaCNPJ();
	
	public FornecedorMB(){
		fornecedores = dao.buscarTodos();
	}

	public void inserir() {
		
		if(cnpj.isCNPJ(fornecedor.getCnpj())){
			
			if (fornecedor.getId() == null) {
				dao.salvar(fornecedor);
			} else {
				dao.alterar(fornecedor);
			}
			fornecedor = new Fornecedor();
			fornecedores = dao.buscarTodos();
		}
		
	
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		fornecedores = dao.buscarTodos();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedors(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	
	

}
