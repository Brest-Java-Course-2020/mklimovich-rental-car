DROP TABLE IF EXISTS car cascade;
CREATE TABLE car (
  car_id INT NOT NULL AUTO_INCREMENT,
  model VARCHAR(255) NOT NULL,
  color VARCHAR(255),
PRIMARY KEY (car_id)
);

DROP TABLE IF EXISTS rent cascade;
CREATE TABLE rent (
  rent_id INT AUTO_INCREMENT,
  daterent VARCHAR(255),
  car_id INT NOT NULL,
PRIMARY KEY (rent_id),
FOREIGN KEY (car_id) REFERENCES car(car_id)
);
