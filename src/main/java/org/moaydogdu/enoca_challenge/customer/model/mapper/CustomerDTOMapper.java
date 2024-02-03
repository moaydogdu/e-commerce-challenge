package org.moaydogdu.enoca_challenge.customer.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.customer.model.dto.request.CustomerRegisterRequest;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;

@UtilityClass
public class CustomerDTOMapper {

    public static CustomerEntity mapForSaving(
            final CustomerRegisterRequest customerRegisterRequest
    ) {
        return CustomerEntity.builder()
                .email(customerRegisterRequest.getEmail())
                .firstName(customerRegisterRequest.getFirstName())
                .lastName(customerRegisterRequest.getLastName())
                .phoneNumber(customerRegisterRequest.getPhoneNumber())
                .build();
    }
}
