package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import banco.DAOGenerico;
import modelo.ItensVenda;
import modelo.Produto;
import modelo.Venda;

@ManagedBean
@ViewScoped
public class VendaMB {
	private Venda venda = new Venda();
	private ItensVenda itensVenda = new ItensVenda();
	private List<ItensVenda> listaItensVenda = new ArrayList<>();
	private List<Venda> listaVendas = new ArrayList<>();
	private DAOGenerico<Venda> daoVenda = new DAOGenerico<>(Venda.class);
	private DAOGenerico<ItensVenda> daoItensVenda = new DAOGenerico<>(ItensVenda.class);

	public VendaMB() {
		listaVendas = daoVenda.buscarTodos();

	}

	public void adicionarItem() {
		System.out.println("DEntro do Método");
		if (itensVenda.getProduto() != null) {
			itensVenda.setValorUnitario(itensVenda.getProduto().getValorVenda());
			itensVenda.setValotTotal(itensVenda.getQuantidade() * itensVenda.getValorUnitario());
			listaItensVenda.add(itensVenda);
			itensVenda = new ItensVenda();
			System.out.println("QTDLista: " + listaItensVenda.size());
		}
	}

	public void finalizarVenda() {
		Double valorfinalvenda = 0.0;
		daoVenda.salvar(venda);
		for (ItensVenda it : listaItensVenda) {
			valorfinalvenda += it.getValotTotal();
			it.setVenda(venda);
			daoItensVenda.salvar(it);

		}
		venda.setValorTotalVenda(valorfinalvenda);
		daoVenda.alterar(venda);

		listaVendas = daoVenda.buscarTodos();
		System.out.println("Tamanho da Lista" + listaVendas.size());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Venda Realizada Com Sucesso!!", ""));

		novaVenda();
	}

	public void removerItem(ItensVenda itemRemover) {
		listaItensVenda.remove(itemRemover);
	}

	public void novaVenda() {
		venda = new Venda();
		listaItensVenda = new ArrayList<>();
		itensVenda = new ItensVenda();

	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public ItensVenda getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(ItensVenda itensVenda) {
		this.itensVenda = itensVenda;
	}

	public List<ItensVenda> getListaItensVenda() {
		return listaItensVenda;
	}

	public void setListaItensVenda(List<ItensVenda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}

	public List<Venda> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

}
