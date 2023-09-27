# Second project for FSMVU BLM22104/BLM22106

This project is a basic library automation system written as part of the aforementioned course.

[Screenshots](screenshots) are provided.

## Features
### Users
- Borrowing/returning books
- Searching books by name/publisher/author/ISBN-13/category
- Sorting books by fields mentioned above
- Modifying account credentials
### Admins
- Adding/removing users, books
- [Adding admin accounts](#warning)
- Inspecting/modifying book credentials
- Inspecting/modifying user credentials
- Book search feature mentioned for users

## How To Run

This program uses MySQL for database purposes. Credentials used to connect to the database is stored in [DBCredentials.java](src/main/java/com/ertu/proje4/DBCredentials.java). If you wish to change the database name or username and password of your user, make sure to change the fields in that file accordingly.

This program uses 3 tables with source files provided: [books](tables/books.sql), [members](tables/members.sql) and [borrows](tables/borrows.sql).

Run these three files as is on the DB before you run the program.

## Warning

After doing these, you need to perform additional steps. The reason is that project assumes a supreme/original admin whose account cannot be modified or deleted by other admins. What you need to do is running this code on "members" table.

```sql
INSERT INTO `members`
(`email`, `password`, `fullname`,
 `birthday`, `uuid`, `admin`)
VALUES
('EMAIL', 'PASSWORD',
 'FULLNAME', 'BIRTHDAY-AS-YEAR-MONTH-DAY',
 'UUIDv4-SHOULD-BE-RANDOM-GENERATED',
  1);
```
You can generate UUIDv4 values [here](https://www.uuidgenerator.net/version4).

After that, take the UUID and pass it to variable "supreme_admin_uuid" in [AdminManagement.java](src/main/java/com/ertu/proje4/AdminManagement.java)
##

Finally, the main method which starts the program is in [Login.java](src/main/java/com/ertu/proje4/Login.java). From the IDE, you can run the program from there. I also provided a jar file with the credentials in the repo. I could also retrieve the credentials in runtime but honestly, this project is beyond frozen so if you make any changes, your choices are running the program from IDE, building your own jar or finally, modifying the program so that credentials are given in runtime.

