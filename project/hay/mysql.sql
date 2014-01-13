CREATE TABLE  `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

CREATE TABLE  `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `info` text,
  PRIMARY KEY (`id`),
  KEY `FK_user_1` (`role_id`)
);

CREATE TABLE  `news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `headline` varchar(128) NOT NULL,
  `message` text NOT NULL,
  `birthday` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE  `note` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `birthday` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_note_1` (`user_id`)
);

CREATE TABLE  `mail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sender_id` int(10) unsigned NOT NULL,
  `recipient_id` int(10) unsigned NOT NULL,
  `birthday` datetime NOT NULL,
  `subject` varchar(45) NOT NULL,
  `message` text NOT NULL,
  `delivered` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO role (name)
VALUES('damned');
INSERT INTO role (name)
VALUES('user');
INSERT INTO role (name)
VALUES('admin');

INSERT INTO user (name, password, role_id)
VALUES('admin', 'password',(SELECT id FROM role WHERE name='admin'));