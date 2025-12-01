package com.template.user.infrastructure.persistence;

import com.template.user.domain.model.User;
import com.template.user.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaUserRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserEntity::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.fromDomain(user);
        return jpaUserRepository.save(userEntity).toDomain();
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }
}