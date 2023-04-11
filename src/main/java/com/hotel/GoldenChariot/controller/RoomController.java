package com.hotel.GoldenChariot.controller;

import com.hotel.GoldenChariot.dto.admin.AdminInsertDto;
import com.hotel.GoldenChariot.dto.room.RoomUpsertDto;
import com.hotel.GoldenChariot.service.customer.CustomerService;
import com.hotel.GoldenChariot.service.room.RoomService;
import com.hotel.GoldenChariot.utility.Dropdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String roomNumber
    ){
        var listDto = roomService.getListRoom(page, roomNumber);
        model.addAttribute("listDto", listDto);
        long totalPage = roomService.getCountPage(roomNumber);
        if (totalPage == 0) {
            page = 1;
            totalPage = 1;
        }

        model.addAttribute("roomNumber", roomNumber);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", totalPage);

        return "/room/index";
    }

    @GetMapping("/upsert")
    public String upsert(Model model, @RequestParam(required = false) String roomNumber){
        RoomUpsertDto dto = new RoomUpsertDto();
        var dropdownType = Dropdown.dropdownType();
        if (roomNumber == null){
            dto.setRoomStatus("Vacant");
            model.addAttribute("dto", dto);
            model.addAttribute("dropdown", dropdownType);
            return "room/upsert";
        } else {
            dto = roomService.getData(roomNumber);
            model.addAttribute("dropdown", dropdownType);
            model.addAttribute("dto", dto);
            return "room/upsert";
        }

    }

    @PostMapping("upsert")
    public String upsert(@Valid @ModelAttribute("dto") RoomUpsertDto dto,
                         BindingResult bindingResult, Model model
    ){
        if (bindingResult.hasErrors()) {
            var dropdownType = Dropdown.dropdownType();
            model.addAttribute("dropdown", dropdownType);
            model.addAttribute("dto", dto);
            return "room/upsert";
        } else {
            roomService.save(dto);
            return "redirect:/room/index";
        }
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam(required = true) String roomNumber
    ){
        boolean bool = roomService.checkDependency(roomNumber);
        if (bool){
            return "redirect:/room/index";
        } else {
            return "/room/failDelete";
        }
    }
}
