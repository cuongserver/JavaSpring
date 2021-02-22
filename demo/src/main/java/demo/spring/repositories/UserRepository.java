package demo.spring.repositories;

import demo.spring.models.mapping.UserMapping;
import demo.spring.models.persistence.User;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Scope("request")
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLoginName(String loginName);

    @Query(value =
            "select cast(a.id as varchar) as id, a.email as email, a.login_name as loginName, " +
            "a.display_name as displayName, a.mfa_enabled as mfaEnabled, a.version as version from " +
            "users a where a.login_name like '%'||:phrase||'%' ",
            nativeQuery = true)
//    @Query(value = "select cast(a.id as varchar) as id, a.email, a.login_name, " +
//            "a.display_name, a.mfa_enabled, a.version from " +
//            "users a where a.login_name like '%'||:phrase||'%' ", nativeQuery = true)
    List<UserMapping> getUsersByLoginNamePhrase(String phrase);
}
