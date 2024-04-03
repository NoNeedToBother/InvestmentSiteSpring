package ru.kpfu.itis.paramonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.paramonov.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    @Query("update User u set u.bio = ?1 where u.id = ?2")
    @Modifying
    void updateBioById(String bio, Long id);

    @Query("update User u set u.country = ?1 where u.id = ?2")
    @Modifying
    void updateCountryById(String country, Long id);

    @Query("update User u set u.email = ?1 where u.id = ?2")
    @Modifying
    void updateEmailById(String email, Long id);

    @Query("update User u set u.lastname = ?1 where u.id = ?2")
    @Modifying
    void updateLastnameById(String lastname, Long id);

    @Query("update User u set u.name = ?1 where u.id = ?2")
    @Modifying
    void updateNameById(String name, Long id);

    @Query("update User u set u.login = ?1 where u.id = ?2")
    @Modifying
    void updateLoginById(String login, Long id);

    @Query("update User u set u.profilePicture = ?1 where u.id = ?2")
    @Modifying
    void updateProfilePictureById(String profilePicture, Long id);
}
