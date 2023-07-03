package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.dto.request.AddTaskRequest;
import com.payment.admin.entity.Task;
import com.payment.admin.query.SearchFilter;
import com.payment.admin.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/list/{workReportId}")
    public Object getList(@PathVariable("workReportId") Long id) {
        return CommonResult.success(taskService.queryAll(SearchFilter.build("workReportId", SearchFilter.Operator.EQ,id)));
    }

    @PostMapping("/add")
    public Object add(@RequestBody @Valid AddTaskRequest request){
        Task task = new Task();
        BeanUtils.copyProperties(request,task);
        return CommonResult.success(taskService.insertOrUpdate(task));
    }
}
