package id.ac.ui.cs.eaap.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.eaap.lab.model.*;
import id.ac.ui.cs.eaap.lab.service.IssueService;

import java.util.List;

@Repository
public interface IssueDb extends JpaRepository<IssueModel, Long> {
    List<IssueModel> findByRoomModel(RoomModel room);
    IssueModel findByIssueId(Long id);
}
