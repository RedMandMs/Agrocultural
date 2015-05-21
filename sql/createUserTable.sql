Use passport_agricultural
CREATE TABLE users_table
(
id int PRIMARY KEY NOT NULL IDENTITY(1,1),
user_login nvarchar(16) NOT NULL UNIQUE,
user_password nvarchar(16) NOT NULL,
id_organization int NOT NULL,
CONSTRAINT FK_user_organization FOREIGN KEY (id_organization) 
REFERENCES organization_table (id)
)
GO