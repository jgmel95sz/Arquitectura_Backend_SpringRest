package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.MesToJob;

public interface IMesToJobDao extends JpaRepository<MesToJob, Integer> {

}
