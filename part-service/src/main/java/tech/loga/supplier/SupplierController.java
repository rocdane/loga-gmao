package tech.loga.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("part-service")
public class SupplierController {

    @Autowired
    private SupplierManagement supplierManagement;

}
