package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Cliente;

@ManagedBean
@ViewScoped
public class ClienteMB {
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<>();
	private DAOGenerico<Cliente> dao = new DAOGenerico<>(Cliente.class);
	
	public ClienteMB(){
		clientes = dao.buscarTodos();
	}

	public void inserir() {
		if (cliente.getId() == null) {
			dao.salvar(cliente);
		} else {
			dao.alterar(cliente);
		}
		cliente = new Cliente();
		clientes = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		clientes = dao.buscarTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	

}
