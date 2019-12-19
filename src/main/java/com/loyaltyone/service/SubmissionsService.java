package com.loyaltyone.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.loyaltyone.entity.Submissions;
@Repository
public interface SubmissionsService  extends JpaRepository<Submissions,Long> {
    @Query(value="SELECT * FROM SUBMISSIONS where name= :name", nativeQuery=true)
	public List<Submissions> findSubmissionsByName(@Param ("name") String name);
    @Transactional
    @Modifying
    @Query(value="UPDATE submissions SET name = :name, post= :post, response= :response WHERE postid = :postid" , nativeQuery=true)
   	public List<Submissions> findSubmissionsByPostId(@Param ("name") String name, @Param ("post") String post, @Param ("response") String response, @Param ("postid") String postid);
}
