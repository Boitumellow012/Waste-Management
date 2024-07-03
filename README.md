# Waste Management API

This project is a Spring Boot application for managing waste categories and recycling tips. It provides RESTful endpoints for CRUD operations on waste categories, recycling tips and disposal guidelines.

## Table of Contents

- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
    - [Waste Category Endpoints](#waste-category-endpoints)
    - [Recycling Tip Endpoints](#recycling-tip-endpoints)
    - [Disposal Guideline Endpoints](#disposal-guideline-endpoints)
- [License](#license)

## Overview

The Waste Management API allows users to:

- Retrieve all waste categories and recycling tips
- Retrieve a specific waste category or recycling tip by ID
- Add new waste categories and recycling tips
- Update existing waste categories and recycling tips
- Delete a specific waste category or recycling tip by ID
- Delete all waste categories and recycling tips

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Lombok

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Boitumellow012/Waste-Management
    ```

2. Navigate to the project directory:
    ```sh
    cd Waste-Management
    ```

3. Build the project:
    ```sh
    mvn clean install
    ```

4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

The application will start and run on `http://localhost:7120/h2-ui`.

## API Endpoints

### Waste Category Endpoints

- **Get All Waste Categories**
  ```http
  GET /api/getAllWasteCategories
  ```
  
- **Get Waste Category By ID**
  ```http
  GET /api/getWasteCategoryById/{id}
  ```
  
- **Get All Waste Categories**
  ```http
  GET /api/getAllWasteCategories
  ```
  
- **Add Waste Category**
  ```http
  POST /api/addWasteCategory
  ```

- **Update Waste Category**
  ```http
  POST /api/updateWasteCategory/{id}
  ```

- **Delete Waste Category By ID**
  ```http
  DELETE /api/deleteWasteCategoryById/{id}
  ```

- **Delete All Waste Categories**
  ```http
  DELETE /api/deleteAllWasteCategories
  ```


### Recycling Tip Endpoints

- **Get All Recycling Tips**
  ```http
  GET /api/getAllRecyclingTips
  ```

- **Get Recycling Tip By ID**
  ```http
  GET /api/getRecyclingTipById/{id}
  ```

- **Add Recycling Tip**
  ```http
  POST /api/addRecyclingTip
  ```

- **Update Recycling Tip**
  ```http
  POST /api/updateRecyclingTip/{id}
  ```

- **Delete Recycling Tip By ID**
  ```http
  DELETE /api/deleteRecyclingTipById/{id}
  ```

- **Delete All Recycling Tip**
  ```http
  DELETE /api/deleteAllRecyclingTip
  ```

### Disposal Guideline Endpoints

- **Get All Recycling Tips**
  ```http
  GET /api/getAllDisposalGuidelines
  ```

- **Get Recycling Tip By ID**
  ```http
  GET /api/getDisposalGuidelineById/{id}
  ```

- **Add Recycling Tip**
  ```http
  POST /api/addDisposalGuideline
  ```

- **Update Recycling Tip**
  ```http
  POST /api/updateDisposalGuideline/{id}
  ```

- **Delete Recycling Tip By ID**
  ```http
  DELETE /api/deleteDisposalGuidelineById/{id}
  ```

- **Delete All Recycling Tip**
  ```http
  DELETE /api/deleteAllDisposalGuideline
  ```
  
## License

This project is licensed under the MIT License. See the LICENSE file for details.

 ```css
  
Make sure to adjust the `git clone` URL to your actual repository URL if you have one. This `README.md` provides a clear overview of your project, how to get started, and details about the API endpoints and error handling.

  ```