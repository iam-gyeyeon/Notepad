package com.project.Notepad.Memo.controller;

import com.project.Notepad.Memo.dto.MemoRequestDto;
import com.project.Notepad.Memo.dto.MemoResponseDto;
import com.project.Notepad.Memo.entity.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    /**
     * @param requestDto
     * @return
     */
    @PostMapping("/memos")
    public MemoResponseDto addMemo(@RequestBody MemoRequestDto requestDto) {
        //RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        //get Memo max Id
        Long maxId = !memoList.isEmpty() ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        //save data
        memoList.put(memo.getId(), memo);

        //Entity -> ResponseDto

        return new MemoResponseDto(memo);
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        //Map to List
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }
}
