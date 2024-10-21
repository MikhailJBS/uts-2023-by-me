package id.ac.ui.cs.eaap.lab.service;


import id.ac.ui.cs.eaap.lab.model.IssueModel;
import id.ac.ui.cs.eaap.lab.model.RoomModel;
import id.ac.ui.cs.eaap.lab.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    IssueDb issueDb;

    public List<IssueModel> findAll() {
        return issueDb.findAll();
    }

    public void add(IssueModel issueModel) {
        issueDb.save(issueModel);
    }

    public void update(IssueModel issueModel) {
    }

    public List<IssueModel> getIssueByRoom(RoomModel room) {
        return issueDb.findByRoomModel(room);
    }

    public IssueModel getIssue(Long id) {
        return issueDb.findByIssueId(id);
    }

    public void updateIssue(IssueModel issue) {
        IssueModel oldIssue = getIssue(issue.getIssueId());
        if (oldIssue != null) {
            oldIssue.setDescription(issue.getDescription());
            oldIssue.setIssueId(issue.getIssueId());
            oldIssue.setReportedBy(issue.getReportedBy());
            oldIssue.setReportedOn(issue.getReportedOn());
            oldIssue.setRoomModel(issue.getRoomModel());
            oldIssue.setStatus(issue.getStatus());
            issueDb.save(oldIssue);
        }
    }

    public List<IssueModel> getActiveIssueModel() {
        List<IssueModel> allIssue = issueDb.findAll();
        List<IssueModel> allActiveIssue = new ArrayList();
        for (IssueModel issue : allIssue) {
            if (issue.getStatus() != "done") {
                allActiveIssue.add(issue);
            }
        }

        return allActiveIssue;
    }

    public List<HashMap<String, Object>> getStatisticsByFaculty() {
        // Fetch all issues
        List<IssueModel> allIssues = issueDb.findAll();
    
        // Create a HashMap to store the count of issues per faculty
        HashMap<String, Integer> facultyIssueCount = new HashMap<>();
    
        // Iterate through all issues and count the number of issues per faculty
        for (IssueModel issue : allIssues) {
            String faculty = issue.getRoomModel().getFaculty();
            facultyIssueCount.put(faculty, facultyIssueCount.getOrDefault(faculty, 0) + 1);
        }
    
        // Convert the result to a List of HashMaps
        List<HashMap<String, Object>> statistics = new ArrayList<>();
        for (String faculty : facultyIssueCount.keySet()) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("fakultas", faculty);
            data.put("jumlahKasus", facultyIssueCount.get(faculty));
            statistics.add(data);
        }
    
        return statistics;
    }    

}

