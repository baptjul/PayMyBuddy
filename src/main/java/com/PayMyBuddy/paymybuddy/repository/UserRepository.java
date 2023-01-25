package com.PayMyBuddy.paymybuddy.repository;
import com.PayMyBuddy.paymybuddy.constants.DBqueries;
import com.PayMyBuddy.paymybuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = DBqueries.CHECK_IF_FRIEND, nativeQuery = true)
    int findFriend(int fistUserId, int secondUserId) ;

    @Query(value = DBqueries.FIND_BY_MAIL, nativeQuery = true)
    User findByMail(String eMail);

}