package com.stom.app.reps;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stom.app.jpa.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}
