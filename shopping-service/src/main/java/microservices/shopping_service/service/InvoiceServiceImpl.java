package microservices.shopping_service.service;

import lombok.RequiredArgsConstructor;
import microservices.shopping_service.entity.Invoice;
import microservices.shopping_service.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB != null) {
            return invoiceDB;
        }
        invoice.setState("CREATED");
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoice(long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if (invoiceDB == null) {
            return null;
        }
        invoice.setState("UPDATED");
        invoice.setId(invoiceDB.getId());

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice deleteInvoice(long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return null;
        }
        invoice.setState("DELETED");

        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public List<Invoice> getInvoicesByCustomerId(String customerId) {
        return invoiceRepository.findByCustomerId(customerId);
    }
}
