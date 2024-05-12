package tech.loga.supplier;

import java.util.List;

public interface SupplierManagement {
    Supplier registerSupplier(Supplier supplier);
    Supplier getSupplier(Long id);
    List<Supplier> getAllSupplier();
    void editSupplier(Supplier supplier, Long id);
    void deleteSupplier(Long id);
}
