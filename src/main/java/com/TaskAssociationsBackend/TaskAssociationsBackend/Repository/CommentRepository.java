package com.TaskAssociationsBackend.TaskAssociationsBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskAssociationsBackend.TaskAssociationsBackend.Models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
