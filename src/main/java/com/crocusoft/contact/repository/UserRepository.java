package com.crocusoft.contact.repository;

import com.crocusoft.contact.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
