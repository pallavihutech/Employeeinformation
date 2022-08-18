package com.crud.api.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crud.api.model.Attendance;

@Repository
public interface AttendanceRepo extends CrudRepository<Attendance, Long> {

}
