package com.mrlaksus.emailsender.Repository;

import com.mrlaksus.emailsender.Entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {}
