package com.PayMyBuddy.paymybuddy.constants;

public class DBqueries {
    public static final String CHECK_IF_FRIEND = "select count(*) from user_friends where (first_userid = ?1 and second_userid = ?2) or (first_userid = ?2 and second_userid = ?1)";
    public static final String FIND_BY_MAIL = "select * from user where email = ?";
    public static final String SEARCH_BY_MAIL = "select * from user where email like %?%";
}
