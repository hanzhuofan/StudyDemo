package com.hzf.demo.controller;

import javax.validation.Valid;

import com.hzf.demo.common.validation.groups.Add;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hzf.demo.beans.dto.LoginUserDTO;
import com.hzf.demo.beans.vo.OrganizationVO;
import com.hzf.demo.common.Constants;
import com.hzf.demo.common.Result;
import com.hzf.demo.service.OrganizationService;
import com.hzf.demo.utils.MessageUtils;

import lombok.RequiredArgsConstructor;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping(value = "v1/save", produces = {Constants.CONTENT_TYPE})
    public Result<?> save(@AuthenticationPrincipal LoginUserDTO user,
        @Validated(Add.class) @RequestBody OrganizationVO body) {
        organizationService.save(body);
        return Result.ok(user);
    }

    @GetMapping(value = "test", produces = {Constants.CONTENT_TYPE})
    public Result<?> test(@RequestParam(required = false, defaultValue = "test") String msgKey) {
        return Result.ok(MessageUtils.get(msgKey));
    }
}
