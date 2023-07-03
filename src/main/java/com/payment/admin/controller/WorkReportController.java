package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.query.SearchFilter;
import com.payment.admin.service.WorkReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/work-report")
public class WorkReportController {

    @Autowired
    private WorkReportService service ;
    @GetMapping("/list/{userId}")
    public Object get(@PathVariable("userId") Long id){
        return CommonResult.success(service.queryAll(SearchFilter.build("userId", SearchFilter.Operator.EQ,id)));
    }
}
