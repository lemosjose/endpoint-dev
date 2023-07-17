psql postgres -c "CREATE USER kyros WITH LOGIN SUPERUSER INHERIT CREATEDB CREATEROLE NOREPLICATION PASSOWRD 'kyrospassword'"
psql postgres -c "CREATE DATABASE endpointdb WITH OWNER = kyros ENCODING = 'UTF-8'"
psql endpointdb -f resources/schema.sql