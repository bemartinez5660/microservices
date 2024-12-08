package microservices.shopping_service.service;

import microservices.shopping_service.entity.Invoice;

import java.util.List;

public interface InvoiceService {
    public Invoice createInvoice(Invoice invoice);
    public Invoice getInvoice(long id);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(long id);
    public List<Invoice> getAllInvoices();
    public List<Invoice> getInvoicesByCustomerId(String customerId);
}
