package tech.loga.invoicing;

public interface InvoiceManagement {
    void invoicing(InvoiceRequest invoiceRequest);
    void process(String uid, String action);
}
