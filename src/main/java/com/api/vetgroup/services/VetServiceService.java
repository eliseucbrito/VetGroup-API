package com.api.vetgroup.services;

import com.api.vetgroup.models.Report;
import com.api.vetgroup.models.VetService;
import com.api.vetgroup.models.enums.ReportTypes;
import com.api.vetgroup.models.enums.ServiceStatus;
import com.api.vetgroup.models.enums.ServiceTypes;
import com.api.vetgroup.repositories.ReportRepository;
import com.api.vetgroup.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetServiceService {

    @Autowired
    private ServiceRepository repository;

    public List<VetService> findAll() {return repository.findAll();}

    public VetService findById(Long id) {
        Optional<VetService> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public VetService insert(VetService newService) {
        return repository.save(newService);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(VetService service) {
        repository.save(service);
    }

    @Transactional
    public void changeStatus(Long id, ServiceStatus status) throws IllegalAccessException {
        Optional<VetService> service = repository.findById(id);

        if (service.get().getType() == ServiceTypes.EMERGENCY && status == ServiceStatus.SCHEDULED) {
            throw new IllegalArgumentException("Service of EMERGENCY not accept the status SCHEDULED");
        }

        switch (status.getCode()) {
            case 1:
                service.get().setStatus(ServiceStatus.SCHEDULED);
                update(service.get());
                break;
            case 2:
                service.get().setStatus(ServiceStatus.WAITING_PAYMENT);
                update(service.get());
                break;
            case 3:
                if (service.get().getPrice() == null || service.get().getPrice() == 0) {
                    throw new IllegalAccessException("This service does not have an acceptable price");
                }
                service.get().setStatus(ServiceStatus.PAID);
                update(service.get());
                break;
            case 4:
                if (service.get().getStatus() == ServiceStatus.PAID) {
                    throw new IllegalArgumentException("This service cant be CANCELED because it is already PAID");
                }

                if (service.get().getStatus() == ServiceStatus.CANCELED) {
                    throw new IllegalArgumentException("This service is already CANCELED");
                }
                service.get().setStatus(ServiceStatus.CANCELED);
                update(service.get());
                break;
            default:
                throw new IllegalArgumentException("This service status not exists");
        }
        update(service.get());
    }
}
