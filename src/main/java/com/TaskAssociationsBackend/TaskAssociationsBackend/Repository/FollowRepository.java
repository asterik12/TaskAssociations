package com.TaskAssociationsBackend.TaskAssociationsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long>{

}
