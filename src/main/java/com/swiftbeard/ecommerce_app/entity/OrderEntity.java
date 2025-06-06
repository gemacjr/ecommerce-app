package com.swiftbeard.ecommerce_app.entity;

import com.swiftbeard.ecommerce_app.model.Order;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Order.StatusEnum status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", insertable=false, updatable=false)
    private AddressEntity addressEntity;

    @OneToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
    private PaymentEntity paymentEntity;

    @JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID")
    @OneToOne
    private ShipmentEntity shipment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
    private CardEntity cardEntity;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ORDER_ITEM",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();

    @OneToOne(mappedBy = "orderEntity")
    private AuthorizationEntity authorizationEntity;

    public UUID getId() {
        return id;
    }

    public OrderEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderEntity setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public Order.StatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(Order.StatusEnum status) {
        this.status = status;
        return this;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public OrderEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public OrderEntity setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
        return this;
    }

    public PaymentEntity getPaymentEntity() {
        return paymentEntity;
    }

    public OrderEntity setPaymentEntity(PaymentEntity paymentEntity) {
        this.paymentEntity = paymentEntity;
        return this;
    }

    public ShipmentEntity getShipments() {
        return shipment;
    }

    public OrderEntity setShipments(ShipmentEntity shipment) {
        this.shipment = shipment;
        return this;
    }

    public CardEntity getCardEntity() {
        return cardEntity;
    }

    public OrderEntity setCardEntity(CardEntity cardEntity) {
        this.cardEntity = cardEntity;
        return this;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public OrderEntity setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public OrderEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }

    public AuthorizationEntity getAuthorizationEntity() {
        return authorizationEntity;
    }

    public OrderEntity setAuthorizationEntity(
            AuthorizationEntity authorizationEntity) {
        this.authorizationEntity = authorizationEntity;
        return this;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", total=" + total +
                ", status=" + status +
                ", userEntity=" + userEntity +
                ", addressEntity=" + addressEntity +
                ", paymentEntity=" + paymentEntity +
                ", shipment=" + shipment +
                ", cardEntity=" + cardEntity +
                ", orderDate=" + orderDate +
                ", items=" + items +
                ", authorizationEntity=" + authorizationEntity +
                '}';
    }
}
