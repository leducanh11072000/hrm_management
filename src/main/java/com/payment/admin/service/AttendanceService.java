package com.payment.admin.service;

import com.payment.admin.entity.Attendance;
import com.payment.admin.repository.AttendanceRepository;
import com.payment.admin.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService extends BaseService<Attendance,Long, AttendanceRepository> {
    @Override
    protected AttendanceRepository getDao() {
        return repository;
    }

    @Autowired
    private AttendanceRepository repository;


}
