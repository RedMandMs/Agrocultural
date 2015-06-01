Use passport_agricultural
CREATE TABLE users
(
id int PRIMARY KEY NOT NULL IDENTITY(1,1),
user_login nvarchar(16) NOT NULL UNIQUE,
user_password nvarchar(64) NOT NULL,
user_role nvarchar(16) NOT NULL,
id_organization int NOT NULL DEFAULT('GUEST'),
CONSTRAINT FK_user_organization FOREIGN KEY (id_organization) 
REFERENCES organization_table (id)
)
GO