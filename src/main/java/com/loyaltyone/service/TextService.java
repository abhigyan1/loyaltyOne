package com.loyaltyone.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.loyaltyone.entity.Text;

@Component
@Repository
public interface TextService extends CrudRepository<Text , Long > {}


