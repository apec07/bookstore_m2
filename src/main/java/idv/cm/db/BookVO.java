package idv.cm.db;

public class BookVO implements java.io.Serializable {
	
	private int id;
	private String name;
	private String author;
	private String publisher;
	private Double price;
	private Integer quantity;
	
	public BookVO() {
		name = "";
		author = "";
		publisher = "";
		price = 0.0;
		quantity = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BookVO [name=" + name + "]";
	}



	


}
