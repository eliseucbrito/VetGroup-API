package com.api.vetgroup.repositories;

import com.api.vetgroup.models.RoomAccessList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAccessRepository extends JpaRepository<RoomAccessList, Long> {

}
