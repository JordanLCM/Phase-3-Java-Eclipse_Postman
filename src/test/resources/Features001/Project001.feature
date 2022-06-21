Feature: Cucumber with Rest Assured Project
Scenario: +++GET Request 1+++
Given A base URL "https://reqres.in"
And path is "/api/users?page=2"
Then User sends a GET request
And prints response
And status code is equal to 200

Scenario: +++POST Request 2+++
Given A base URL002 "https://reqres.in"
And path002 is "/api/users"
And payload002 file is "payload_cu001.json"
When User sends a post request
And prints response002
And status code002 is equal to 201

Scenario: +++PUT Request 3+++
Given A base URL003 "https://reqres.in"
And path003 is "/api/users/2"
And payload003 file is "payload_cu001.json"
Then User sends a PUT request
And prints response003
And status code003 is equal to 200

Scenario: +++DELETE Request 4+++
Given A base URL004 "https://reqres.in"
And path004 is "/api/users/2"
Then User sends a DELETE request
And prints response004
And status code004 is equal to 204