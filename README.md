# PharmacyApp
Spring project for a pharmacy store, returns data about Medicines, Employees, Patients, Prescriptions and Ingredients in JSON format through endpoints.
The project allows CRUD operations for all model classes.
Tests were performed using JUnit.

## Running as Docker Container
This app can be run as a Docker container on a 64 bit OS

Building the base image:
This must be built from the jdk11-spring directory and the image must be named "spring-maven-jdk11-base"
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Docker%20build%20for%20base%20image.JPG)

Building pharmacy app image:
This image must be built from the PharmacyApp directory and can have any name
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Build%20Pharmacy%20Image.JPG)

Running pharmacy app image:
sudo docker run -p 8080:8080 image name

The -p 8080:8080 flag exposes port 8080 for requests.
To send requests when running the application this way:
http://(ip address):8080/desired_url

Successful output from run command:

![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/App%20Running%20Correctly.JPG)

## Postman Requests
There postman requests can be imported into postman from the json file present in the postman directory, this should be the result:

![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Postman%20Requests.JPG)

Typical GET request and response to find object by Id:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Get%20Patient%20By%20Id.JPG)

The scenario of the object not existing is also handled, returning a 404 response code:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Get%20Patient%20No%20Id.JPG)
This is handled for all requests where an id is passed in the URL

Objects can also be saved using a POST request:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Patient%20Saved.JPG)

There is field validation for these requests:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Patient%20No%20Fields.JPG)

Objects can be deleted with a GET request:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Delete%20Patient.JPG)

Certain objects are updated using a POST request where the input is passed as raw text:
![](https://github.com/RavinderSian/PharmacyApp/blob/main/screenshots/Update%20Patient%20Name.JPG)
