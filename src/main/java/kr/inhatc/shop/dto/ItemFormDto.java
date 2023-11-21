package kr.inhatc.shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.inhatc.shop.constant.ItemSellStatus;
import kr.inhatc.shop.entity.Item;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFormDto {
    private Long id;             // 상품 코드

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;       // 상품명

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private int price;           // 상품 가격

    @NotNull(message = "수량은 필수 입력 값입니다.")
    private int stockNumber;     // 상품 재고 수량

    @NotBlank(message = "상품 상세 설명은 필수 입력 값입니다.")
    private String itemDetail;   // 상품 상세 설명

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto entityToDto(Item item) {
        return modelMapper.map(item, ItemFormDto.class);
    }
}
