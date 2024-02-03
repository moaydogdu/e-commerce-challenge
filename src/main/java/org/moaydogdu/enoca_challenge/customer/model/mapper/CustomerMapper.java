package org.moaydogdu.enoca_challenge.customer.model.mapper;

import lombok.experimental.UtilityClass;
import org.moaydogdu.enoca_challenge.customer.model.Customer;
import org.moaydogdu.enoca_challenge.customer.model.entity.CustomerEntity;

import java.util.List;

@UtilityClass
public class CustomerMapper {

    public static Customer toDomainModel(
            final CustomerEntity customerEntity
    ) {
        return Customer.builder()
                .id(customerEntity.getId())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPassword())
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .phoneNumber(customerEntity.getPhoneNumber())
                .userType(customerEntity.getUserType())
                .userStatus(customerEntity.getUserStatus())
                .createdAt(customerEntity.getCreatedAt())
                .createdBy(customerEntity.getCreatedBy())
                .updatedAt(customerEntity.getUpdatedAt())
                .updatedBy(customerEntity.getUpdatedBy())
                .build();
    }

    public static List<Customer> toDomainModel(
            final List<CustomerEntity> customerEntities
    ) {
        return customerEntities.stream()
                .map(CustomerMapper::toDomainModel)
                .toList();
    }

    public static CustomerEntity toEntity(
            final Customer customer
    ) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .userType(customer.getUserType())
                .userStatus(customer.getUserStatus())
                .createdAt(customer.getCreatedAt())
                .createdBy(customer.getCreatedBy())
                .updatedAt(customer.getUpdatedAt())
                .updatedBy(customer.getUpdatedBy())
                .build();
    }
}
