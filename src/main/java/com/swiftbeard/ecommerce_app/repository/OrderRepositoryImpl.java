package com.swiftbeard.ecommerce_app.repository;

import com.swiftbeard.ecommerce_app.entity.CartEntity;
import com.swiftbeard.ecommerce_app.entity.ItemEntity;
import com.swiftbeard.ecommerce_app.entity.OrderEntity;
import com.swiftbeard.ecommerce_app.entity.OrderItemEntity;
import com.swiftbeard.ecommerce_app.exception.ResourceNotFoundException;
import com.swiftbeard.ecommerce_app.model.NewOrder;
import com.swiftbeard.ecommerce_app.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Repository;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt {

    @PersistenceContext private final EntityManager em;

    private final ItemRepository itemRepo;
    private final CartRepository cRepo;
    private final OrderItemRepository oiRepo;

    public OrderRepositoryImpl(
            EntityManager em, ItemRepository itemRepo, CartRepository cRepo, OrderItemRepository oiRepo) {
        this.em = em;
        this.itemRepo = itemRepo;
        this.cRepo = cRepo;
        this.oiRepo = oiRepo;
    }


    @Override
    public Optional<OrderEntity> insert(NewOrder m) {
        // Items are already in cart and saved in db when user places the order
        // Here, you can also populate the other Order details like address, card etc.
        Iterable<ItemEntity> dbItems = itemRepo.findByCustomerId(m.getCustomerId());
        List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).toList();
        if (items.size() < 1) {
            throw new ResourceNotFoundException(
                    String.format("There is no item found in customer's (ID: %s) cart.", m.getCustomerId()));
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntity i : items) {
            total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
        }
        Timestamp orderDate = Timestamp.from(Instant.now());
        em.createNativeQuery(
                        """
                    INSERT INTO ecomm.orders (address_id, card_id, customer_id, order_date, total, status)
                     VALUES(?, ?, ?, ?, ?, ?)
                    """)
                .setParameter(1, m.getAddress().getId())
                .setParameter(2, m.getCard().getId())
                .setParameter(3, m.getCustomerId())
                .setParameter(4, orderDate)
                .setParameter(5, total)
                .setParameter(6, Order.StatusEnum.CREATED.getValue())
                .executeUpdate();
        Optional<CartEntity> oCart = cRepo.findByCustomerId(UUID.fromString(m.getCustomerId()));
        CartEntity cart =
                oCart.orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        String.format(
                                                "Cart not found for given customer (ID: %s)", m.getCustomerId())));
        itemRepo.deleteCartItemJoinById(
                cart.getItems().stream().map(ItemEntity::getId).collect(toList()), cart.getId());
        OrderEntity entity =
                (OrderEntity)
                        em.createNativeQuery(
                                        """
                            SELECT o.* FROM ecomm.orders o WHERE o.customer_id = ? AND o.order_date >= ?
                            """,
                                        OrderEntity.class)
                                .setParameter(1, m.getCustomerId())
                                .setParameter(
                                        2,
                                        OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z"))
                                                .truncatedTo(ChronoUnit.MICROS))
                                .getSingleResult();
        oiRepo.saveAll(
                cart.getItems().stream()
                        .map(i -> new OrderItemEntity().setOrderId(entity.getId()).setItemId(i.getId()))
                        .collect(toList()));
        return Optional.of(entity);
    }
}
