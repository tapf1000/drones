# DRONES

REST SERVICE FOR DRONE MANAGEMENT

## Service Functionality

+ Registering a drone : /api/v1/register
+ Loading a drone with medication items : /api/v1//load/{serialNo}
+ Checking loaded medication items for a given drone : /api/v1/medications/{serialNo}
+ Checking available drones for loading : /api/v1//availability
+ check drone battery level for a given drone : /api/v1//battery/{serialNo}
+ Audit logging for the battery level

## Building and Executing

To run the project, first clone a copy to your local machine, import into you IDE and execute. 
An embedded H2 database is included so there is no need for downloading a seperate databse.

## Running
The Drone.postman_collection.json in the project classpath has the calls for the REST endpoints and their parameters can be modified to siute different test cases. 
Each endpoint above has an appropriately titled API call in the postman collection.
The battery audit logs are loged to the table "POWER_LEVEL_LOGS"
