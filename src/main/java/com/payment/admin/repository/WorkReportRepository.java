package com.payment.admin.repository;

import com.payment.admin.entity.WorkReport;
import com.payment.admin.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkReportRepository extends BaseRepository<WorkReport,Long> {
}
