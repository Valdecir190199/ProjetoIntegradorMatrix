package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import banco.DAOGenerico;
import modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoMB {
	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<>();
	private DAOGenerico<Produto> dao = new DAOGenerico<>(Produto.class);
	
	public ProdutoMB(){
		produtos = dao.buscarTodos();
	}

	public void inserir() {
		if (produto.getId() == null) {
			dao.salvar(produto);
		} else {
			dao.alterar(produto);
		}
		produto = new Produto();
		produtos = dao.buscarTodos();
	}
	
	public void excluir(Long id){
		dao.excluir(id);
		produtos = dao.buscarTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public DAOGenerico<Produto> getDao() {
		return dao;
	}

	public void setDao(DAOGenerico<Produto> dao) {
		this.dao = dao;
	}

	


}
