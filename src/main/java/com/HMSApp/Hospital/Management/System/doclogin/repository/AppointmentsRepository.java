package com.HMSApp.Hospital.Management.System.doclogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HMSApp.Hospital.Management.System.doclogin.entity.Appointment;

public interface AppointmentsRepository extends JpaRepository<Appointment,Long>{

}
