package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listMessage(Model model){
        model.addAttribute("messages", messageRepository.findAll());
        return "list";

    }

    @GetMapping("/add")
    public String messageForm(Model model){
        model.addAttribute("message", new Message());
        return "messageForm";
    }


    @RequestMapping("/process")
    public String processForm(@Valid Message message, BindingResult result){
        if (result.hasErrors()){
            return "todoForm";
        }
        messageRepository.save(message);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String processActor(@ModelAttribute Message message,
                               @RequestParam("file") MultipartFile file){
        if (file.isEmpty()){
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(),
                    ObjectUtils.asMap("resourcetype", "auto"));
            message.setPicture(uploadResult.get("url").toString());
            messageRepository.save(message);
        } catch (IOException e){
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }



    @RequestMapping("/edit/{id}")
    public String showTODO(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateTODO(@PathVariable("id") long id, Model model){
        model.addAttribute("message", messageRepository.findById(id).get());
        return "messageForm";
    }

    @RequestMapping("/delete/{id}")
    public String delTODO(@PathVariable("id") long id){
        messageRepository.deleteById(id);
        return "redirect:/";
    }








}
