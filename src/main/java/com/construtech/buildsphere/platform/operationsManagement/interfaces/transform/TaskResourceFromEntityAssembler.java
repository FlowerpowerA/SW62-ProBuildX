package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Task;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.TaskResource;

public class TaskResourceFromEntityAssembler {

    public static TaskResource toResourceFromEntity(Task entity){
        return new TaskResource(entity.getId(), entity.getTaskName(), entity.getTaskDescription(), entity.getStartDate(), entity.getMaxEndDate(), entity.getTeam(), entity.getProjectId());
    }
}
