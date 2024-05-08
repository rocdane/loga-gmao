package tech.loga.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetResource implements AssetManagement
{
    @Autowired
    private AssetRepository assetRepository;

    @Override
    public Asset getAssetById(Long id) {
        if(assetRepository.findById(id).isPresent()){
            return assetRepository.findById(id).get();
        }
        throw new RuntimeException(String.format("Asset with id : %d not found",id));
    }

    @Override
    public Asset getAssetByReference(String reference) {
        if(assetRepository.findByReference(reference).isPresent()) {
            return assetRepository.findByReference(reference).get();
        }
        throw new RuntimeException(String.format("Asset with reference : %s not found",reference));
    }

    @Override
    public List<Asset> getAllAsset() {
        return assetRepository.findAll();
    }

    @Override
    public void editAsset(AssetRequest request, Long id) {
        assetRepository
                .findById(id)
                .ifPresentOrElse(up -> {
                    up.setDesignation(request.designation());
                    up.setReference(request.reference());
                    assetRepository.saveAndFlush(up);
                },() -> {
                    throw new RuntimeException(String.format("Update Asset with id : %d failed",id));
                });
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepository
                .findById(id)
                .ifPresentOrElse(asset -> {
                    assetRepository.delete(asset);
                },() -> {
                    throw new RuntimeException(String.format("Asset with id : %d not found",id));
                });
    }
}
