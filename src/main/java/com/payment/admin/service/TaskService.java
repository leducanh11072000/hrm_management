package com.payment.admin.service;

import com.payment.admin.entity.Task;
import com.payment.admin.repository.TaskRepository;
import com.payment.admin.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseService<Task,Long, TaskRepository> {
    @Override
    protected TaskRepository getDao() {
        return repository;
    }

    @Autowired
    private TaskRepository repository;


}
