package com.dineflow.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "restaurant_verification", schema = "restaurants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantVerification {
    
    public enum VerificationStatus {
        PENDING,
        IN_PROGRESS,
        APPROVED,
        REJECTED
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
    
    @Column(name = "fssai_license_number", nullable = true)
    private String fssaiLicenseNumber;
    
    @Column(name = "fssai_license_image_url", nullable = true)
    private String fssaiLicenseImageUrl;
    
    @Column(name = "gst_number", nullable = true)
    private String gstNumber;
    
    @Column(name = "gst_certificate_url", nullable = true)
    private String gstCertificateUrl;
    
    @Column(name = "pan_number", nullable = true)
    private String panNumber;
    
    @Column(name = "pan_card_url", nullable = true)
    private String panCardUrl;
    
    @Column(name = "owner_pan_card_url", nullable = true)
    private String ownerPanCardUrl;
    
    @Column(name = "owner_aadhaar_card_url", nullable = true)
    private String ownerAadhaarCardUrl;
    
    @Column(name = "bank_account_number", nullable = true)
    private String bankAccountNumber;
    
    @Column(name = "bank_name", nullable = true)
    private String bankName;
    
    @Column(name = "bank_ifsc_code", nullable = true)
    private String bankIfscCode;
    
    @Column(name = "cancelled_cheque_url", nullable = true)
    private String cancelledChequeUrl;
    
    @Column(name = "location_image_url", nullable = true)
    private String locationImageUrl;
    
    @Column(name = "verification_status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;
    
    @Column(name = "verification_notes")
    private String verificationNotes;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
