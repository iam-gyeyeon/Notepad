package com.project.Notepad.Memo.controller;

import com.project.Notepad.Memo.dto.MemoRequestDto;
import com.project.Notepad.Memo.dto.MemoResponseDto;
import com.project.Notepad.Memo.entity.Memo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    /**
     *
     * @param requestDto
     * @return
     */
    @PostMapping("/memos")
    public MemoResponseDto addMemo(@RequestBody MemoRequestDto requestDto) {
        //RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        //get Memo max Id
        Long maxId = !memoList.isEmpty() ? Collections.max(memoList.keySet())+1 : 1;
        memo.setId(maxId);

        //save data
        memoList.put(memo.getId(), memo);

        //Entity -> ResponseDto
        MemoResponseDto responseDto = new MemoResponseDto(memo);

        return responseDto;
    }
}
