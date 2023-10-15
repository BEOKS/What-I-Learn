package com.example.fileupload.controller;

import com.example.fileupload.controller.dto.ItemForm;
import com.example.fileupload.domain.Item;
import com.example.fileupload.domain.ItemRepository;
import com.example.fileupload.domain.UploadFile;
import com.example.fileupload.file.FileStore;
import jakarta.servlet.DispatcherType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm itemForm){
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(form.getMultipartFile());
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setUploadFile(uploadFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);

        redirectAttributes.addAttribute("itemId",item.getId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item",item);
        return "item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFulPath(filename));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        DispatcherType

        String storeFilename = item.getUploadFile().getStoreFilename();
        String uploadFilename = item.getUploadFile().getUploadFilename();

        String encode = UriUtils.encode(uploadFilename, StandardCharsets.UTF_8);
        UrlResource urlResource = new UrlResource("file:" + fileStore.getFulPath(storeFilename));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+encode+"\"")
                .body(urlResource);
    }
}
