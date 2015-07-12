
use select_exercise;
create table student
	(sid char(15) primary key,
	 sname char(20),
	 sclass char(10),
	 sphone char(15),
	 sqq char(15),
	 sisleader bool
	);
create table allgroup
	(gid int(4) not null AUTO_INCREMENT,
	 gleader char(15), 
	 gsum int(4),
	 primary key (gid),
	 foreign key (gleader) references student(sid)on delete cascade on update cascade
	);
create table teacher
	(tid char(15) primary key,
	 tname char(20),
	 toffice char(20),
	 tphone char(15),
	 tqq char(15),
	 tischecker bool
	);
create table exercise
	(eid int(4) not null auto_increment,
	 etitle varchar(50),
	 eteacher char(20),
	 elevel char(2),
	 esum int(4),
	 eleftcount int(4),
	 eispass bool,
	 esummary varchar(500),
	 efilepath char(50),
	 primary key (eid)
	);
create table s_g
	(sid char(15),
	 gid int(4),
	 primary key (sid,gid),
	 foreign key (sid) references student(sid) on delete cascade on update cascade,
	 foreign key (gid) references allgroup(gid) on delete cascade on update cascade
	);
create table g_e
	(gid int(4),
	 eid int(4),
	 primary key (gid,eid),
	 foreign key (gid) references allgroup(gid) on delete cascade on update cascade,
	 foreign key (eid) references exercise(eid) on delete cascade on update cascade
	);
create table t_e
	(tid char(15),
	 eid int(4),
	 primary key (tid,eid),
	 foreign key (tid) references teacher(tid) on delete cascade on update cascade,
	 foreign key (eid) references exercise(eid) on delete cascade on update cascade
	);

create table user
	(uid char(15) primary key,
	 password char(35)
	);
