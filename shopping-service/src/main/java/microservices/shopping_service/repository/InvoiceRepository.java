package microservices.shopping_service.repository;

import microservices.shopping_service.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(String customerId);
    public Invoice findByNumberInvoice(String numberInvoice);
}
