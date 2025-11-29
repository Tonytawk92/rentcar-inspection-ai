-- Create a test user with a known password ('password')
-- The password hash was generated using a BCrypt generator for "password"
INSERT INTO USER_DETAILS (email, password_hash) VALUES
('test@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG');

-- Create a rental agent
INSERT INTO AGENT (name, email, phone_number, created_at) VALUES
('City Car Rentals', 'contact@citycar.com', '123-456-7890', CURRENT_TIMESTAMP);

-- Create some cars, linking them to the agent
-- Two cars are available, one is in use
INSERT INTO CAR (agent_id, plate_number, type, brand, model, color, manufacture_year, gear_type, status) VALUES
(1, 'ABC-123', 'Sedan', 'Toyota', 'Corolla', 'White', 2022, 'Auto', 'Available'),
(1, 'XYZ-789', 'SUV', 'Ford', 'Explorer', 'Black', 2023, 'Auto', 'Available'),
(1, 'DEF-456', 'Sedan', 'Honda', 'Civic', 'Silver', 2021, 'Auto', 'In_Use');

-- Create an active rental for the test user with the "In_Use" car
-- Assumes the user will have id=1 and the car will have id=3 based on insertion order
INSERT INTO RENTAL (user_id, car_id, start_date, status) VALUES
(1, 3, CURRENT_TIMESTAMP, 'Active');
