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
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)       // 1:1 매핑, 지연로딩
    @JoinColumn(name = "member_id")         // 외래키(FK)를 매핑할 때 사용
    private Member member;

}
