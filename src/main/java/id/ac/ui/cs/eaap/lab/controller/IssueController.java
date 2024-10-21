package id.ac.ui.cs.eaap.lab.controller;

import id.ac.ui.cs.eaap.lab.model.IssueModel;
import id.ac.ui.cs.eaap.lab.service.IssueService;
import id.ac.ui.cs.eaap.lab.service.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ui.cs.eaap.lab.dto.request.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/lapor")
public class IssueController {

    @Autowired
    ListService listService;

    @Autowired
    IssueService issueService;


    @GetMapping("/view-all")
    public String viewAllIssues(Model model) {
        // TODO
        log.info("view all issues");
        List<IssueModel> issueModels = new ArrayList();
        issueModels = issueService.findAll();
        model.addAttribute("listIssues", issueModels);
        return "issue/view-all";
    }


    @PostMapping(value = "/add", params = {"save"})
    public String addIssueSubmitPage(@ModelAttribute AddIssueRequestDTO issueDTO, BindingResult result,
                                     RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/lapor/view-all";
        }

        // default values
        issueDTO.setReportedOn(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        issueDTO.setStatus(listService.getStatusOptionsList().get(0));

        log.info(issueDTO.toString());
        // TODO: store to database
        log.info("after save\n{}", issueDTO);

        IssueModel newIssue = new IssueModel();
        newIssue.setDescription(issueDTO.getDescription());
        newIssue.setIssueId(issueDTO.getIssueId());
        newIssue.setReportedBy(issueDTO.getReportedBy());
        newIssue.setReportedOn(issueDTO.getReportedOn());
        newIssue.setRoomModel(issueDTO.getRoomModel());
        newIssue.setStatus(issueDTO.getStatus());

        issueService.add(newIssue);

        redirectAttrs.addFlashAttribute("success",
                String.format("Kasus berhasil disimpan dengan id %d", newIssue.getIssueId()));
        return "redirect:/lapor/view-all";
    }


    @GetMapping(value = "/{id}/update")
    public String updateStatus(@PathVariable Long id, Model model) {
        // TODO
        IssueModel issueModel = issueService.getIssue(id);

        AddIssueRequestDTO issueDTO = new AddIssueRequestDTO();
        
        issueDTO.setDescription(issueModel.getDescription());
        issueDTO.setIssueId(issueModel.getIssueId());
        issueDTO.setReportedBy(issueModel.getReportedBy());
        issueDTO.setReportedOn(issueModel.getReportedOn());
        issueDTO.setRoomModel(issueModel.getRoomModel());
        issueDTO.setStatus(issueModel.getStatus());

        model.addAttribute("issueDTO", issueDTO);
        model.addAttribute("listService", listService);

        return "issue/form-update-status";
    }

    @PostMapping(value = "/update", params = {"save"})
    public String updateStatus(@ModelAttribute AddIssueRequestDTO issueDTO, BindingResult result,
                               RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            redirectAttrs.addFlashAttribute("error", "The error occurred.");
            return "redirect:/lapor/view-all";
        }

        // TODO: store to database
        IssueModel updatedIssue = new IssueModel();
        updatedIssue.setDescription(issueDTO.getDescription());
        updatedIssue.setIssueId(issueDTO.getIssueId());
        updatedIssue.setReportedBy(issueDTO.getReportedBy());
        updatedIssue.setReportedOn(issueDTO.getReportedOn());
        updatedIssue.setRoomModel(issueDTO.getRoomModel());
        updatedIssue.setStatus(issueDTO.getStatus());

        issueService.updateIssue(updatedIssue);


        redirectAttrs.addFlashAttribute("success",
                "Status berhasil di update");
        return "redirect:/lapor/view-all";
    }

    @GetMapping("/active")
    public String viewActiveIssues(Model model) {
        log.info("view all issues");
        return "issue/view-all";
    }
}
