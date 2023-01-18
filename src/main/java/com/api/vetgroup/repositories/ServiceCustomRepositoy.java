//package com.api.vetgroup.repositories;
//
//import com.api.vetgroup.models.VetService;
//import jakarta.persistence.EntityManager;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class ServiceCustomRepositoy {
//
//    private final EntityManager em;
//
//    public ServiceCustomRepositoy(EntityManager em) {
//        this.em = em;
//    }
//
//    public List<VetService> findByPatientId(Long id) {
//
//        String query = "SELECT S FROM tb_service as S ";
//
//        if (id != null) {
//            query += "WHERE S.patient_id = :id";
//        }
//
//        var q = em.createQuery(query, VetService.class);
//
//        if (id != null) {
//            q.setParameter("id", id);
//        }
//
//        return  q.getResultList();
//
//    }
//}
