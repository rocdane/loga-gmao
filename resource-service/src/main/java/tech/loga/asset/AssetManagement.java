package tech.loga.asset;

import java.util.List;

public interface AssetManagement {
    Asset getAssetById(Long id);
    Asset getAssetByReference(String reference);
    List<Asset> getAllAsset();
    void editAsset(AssetRequest asset, Long id);
    void deleteAsset(Long id);
}
