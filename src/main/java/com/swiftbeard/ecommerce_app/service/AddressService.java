package com.swiftbeard.ecommerce_app.service;


import com.swiftbeard.ecommerce_app.entity.AddressEntity;
import com.swiftbeard.ecommerce_app.model.AddAddressReq;

import java.util.Optional;

public interface AddressService {
    Optional<AddressEntity> createAddress(AddAddressReq addAddressReq);

    void deleteAddressesById(String id);

    Optional<AddressEntity> getAddressesById(String id);

    Iterable<AddressEntity> getAllAddresses();
}
