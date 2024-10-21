package id.ac.ui.cs.eaap.lab.service;


import id.ac.ui.cs.eaap.lab.model.RoomModel;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.eaap.lab.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class RoomService {

    @Autowired
    RoomDb roomDb;

    public List<RoomModel> findAll() {
        return roomDb.findAll();
    }

    public void add(RoomModel roomModel) {
        roomDb.save(roomModel);
    }

    public void update(RoomModel roomModel) {
    }

    public RoomModel getRoomById(Long id){
        return roomDb.findByRoomId(id);
    }

    public List<RoomModel> getAllByNama(String name) {
        Sort sortByName = Sort.by(Sort.Order.by("roomName").ignoreCase());

        return roomDb.findByRoomNameContainingIgnoreCase(name, sortByName);
    }
}

