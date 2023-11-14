package kr.inhatc.shop.entity;

import jakarta.persistence.*;
import kr.inhatc.shop.utils.audit.BaseEntity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", nullable = false)
    private Long id;                        // 장바구니 상품 ID

    @ManyToOne(fetch = FetchType.LAZY)      // N:1 매핑(CartItem N), 지연로딩
    @JoinColumn(name = "cart_id")           // 외래키(FK)를 매핑할 때 사용
    private Cart cart;                      // 장바구니

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")           // 외래키(FK)를 매핑할 때 사용
    private Item item;                      // 상품

    private int count;                      // 수량
}
