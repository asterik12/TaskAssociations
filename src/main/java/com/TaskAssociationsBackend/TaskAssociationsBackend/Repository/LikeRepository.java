package com.TaskAssociationsBackend.TaskAssociationsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Like;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

}
