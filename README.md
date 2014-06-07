jbirth
======

simple bootstrap collection of instruments and code for REST-based web application
just do few steps:

1. clone it
2. copy the whole directory to the new path (cp -fvr jbirth newProj)
3. edit migration.sh in your new directory - $PROJECT_NAME=newProj
4. call it - ./migration.sh
5. import it as a maven project into your eclipse

Bingo. Now you have:
- init script for cassandra database
- simple DAO object that can save and read object User from db
- REST service for login-logout 
- simple junit test for DAO
- simple UI with angular for login\logout
- simple Integration test for your REST service
- lots of small happinesses

ATTENTION! THIS PROJECT HAS NO RELEASE OR ALFA OR BETA VERSION YET. 
PLEASE, KEEP CALM AND WAIT. WHEN I GET FREE TIME - I WILL FINISH IT.
THANK YOU.
