package id.ac.ui.cs.eaap.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.eaap.lab.model.RoomModel;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface RoomDb extends JpaRepository<RoomModel, Long> {
    RoomModel findByRoomId(long roomId);
    List<RoomModel> findByRoomNameContainingIgnoreCase(String name, Sort sort);
}
