package kr.inhatc.shop.dto;

import jakarta.persistence.Entity;
import kr.inhatc.shop.entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemImgDto {
    private Long id;

    private String imgName; //이미지 파일명

    private String oriImgName; // 원본 이미지 파일명

    private String imgUrl; // 이미지 파일 경로

    private String regImgYn; // 대표 이미지 여부

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto entityToDto(ItemImg itemImg) { return modelMapper.map(itemImg, ItemImgDto.class);}

}
