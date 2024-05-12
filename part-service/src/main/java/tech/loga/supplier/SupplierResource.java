package tech.loga.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierResource implements SupplierManagement{

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier registerSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplier(Long id) {
        if(supplierRepository.findById(id).isPresent()) {
            return supplierRepository.findById(id).get();
        }else {
            throw new SupplierNotFoundException(String.format("Supplier with id:%d not found", id));
        }
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public void editSupplier(Supplier supplier, Long id) {
        supplierRepository
                .findById(id)
                .ifPresent(up -> {
                    up.setName(supplier.getName());
                    up.setAddress(supplier.getAddress());
                    up.setContact(supplier.getContact());
                    supplierRepository.saveAndFlush(up);
                });
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }
}
