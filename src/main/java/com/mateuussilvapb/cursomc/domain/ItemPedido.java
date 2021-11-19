package com.mateuussilvapb.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * Essa anotação é utilizada em atributos que devem ser ignorados na hora do
	 * mapeamento. Isso foi necessário pois caso o atributo não fosse ignorado, iria
	 * gerar uma serialização cíclica.
	 */
	@JsonIgnore
	/*
	 * Como a classe ItemPedido é uma classe que é composta pelas informações das
	 * classes Pedido e Produto, sua chave é composta pelas chaves (nesse caso,
	 * estrangeiras) das classes Pedido e Produto. Para que isso seja possível, é
	 * necessário criar uma classe auxiliar, que é composta apenas por duas várias:
	 * uma que representa o pedido, outra que representa o produto. Essa classe
	 * auxiliar irá servir como chave primária nessa classe (ItemPedido).
	 */
	@EmbeddedId
	private ItemPedidoPk id = new ItemPedidoPk();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

	public ItemPedidoPk getId() {
		return id;
	}

	public void setId(ItemPedidoPk id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/*
	 * Essa anotação é utilizada em atributos que devem ser ignorados na hora do
	 * mapeamento. Isso foi necessário pois caso o atributo não fosse ignorado, iria
	 * gerar uma serialização cíclica.
	 */
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		/*
		 * Variável que serve para formatar os números de acordo com a localidade.
		 */
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("---> ");
		builder.append(getProduto().getNome());
		builder.append(", qte: ");
		builder.append(getQuantidade());
		builder.append(", preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}

}
