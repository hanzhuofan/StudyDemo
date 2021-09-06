package com.hzf.demo.controller;

import com.hzf.demo.beans.vo.OrganizationVO;
import com.hzf.demo.common.Result;
import com.hzf.demo.service.OrganizationService;
import com.hzf.demo.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping(value = "v1/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Result<?> save(@Valid @RequestBody OrganizationVO body) {
        organizationService.save(body);
        return Result.ok();
    }

    @GetMapping("test")
    public Result<?> test(@RequestParam(required = false, defaultValue = "test") String msgKey) {
        return Result.ok(MessageUtils.get(msgKey));
    }
}
