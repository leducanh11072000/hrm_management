package com.payment.admin.controller;

import com.payment.admin.common.CommonResult;
import com.payment.admin.common.exception.ApiException;
import com.payment.admin.dto.request.AddAttendanceRequest;
import com.payment.admin.entity.Attendance;
import com.payment.admin.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin')")
    public Object get() {
        return CommonResult.success(service.queryAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin')")
    public Object add(@RequestBody @Valid AddAttendanceRequest request) {
        Attendance entity = new Attendance();
        BeanUtils.copyProperties(request, entity);
        return CommonResult.success(service.insertOrUpdate(entity));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Object update(@PathVariable("id") Long id, @RequestBody @Valid AddAttendanceRequest attendanceRequest) {
        Attendance entity = service.get(id);
        if (entity == null) {
            throw new ApiException("Role not Exit!");
        }
        BeanUtils.copyProperties(attendanceRequest, entity);
        return CommonResult.success(service.insertOrUpdate(entity));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Object delete(@PathVariable Long id) {
        service.deleteById(id);
        return CommonResult.success();
    }
}
