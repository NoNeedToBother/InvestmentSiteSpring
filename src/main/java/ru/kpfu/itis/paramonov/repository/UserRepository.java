package ru.kpfu.itis.paramonov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.paramonov.model.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    @Query("update User u set u.bio = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateBioById(String bio, Long id);

    @Query("update User u set u.country = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateCountryById(String country, Long id);

    @Query("update User u set u.email = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateEmailById(String email, Long id);

    @Query("update User u set u.lastname = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateLastnameById(String lastname, Long id);

    @Query("update User u set u.name = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateNameById(String name, Long id);

    @Query("update User u set u.login = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateLoginById(String login, Long id);

    @Query("update User u set u.profilePicture = ?1 where u.id = ?2")
    @Modifying
    @Transactional
    void updateProfilePictureById(String profilePicture, Long id);

    @Query(value = "insert into user_role(user_id, role_id) values (:userId, :roleId)", nativeQuery = true)
    @Modifying
    @Transactional
    void addRole(@Param("roleId") Long roleId, @Param("userId") Long id);

    @Query(value = "insert into user_likes(receiver_id, sender_id) values (:receiverId, :senderId)", nativeQuery = true)
    @Modifying
    @Transactional
    void updateLikes(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId);

    @Query(value = "update User u set u.likes = u.likes + 1 where u.id = :receiverId")
    @Modifying
    @Transactional
    void addLike(@Param("receiverId") Long receiverId);
}
