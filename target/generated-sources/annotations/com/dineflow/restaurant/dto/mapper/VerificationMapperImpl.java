package com.dineflow.restaurant.dto.mapper;

import com.dineflow.restaurant.dto.RestaurantVerificationDTO;
import com.dineflow.restaurant.dto.request.VerificationRequest;
import com.dineflow.restaurant.entity.RestaurantVerification;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T17:08:35+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (JetBrains s.r.o.)"
)
@Component
public class VerificationMapperImpl implements VerificationMapper {

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Override
    public RestaurantVerification toEntity(VerificationRequest request) {
        if ( request == null ) {
            return null;
        }

        RestaurantVerification.RestaurantVerificationBuilder restaurantVerification = RestaurantVerification.builder();

        restaurantVerification.locationImageUrl( request.getLocationImageUrl() );
        restaurantVerification.fssaiLicenseNumber( request.getFssaiLicenseNumber() );
        restaurantVerification.fssaiLicenseImageUrl( request.getFssaiLicenseImageUrl() );
        restaurantVerification.gstNumber( request.getGstNumber() );
        restaurantVerification.gstCertificateUrl( request.getGstCertificateUrl() );
        restaurantVerification.panNumber( request.getPanNumber() );
        restaurantVerification.panCardUrl( request.getPanCardUrl() );
        restaurantVerification.ownerPanCardUrl( request.getOwnerPanCardUrl() );
        restaurantVerification.ownerAadhaarCardUrl( request.getOwnerAadhaarCardUrl() );
        restaurantVerification.bankAccountNumber( request.getBankAccountNumber() );
        restaurantVerification.bankName( request.getBankName() );
        restaurantVerification.bankIfscCode( request.getBankIfscCode() );
        restaurantVerification.cancelledChequeUrl( request.getCancelledChequeUrl() );

        return restaurantVerification.build();
    }

    @Override
    public RestaurantVerificationDTO toDto(RestaurantVerification entity) {
        if ( entity == null ) {
            return null;
        }

        RestaurantVerificationDTO.RestaurantVerificationDTOBuilder restaurantVerificationDTO = RestaurantVerificationDTO.builder();

        restaurantVerificationDTO.restaurant( restaurantMapper.toDto( entity.getRestaurant() ) );
        restaurantVerificationDTO.locationImageUrl( entity.getLocationImageUrl() );
        restaurantVerificationDTO.id( entity.getId() );
        restaurantVerificationDTO.fssaiLicenseNumber( entity.getFssaiLicenseNumber() );
        restaurantVerificationDTO.fssaiLicenseImageUrl( entity.getFssaiLicenseImageUrl() );
        restaurantVerificationDTO.gstNumber( entity.getGstNumber() );
        restaurantVerificationDTO.gstCertificateUrl( entity.getGstCertificateUrl() );
        restaurantVerificationDTO.panNumber( entity.getPanNumber() );
        restaurantVerificationDTO.panCardUrl( entity.getPanCardUrl() );
        restaurantVerificationDTO.ownerPanCardUrl( entity.getOwnerPanCardUrl() );
        restaurantVerificationDTO.ownerAadhaarCardUrl( entity.getOwnerAadhaarCardUrl() );
        restaurantVerificationDTO.bankAccountNumber( entity.getBankAccountNumber() );
        restaurantVerificationDTO.bankName( entity.getBankName() );
        restaurantVerificationDTO.bankIfscCode( entity.getBankIfscCode() );
        restaurantVerificationDTO.cancelledChequeUrl( entity.getCancelledChequeUrl() );
        restaurantVerificationDTO.verificationStatus( entity.getVerificationStatus() );
        restaurantVerificationDTO.verificationNotes( entity.getVerificationNotes() );
        restaurantVerificationDTO.createdAt( entity.getCreatedAt() );
        restaurantVerificationDTO.updatedAt( entity.getUpdatedAt() );

        return restaurantVerificationDTO.build();
    }
}
