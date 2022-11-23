Drop database if exists SuperheroTest;

Create database SuperheroTest;

Use SuperheroTest;

create table location(
locationId int not null primary key auto_increment,
locationName varchar(30),
locationDescription varchar(30),
locationAddress varchar(30),
locationState varchar(20),
locationCity varchar(20),
locationZip varchar(10),
locationLatLat double,
locationLong double);

Create Table superOrganization(
organizationId int not null primary key auto_increment,
locationId int,
Constraint foreign key (locationId) references location(locationId)
);

Create Table power(
powerId int not null primary key auto_increment,
powerDescription varchar(30)
);

Create Table super(
superId int not null primary key auto_increment,
superName varchar(20),
powerId int,
heroDescription varchar(50),
isSuper boolean,
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