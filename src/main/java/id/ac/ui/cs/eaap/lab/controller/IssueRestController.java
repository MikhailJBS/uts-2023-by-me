package id.ac.ui.cs.eaap.lab.controller;


import java.util.HashMap;

import id.ac.ui.cs.eaap.lab.service.IssueService;
import id.ac.ui.cs.eaap.lab.model.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/lapor")
public class IssueRestController {

    @Autowired
    IssueService issueService;

    @GetMapping("/active")
    public ResponseEntity getActiveIssues() {
        log.info("api get active issues");
        // TODO
        List<IssueModel> activeIssues = issueService.getActiveIssueModel();
        return ResponseEntity.ok(activeIssues);
    }

    @GetMapping("/statistics")
    public ResponseEntity getStatisticsIssues() {
        log.info("api statistics");
        // TODO
        List<HashMap<String, Object>> statistics = issueService.getStatisticsByFaculty();
        return ResponseEntity.ok(statistics);
    }
}
