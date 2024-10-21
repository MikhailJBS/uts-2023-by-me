package id.ac.ui.cs.eaap.lab.controller;

import id.ac.ui.cs.eaap.lab.model.IssueModel;
import id.ac.ui.cs.eaap.lab.model.RoomModel;
import id.ac.ui.cs.eaap.lab.service.IssueService;
import id.ac.ui.cs.eaap.lab.service.ListService;
import id.ac.ui.cs.eaap.lab.service.RoomService;

import id.ac.ui.cs.eaap.lab.dto.request.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;

@Slf4j
@Controller
@RequestMapping("/ruang")
public class RoomController {

    @Autowired
    ListService listService;

    @Autowired
    RoomService roomService;

    @Autowired
    IssueService issueService;

    @GetMapping("/view-all")
    public String viewAllPage(Model model) {
        log.info("view all room");
        // TODO
        List<RoomModel> roomModels = new ArrayList<>();
        roomModels = roomService.findAll();
        model.addAttribute("roomList", roomModels);
        return "room/view-all";
    }


    @GetMapping("/add")
    public String addFormPage(Model model) {

        RoomModel room = new RoomModel();
        var addRoomRequestDTO = new AddRoomRequestDTO();

        addRoomRequestDTO.setRoomId(room.getRoomId());

        model.addAttribute("roomDTO", addRoomRequestDTO);
        model.addAttribute("listService", listService);

        return "room/form-add";
    }

    @PostMapping(value = "/add", params = {"save"})
    public String addSubmitPage(@ModelAttribute AddRoomRequestDTO roomDTO, BindingResult result,
                                RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/ruang/add";
        }

        // TODO: store to database
        RoomModel newRoom = new RoomModel();
        
        newRoom.setBuildingName(roomDTO.getBuildingName());
        newRoom.setFaculty(roomDTO.getFaculty());
        newRoom.setRoomName(roomDTO.getRoomName());
        newRoom.setRoomNumber(roomDTO.getRoomNumber());
        
        roomService.add(newRoom);
        redirectAttrs.addFlashAttribute("success",
                String.format("Ruang berhasil disimpan dengan id %d", newRoom.getRoomId()));
        return "redirect:/ruang/view-all";
    }


    @GetMapping("/{id}/view")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        // TODO
        RoomModel roomModel = roomService.getRoomById(id);
        AddRoomRequestDTO roomDTO = new AddRoomRequestDTO();

        roomDTO.setRoomId(id);
        roomDTO.setFaculty(roomModel.getFaculty());
        roomDTO.setBuildingName(roomModel.getBuildingName());
        roomDTO.setRoomName(roomModel.getRoomName());
        roomDTO.setRoomNumber(roomModel.getRoomNumber());

        model.addAttribute("roomDTO", roomDTO);

        List<IssueModel> issueList = issueService.getIssueByRoom(roomModel);
        model.addAttribute("listIssue", issueList);

        AddIssueRequestDTO issueDTO = new AddIssueRequestDTO();
        issueDTO.setReportedOn(new Date(Calendar.getInstance().getTime().getTime()));
        issueDTO.setRoomModel(roomModel);
        model.addAttribute("issueDTO", issueDTO);

        model.addAttribute("listService", listService);

        return "room/detail";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        // TODO

        List<RoomModel> roomList = roomService.getAllByNama(name);
        model.addAttribute("roomList", roomList);
        return "room/view-all";
    }

}
