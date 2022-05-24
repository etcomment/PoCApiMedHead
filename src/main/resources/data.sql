DROP TABLE IF EXISTS hospital;
 
CREATE TABLE hospital (
  id INT AUTO_INCREMENT NOT NULL,
   hospital_name VARCHAR(255),
   longitude FLOAT,
   latitude FLOAT,
   spec VARCHAR(255),
   address VARCHAR(255),
   CONSTRAINT pk_hospital PRIMARY KEY (id)
);
 
INSERT INTO hospital (hospital_name, longitude, latitude, spec, address) VALUES
  ('Nuffield Health', '50.729755401611328', '-1.8697808980941772', 'dentiste, gyno, oncologie', '67 Landsdowne Road, Bournemouth, Dorset'),
  ('The Droitwich Spa Hospital', '52.265537261962891', '-2.1508662700653076', 'gyno, oncologie', 'St Andrews Road, Droitwich, Worcestershire'),
  ('Weston General Hospital', '51.322341918945313', '-2.9713847637176514', 'oncologie', 'Weston-Super-Mare, Avon, BS23 4TQ');
