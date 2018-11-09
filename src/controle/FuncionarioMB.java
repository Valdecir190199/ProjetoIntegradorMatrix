package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Funcionario;

@ManagedBean
@ViewScoped
public class FuncionarioMB {
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<>();
	private DAOGenerico<Funcionario> dao = new DAOGenerico<>(Funcionario.class);
	
	public FuncionarioMB(){
		funcionarios = dao.buscarTodos();
	}

	public void inserir() {
		if (funcionario.getId() == null) {
			dao.salvar(funcionario);
		} else {
			dao.alterar(funcionario);
		}
		funcionario = new Funcionario();
		funcionarios = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		funcionarios = dao.buscarTodos();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	
	

}
