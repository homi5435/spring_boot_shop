package kr.inhatc.shop.service;


import jakarta.persistence.EntityNotFoundException;
import kr.inhatc.shop.entity.ItemImg;
import kr.inhatc.shop.repository.ItemImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }
        itemImg.updateItemImg(imgName, oriImgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException {
        if(!itemImgFile.isEmpty()){
            ItemImg itemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(itemImg.getImgName())){
                fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());
            }
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = "";
            String imgUrl = "";

            if (!StringUtils.isEmpty(oriImgName)){
                imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
                imgUrl = "/images/item/" + imgName;
            }
            itemImg.updateItemImg(imgName, oriImgName, imgUrl); //dirty checking
        }
    }
}
