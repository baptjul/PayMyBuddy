package com.PayMyBuddy.paymybuddy.constants;

public class DBqueries {
    public static final String CHECK_IF_FRIEND = "select count(*) from user_friends where (first_userid = ?1 and second_userid = ?2) or (first_userid = ?2 and second_userid = ?1)";
}
