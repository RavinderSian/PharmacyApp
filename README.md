# PharmacyApp
Spring project for a pharmacy store, returns data about Medicines, Employees, Patients, Prescriptions and Ingredients in JSON format through endpoints.
The project allows CRUD operations for all model classes.
Tests were performed using JUnit.

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
