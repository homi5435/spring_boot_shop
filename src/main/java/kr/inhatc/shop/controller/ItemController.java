package kr.inhatc.shop.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import kr.inhatc.shop.dto.ItemFormDto;
import kr.inhatc.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemSer;

    @GetMapping("/admin/item/new")
    public String itemForm(Model model) {
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/itemForm";
    }

    @PostMapping("/admin/item/new")
    public String itemNew(@Valid ItemFormDto itemFormDto,
                          @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                          BindingResult bindingResult,
                          Model model){
        if(bindingResult.hasErrors()) {
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "item/itemForm";
        }
        try {
            itemSer.saveItem(itemFormDto, itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 등록 중 오류가 발생하였습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }

    @GetMapping("/admin/item/{itemId}") // /admin/item/1
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model){

        try {
            ItemFormDto itemFormDto = itemSer.getItemDetail(itemId);
            model.addAttribute("itemFormDto", itemFormDto);
            return "item/itemForm";
        }catch (EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("itemFormDto", new ItemFormDto());
            return "item/itemForm";
        }

    }

    @PostMapping("/admin/item/{itemId}")
    public String itemUpdate(@Valid ItemFormDto itemFormDto,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                             BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()) {
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입니다.");
            return "item/itemForm";
        }
        try {
            itemSer.updateItem(itemFormDto, itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 수정 중 오류가 발생하였습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }
}
