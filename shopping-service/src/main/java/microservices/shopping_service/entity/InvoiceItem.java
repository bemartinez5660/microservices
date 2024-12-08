package microservices.shopping_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tbl_invoice_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive(message = "Tiene que ser una cantidad positiva")
    private int quantity;

    private double price;

    @Column(name = "product_id")
    private Long productId;

    @Transient
    private Double subTotal;

    public Double getSubTotal() {
        if (this.price > 0) {
            return this.price * this.quantity;
        } else {
            return (double) 0;
        }
    }

}
