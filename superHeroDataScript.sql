use Superhero;

Insert into location(locationName, locationDescription, locationAddress, locationState, locationCity, locationZip, locationLong, locationLatLat)
VALUES
('Six Flags Over Texas', 'amusement park', '2201 E Road to Six Flags', 'TX', 'Arlington', '76011' , '97.0703','32.7550'),
('The Museum of Modern Art', 'art museum', '11 W 53rd St', 'NY', 'New York', '10019', '40.7614', '73.9776' ),
('Hydra Lair', 'Death Valley', '123 Villian Road', 'CA', 'Death Valley', '92328', '36.1449', '116.4901'),
('Hall of Justice', 'Capital' ,'456 Good Guy Street', 'Washington', 'DC', '20001', '38.9072', '77.0369');

Insert into power (powerDescription)
VALUES
('Flight'),
('Super speed'),
('Invisibility'),
('Evil Mastermind');

Insert into superperson(superName, powerId, superDescription, isSuper)
VALUES
('Superman', 1, 'Americas Hero', 1), 
('The Joker', 4, 'Pure psychopath', 0);
('The Wholesome Six', 3, "Angels", 1);

Insert into superOrganization(organizationName, organizationDescription, locationId)
VALUES
('Hydra', 'where the bad guys hang out', 3 ), 
('Justice League', 'where the good guys hang out', 4);


Insert into sightingLocation(date, locationId, superId)
VALUES
('2022-11-11 13:23:44', 1, 1);
('2022-10-29 18:00:35', 2, 5);


Insert into super_Organization(superId, organizationId)
VALUES
(1, 2),
(2, 1);