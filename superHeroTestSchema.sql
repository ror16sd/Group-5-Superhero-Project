Drop database if exists SuperheroTest;

Create database SuperheroTest;

Use SuperheroTest;

create table location(
locationId int not null primary key auto_increment,
locationName varchar (30) not null,
locationDescription varchar(30) not null,
locationAddress varchar(30) not null,
locationState varchar(20) not null,
locationCity varchar(20) not null,
locationZip varchar(10) not null,
locationLatLat double,
locationLong double);

Create Table superOrganization(
organizationId int not null primary key auto_increment,
organizationName varchar(30) not null,
organizationDescription varchar(50) not null,
locationId int,
Constraint foreign key (locationId) references location(locationId)
);

Create Table power(
powerId int not null primary key auto_increment,
powerDescription varchar(30)
);

Create Table super(
superId int not null primary key auto_increment,
superName varchar(20) not null,
powerId int not null,
superDescription varchar(50) not null,
isSuper boolean not null,
Constraint foreign key (powerId) references power(powerId));

Create Table super_Organization(
superId int not null,
organizationId int not null,
primary key (superId, organizationId),
Constraint foreign key (superId) references super(superId),
Constraint foreign key (organizationId) references superOrganization (organizationId));

Create Table sightingLocation(
sightingId int not null primary key auto_increment,
sightingDate timestamp not null default current_timestamp,
locationId int,
superId int,
Constraint foreign key (superId) references super(superId),
Constraint foreign key (locationId) references location(locationId));