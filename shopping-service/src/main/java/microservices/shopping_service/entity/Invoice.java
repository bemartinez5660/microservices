package microservices.shopping_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_invoice")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number_invoice")
    @NotBlank(message = "No puede estar en blanco")
    private String numberInvoice;

    @NotBlank(message = "No puede estar en blanco")
    private String description;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<InvoiceItem> items;

    private String state;
}
