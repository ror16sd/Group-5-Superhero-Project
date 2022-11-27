use Superhero;

Insert into location Values (locationId, locationName, locationDescription, locationAddress, locationState, locationCity, locationZip, locationLong, locationLatLat),
(5, 'Six Flags Over Texas', 'amusement park', '2201 E Road to Six Flags', 'TX', 'Arlington', '76011' , '97.0703','32.7550'),
(2, 'The Museum of Modern Art', 'art museum', '11 W 53rd St', 'NY', 'New York', '10019', '40.7614', '73.9776' ),
(3, 'Hydra Liar', 'Death Valley', '123 Villian Road', 'CA', 'Death Valley', '92328', '36.1449', '116.4901'),
(4, 'Hall of Justice', 'Capital' ,'456 Good Guy Street', 'Washington', 'DC', '20001', '38.9072', '77.0369');

Insert into power Values(powerId, powerDescription),
(2, 'Flight'),
(3, 'Super speed'),
(4, 'Invisibility'),
(5, 'Evil Mastermind');

Insert into super Values(superId, superName, powerId, heroDescription, isSuper),
(8,'Superman', 2, 'Americas Hero', 1), 
(9, 'The Joker', 5, 'Pure psychopath', 0);

Insert into superOrganization Values(organizationId, organizationName, locationId),
(7, 'Hydra', 3 ), (8, 'Justice League', 4);

Insert into super_Organization Values (superId, organizationId),
(8, 8), (9, 7);

Insert into sightingLocation Values(sightingId, sightingDate, locationId, superId),
(18, '2022-11-11 13:23:44', 5, 8);
