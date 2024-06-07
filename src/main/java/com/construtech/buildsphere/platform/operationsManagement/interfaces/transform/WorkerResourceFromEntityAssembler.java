package com.construtech.buildsphere.platform.operationsManagement.interfaces.transform;

import com.construtech.buildsphere.platform.operationsManagement.domain.model.aggregates.Worker;
import com.construtech.buildsphere.platform.operationsManagement.interfaces.resources.WorkerResource;

public class WorkerResourceFromEntityAssembler {

    public static WorkerResource toResourceFromEntity(Worker entity){
        return new WorkerResource(entity.getId(), entity.getFullName(), entity.getRole(), entity.getHoursWorked(), entity.getTeam(), entity.getProjectId());
    }
}
