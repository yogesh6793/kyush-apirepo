package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.ContactUs;

@Repository("contact_us")
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {

}
