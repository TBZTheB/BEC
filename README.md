
# BEC Tool Installation Guide 

An improved error detection tool in Concolic testing for Java projects


## Introduction

This README provides instructions for installing and running the BEC tool. You have two options for running the BEC tool:

1. Using the precompiled JAR file.

2. Building the code and running it directly.

# Option 1: Running the Precompiled JAR File 
## Step 1: Download the JAR File
Download the precompiled JAR file from the following Google Drive link: 

[Download BEC Tool JAR](https://drive.google.com/drive/folders/1BvjrVmqXaOsYuQZuyGl9bSjDB_3cb3GB)

Create a directory and move the downloaded JAR file into this directory.
## Step 2: Run the JAR File
Once downloaded, open Terminal/cmd and navigate to the project's directory. Run the following command, replacing `filename.jar` with the actual name of the JAR file you downloaded:

    java -jar <filename>.jar

Test functions are copied to the `Test.java` file

The functions we tested in the report are written in the `TestData.java` file

# Option 2: Building and Running from Source Code in IntelliJ IDEA
## Step 1: Clone the Repository
Clone the repository containing the BEC tool source code. Use the following command:

    git clone https://github.com/TBZTheB/BEC.git

## Step 2: Navigate to the Project Directory
Replace <project-directory> with the actual path to the cloned repository.

    cd <project-directory>

## Step 3: Compile the Code

## Step 4: Run the Main Class
After compiling, navigate to the `src` directory where the files are located.
Run the main method in the files 
- Choose the method and coverage level you want to run:
    - BEC Method:
        - BEC C1: Run `main` in `BECC1.java`
        - BEC C2: Run `main` in `BECC2.java`
        - BEC C3: Run `main` in `BECC3.java`
    - Concolic + BVA Method:
        - Concolic + BVA C1: Run `main` in `ConcolicBVAC1.java`
        - Concolic + BVA C2: Run `main` in `ConcolicBVAC2.java`
        - Concolic + BVA C3: Run `main` in `ConcolicBVAC3.java`
    - Concolic Method:
        - Concolic C1: Run `main` in `ConcolicC1.java`
        - Concolic C2: Run `main` in `ConcolicC2.java`
        - Concolic C3: Run `main` in `ConcolicC3.java`

### File Structure
- `BECC1.java`, `BECC2.java`, `BECC3.java`: Files for BEC method with different coverage levels (C1, C2, C3).
- `ConcolicBVAC1.java`, `ConcolicBVAC2.java`, `ConcolicBVAC3.java`: Files for Concolic + BVA method with different coverage levels.
- `ConcolicC1.java`, `ConcolicC2.java`, `ConcolicC3.java`: Files for Concolic method with different coverage levels.
## Add test method to the tool 
- Open the `Test.java` file located in the project's directory.
- Add test cases to the `Test.java` file.
- Run the test cases