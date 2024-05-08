package tech.loga.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("resource-service")
public class AssetController {

    @Autowired
    private AssetManagement assetManagement;

    @GetMapping(path = "assets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asset> getAsset(@PathVariable Long id){
        try {
            return ResponseEntity.ok(assetManagement.getAssetById(id));
        }catch (Exception e){
            throw new AssetNotFoundException(String.format("Asset with id:%d not found",id));
        }
    }

    @GetMapping(path = "assets/{reference}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asset> getAsset(@PathVariable String reference){
        try {
            return ResponseEntity.ok(assetManagement.getAssetByReference(reference));
        }catch (Exception e){
            throw new AssetNotFoundException(String.format("Asset with reference : %s not found :\n%s",reference,e.getMessage()));
        }
    }

    @GetMapping(path = "assets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Asset>> getAssets(){
        try {
            return ResponseEntity.ok(assetManagement.getAllAsset());
        }catch (Exception e){
            throw new AssetNotFoundException(String.format("Any Asset : %s found :\n",e.getMessage()));
        }
    }

    @PutMapping(path = "assets/{id}")
    public void editAsset(@RequestBody AssetRequest request, @PathVariable Long id){
        try {
            assetManagement.editAsset(request,id);
        }catch (Exception e){
            throw new AssetRegistrationFailedException(String.format("Update Asset with id:%d failed \n:%s ",id,e.getMessage()));
        }
    }

    @DeleteMapping(path = "assets/{id}")
    public void deleteAsset(@PathVariable Long id) {
        try {
            assetManagement.deleteAsset(id);
        }catch (Exception e){
            throw new AssetRegistrationFailedException(String.format("Delete Asset with id:%d failed \n:%s ",id,e.getMessage()));
        }
    }
}
