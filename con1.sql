create table station (id integer primary key, city char(20),
state char(2), lat_D real, long_W real);
insert into station values(13, 'Phoenix', 'AZ', 33, 112);
insert into station values(44, 'Denver', 'CO', 40, 105);
insert into station values(66, 'Caribou', 'ME', 47, 68);
select * from station where lat_D > 39.7;

create table stats(id integer references station(id),
month integer check(month between 1 and 12),
temp_f real check(temp_f between -80 and 150),
rain_i real check(rain_i between 0 and 100),
primary key (id, month));
insert into stats values(13, 1, 57.4, 0.31);
insert into stats values(13, 7, 91.7, 5.15);
insert into stats values(44, 1, 27.3, 0.18);
insert into stats values(44, 7, 74.8, 2.11);
insert into stats values(66, 1, 6.7, 2.10);
insert into stats values(66, 7, 65.8, 4.52);

commit;

select *
from station
inner join stats on station.id = stats.id;

select month, id, rain_i, temp_f
from stats order by month, rain_i desc;

select lat_D, city, temp_f
from stats, station
where month = 7
and stats.id = station.id
order by temp_f;
select max(temp_f), min(temp_f), avg(rain_i), id
from stats
group by id;
select * from station
where 50 < (select avg(temp_f) from stats
where station.id = stats.id);
create view metric_stats (id, month, temp_c, rain_c) as
select id,
month,
(temp_f - 32) * 5/9,
rain_i * 0.3937
from stats;

select id?month, to_char(temp_c, '99.999') as temp, rain_c from metric_stats
where temp_c < 0
and month = 1
order by rain_c;

update stats set rain_i = rain_i + 0.01;

create table student (sid integer primary key,sname char(40),sex char(1),age integer, year integer, gpa real);
create table dept(dname char(40) primary key,numphds integer);
create table prof(pname char(40) primary key,dname char(40) references dept(dname));
create table course(cno integer,cname char(40),dname char(40) references dept(dname), primary key(cno,dname))
create table major(dname char(40) references dept(dname), sid integer references student(sid))
create table section(dname char(40), cno integer,sectno integer,pname char(40) references prof(pname),primary key(dname,cno,sectno))
create table enroll(sid integer,grade real,dname char(40) 
,cno integer,sectno integer,primary key(sid,dname,cno,sectno))

select * from student
select * from dept
select * from prof
select * from course
select * from major
select * from section
select * from enroll







//1
select pname from prof where dname in(select dname from dept where numphds<50)
//2
select sname from student where gpa = (select min(gpa) from student)

//3
SELECT  enroll.dname, enroll.cno, enroll.sectno, AVG(gpa)
FROM enroll 
JOIN student on student.sid = enroll.sid
WHERE dname = 'Computer Sciences'
GROUP BY dname, cno, sectno
//4
select course.cname,q1.sectno from course join (select cno,sectno from enroll group by(cno,sectno) having count(cno)>6)q1 on course.cno=q1.cno
//5
select sid,sname from student where sid = (
select sid from enroll group by sid having count(cno) =
(
select max(count(cno)) from enroll group by sid
))
//6
select dname from major where sid in(select sid from student where age<18)
//7
select student.sname, q.dname from student join 
(select sid,dname from enroll where cno in
(select cno from course where cname Like '%College Geometry%'))q on student.sid = q.sid

//8
select dname numphds from dept where dname not in(
select distinct(dname) from enroll where cno in(
select cno from course where cname Like '%College Geometry%'))

//9
select sname from student where sid =(
select CS.sid from
(select sid from enroll where dname like '%Computer Sciences%')CS join
(select sid from enroll where dname like '%Mathematics%')M
on CS.sid = M.sid)



//10
select
    m.dname
    , max(s.age) - min(s.age) as age_difference
from
    student s
    join major m
        on s.sid = m.sid
where
    m.dname = 'Computer Sciences'
group by
    m.dname

select sid,dname from major where sid in(
select sid from student where gpa<1.0)

select avg(gpa) from student where 
select sid from student where gpa<1.0

//12
select cno from course where dname like '%Civil Engineering%'

select sid,sname,gpa from student where sid = (
select sid from enroll where cno in 
(select cno from course where dname like '%Civil Engineering%')
group by sid having count(*) = (select count(*) from course where dname like '%Civil Engineering%'))

