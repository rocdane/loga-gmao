package tech.loga.asset;

import java.util.List;

public interface AssetManagement {
    Asset createAsset(Asset asset);
    Asset getAssetById(Long id);
    Asset getAssetByReference(String reference);
    List<Asset> getAllAsset();
    void editAsset(Asset asset, Long id);
    void deleteAsset(Long id);
}
