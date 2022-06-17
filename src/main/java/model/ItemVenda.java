package model;

import java.util.Objects;

public class ItemVenda {
	private Integer id;
	private Cafe cafe;
	private int quant;
	private double valor;
	
	ItemVenda(){
		this.quant = 1;
	}
	
	public ItemVenda(Cafe cafe, double valor) {
		super();
		this.cafe = cafe;
		this.valor = valor;
		this.quant = 1;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cafe getCafe() {
		return cafe;
	}
	public void setCafeId(Cafe cafe) {
		this.cafe = cafe;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cafe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		return Objects.equals(cafe, other.cafe);
	}
	
	
}
