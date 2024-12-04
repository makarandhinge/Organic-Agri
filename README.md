<p align="center">
  <img src="Logo/organic.png" alt="Logo" width="150" />
</p>

# Oraganic Agriculture

Developed an Organic Farming app providing detailed plant growth information and disease identification using machine
learning.

**Here's what it does:**

- **Plant Growth Information**: Detailed insights on plant growth stages and care tips.
- **Disease Identification**: Uses machine learning to identify plant diseases from images.
- **Marketplace**: A platform for farmers to buy and sell farm produce directly to consumers.
- **User-Friendly Interface**: Built using React Native for mobile and React JS for web, ensuring a responsive experience.

## Tech Stack

- **Backend**: Spring Boot
- **Database**: MySQL
- **Frontend**: React Native (Mobile) / React JS (Web)
- **Machine Learning**: Used for plant disease detection via image recognition.

## Run Locally

- Clone the project

```bash
  git clone https://github.com/makarandhinge/Organic-Agri.git
```

- Go to the Machine Learning project directory

```bash
  cd ML
```

- Create a Virtual Environment

```bash
  python -m venv env
```

- Activate the Environment

```bash
  .\env\Scripts\activate
```

- Install Requirements from the File

```bash
  cd "Flask Deployed App"
  pip install -r requirements.txt
```

- Download the machine learning model
   - Download from [Google Drive](https://drive.google.com/file/d/1prENNrRypgOo2FjR6pY8B7Jbl3j3sHO4/view?usp=drive_link) and paste it in "Flask Deployed App" Folder
  
- Start the machine learning server

```bash
  python app.py
```

- Open new tab and navigate to organic-agri root location

- Ensure that Java is installed on your system. You can verify this by running the following command

```bash
  java -version
```

- Configure the Backend

```bash
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=<db_username>
spring.datasource.password=<db_password>
```

- Configure the Oauth

```bash
# src/main/resources/secrets/oauth_keys.properties
spring.security.oauth2.client.registration.google.client-id="your-google-client-id"
spring.security.oauth2.client.registration.google.client-secret="your-google-client-key"
spring.security.oauth2.client.registration.github.client-id="your-github-client-id"
spring.security.oauth2.client.registration.github.client-"your-github-client-key"
spring.security.oauth2.client.registration.google.client-name=google
spring.security.oauth2.client.registration.google.scope=email,profile
spring.security.oauth2.client.registration.github.client-name=github
spring.security.oauth2.client.registration.github.scope=email,profile
```

**`Note`** - save this configuration in oauth_keys.properties and save in the given path src/main/resources/secrets/oauth_keys.properties

- To build and run the Spring Boot application, you need Maven. If Maven is not installed, you can follow the installation instructions from [here](https://github.com/makarandhinge/Installtion-Guideline/blob/main/Maven.md)

- Build the project

```bash
  mvnw clean install
```

- Run the Spring Boot application

```bash
  mvnw spring-boot:run
```

The backend and frontend should now be running on http://localhost:8081
