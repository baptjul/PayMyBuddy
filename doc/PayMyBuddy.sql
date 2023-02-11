DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `transfer`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `user_friends`;

CREATE TABLE `transactions` (
  `id_transaction` int NOT NULL AUTO_INCREMENT,
  `transaction` double DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `user_receiver` int NOT NULL,
  `user_transmitter` int NOT NULL,
  PRIMARY KEY (`id_transaction`),
  KEY `FKrryd656dk3xpj5enwe90gwrjc` (`user_receiver`),
  KEY `FKe6d20i6qj7qg48avlo7mucwc2` (`user_transmitter`)
);

CREATE TABLE `transfer` (
  `id_transfer` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `transfer_date` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `pay_my_buddy_account` int NOT NULL,
  PRIMARY KEY (`id_transfer`),
  KEY `FKs6gk8qrmh6sfwxpc00abrss8l` (`pay_my_buddy_account`)
);

CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
);

INSERT INTO `user` VALUES (1,9.5,'2022-12-11 00:00:00','johndoe@gmail.com','john','doe','$2a$10$rziwrcF0EVmpdgWgwkiQweFp0hPOwH/OJuRAsRl/pxuPUpHTCpFWa','jDogchfg');
INSERT INTO `user` VALUES (2,40,'2022-12-11 00:00:00','janedoe@gmail.com','jane','doe','$2a$10$UHKF4wqrZpdaSTqmWEN0b.qZ8kroo0M03TlErc7SmbAS4vTgnl0Vm','jDoe2');

CREATE TABLE `user_friends` (
  `first_userid` int NOT NULL,
  `second_userid` int NOT NULL,
  KEY `FKem4aebdyarosgxjal594pwb0l` (`second_userid`),
  KEY `FKdjpb3k0faivh7g1aco13merys` (`first_userid`)
);


INSERT INTO `user_friends` VALUES (2,1);
INSERT INTO `user_friends` VALUES (1,2);

