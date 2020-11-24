CREATE DATABASE IF NOT EXISTS sponsorships_db;
DROP DATABASE IF EXISTS sponsorships_db;

USE sponsorships_db;
#
INSERT INTO leagues (name, coords, price) VALUES
("The Wyld Stallions", "1,1", 4500),
("Team Zoidberg", "1,1", 6000),
("The Zoomers", "1,1", 1500),
("North Horseburg Little League", "1,1", 3500),
("The Duloc Ogres", "1,1", 2500);