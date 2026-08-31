package bitc.aws402.miniproject.controller;

import bitc.aws402.miniproject.dto.RestayDTO;
import bitc.aws402.miniproject.service.DetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//  김진형 -----

@RequiredArgsConstructor
@Controller
public class RestayController {

    private final DetailService detailService;

    // 숙소 리스트
    @GetMapping("/RoomList")
    public String RoomList(Model model) {
        List<RestayDTO> roomList = detailService.selectRoomList();
        model.addAttribute("roomList", roomList);
        return "room/RoomList";
    }
    // roomA 진입
    @GetMapping("/RoomList/roomA")
    public String roomA(@RequestParam("id") int roomIdx, Model model) {
        RestayDTO room = detailService.selectRoomDetail(roomIdx);
        model.addAttribute("room", room);
        return "room/roomA";
    }
    // roomB 진입
    @GetMapping("/RoomList/roomB")
    public String roomB(@RequestParam("id") int roomIdx, Model model) {
        RestayDTO room = detailService.selectRoomDetail(roomIdx);
        model.addAttribute("room", room);
        return "room/roomB";
    }
    // roomC 진입
    @GetMapping("/RoomList/roomC")
    public String roomC(@RequestParam("id") int roomIdx, Model model) {
        RestayDTO room = detailService.selectRoomDetail(roomIdx);
        model.addAttribute("room", room);
        return "room/roomC";
    }
    // 부대시설 조회
    @GetMapping("/Facilities")
    public String Facilities() {
        return "room/Facilities";
    }
    // 캠핑장 소개 조회
    @GetMapping("/RoomIntro")
    public String RoomIntro() {
        return "room/RoomIntro";
    }
}