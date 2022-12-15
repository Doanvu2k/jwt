package com.example.sample_BE.controller;
import com.example.sample_BE.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("api/collection")

public class CollectionsController {
    @PostMapping("/addList")
    public ResponseEntity<List<StudentDTO>>convertData(@RequestBody List<StudentDTO> studentDTO){
        List<StudentDTO> listData = new ArrayList<>();
        var i=0;
        for (StudentDTO item: studentDTO){
            StudentDTO std = new StudentDTO();
            std.setUid("SV00"+item.getsClass().replace(" ","") + i + String.format("%04d",Integer.parseInt(item.getUid())));
            std.setName(item.getName());
            std.setsClass(item.getsClass());
            std.setAddress(item.getAddress());
            listData.add(std);
            i++;
        }

        LinkedList<StudentDTO> linkData = new LinkedList<>();
        var j=0;
        for (StudentDTO item: studentDTO) {
            linkData.add(new StudentDTO("SV00"+item.getsClass().replace(" ","") + j + String.format("%04d",Integer.parseInt(item.getUid()))
                    ,item.getName(),item.getsClass(), item.getAddress()));
            j++;
        }
        for (StudentDTO item: linkData ) {
            System.out.println(item.toString());
        }
        return ResponseEntity.ok(listData);
    }


    @PostMapping("/set")
    ResponseEntity<Set<StudentDTO>>convertDataWithSet(@RequestBody List<StudentDTO> studentDTO){
        Set<StudentDTO> setList = new HashSet<>();
        HashSet<Number> listNum = new HashSet<Number>(List.of(1,2,3,4,5));

        listNum.remove(1);
        System.out.println(listNum.toString());
        return ResponseEntity.ok(setList);
    }


}
