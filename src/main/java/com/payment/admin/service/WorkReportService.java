package com.payment.admin.service;

import com.payment.admin.entity.WorkReport;
import com.payment.admin.repository.WorkReportRepository;
import com.payment.admin.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkReportService extends BaseService<WorkReport,Long, WorkReportRepository> {
    @Override
    protected WorkReportRepository getDao() {
        return repository;
    }

    @Autowired
    private WorkReportRepository repository;
}
