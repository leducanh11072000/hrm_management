package com.payment.admin.repository;

import com.payment.admin.entity.Task;
import com.payment.admin.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task,Long> {
}
