package model;

public class RouteProduct {
	
	private Integer id;
	private Product product;
	private RouteDestination routeDestination;
	private Double quantity;
	
	
	public RouteProduct(Integer id, Product product,
			RouteDestination routeDestination, Double quantity) {
		super();
		this.id = id;
		this.product = product;
		this.routeDestination = routeDestination;
		this.quantity = quantity;
	}


	public RouteProduct() {
		super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public RouteDestination getRouteDestination() {
		return routeDestination;
	}


	public void setRouteDestination(RouteDestination routeDestination) {
		this.routeDestination = routeDestination;
	}


	public Double getQuantity() {
		return quantity;
	}


	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteProduct other = (RouteProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return this.getQuantity().intValue()+" "+this.getProduct().getName();
	}
	
	
	
	
}
