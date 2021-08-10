package fpoly.java6.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@OneToOne
	@JoinColumn(name = "size_id", referencedColumnName = "id")
	private Size size;

	@OneToOne
	@JoinColumn(name = "sugar_id", referencedColumnName = "id")
	private Sugar sugar;

	@OneToOne
	@JoinColumn(name = "ice_id", referencedColumnName = "id")
	private Ice ice;

	private Long quantity;

	@ManyToOne
	@JoinColumn(name = "receipt_id", referencedColumnName = "id")
	private Receipt receipt;

	@ManyToMany
	@JoinTable(name = "item_topping", joinColumns = {
			@JoinColumn(name = "item_id")
	})
	private List<Topping> topping;
}
