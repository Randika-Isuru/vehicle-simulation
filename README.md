# Vehicle Simulation Application

This is a Java 17 Maven application for simulating vehicle behavior. It allows you to build and run the project 
using Maven and Java commands using terminal.

## Prerequisites

Make sure you have the following installed:
- Java 17 JDK or later
- Apache Maven

## Run Tests

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command:

```bash
mvn test
```


## Build

To build the project, follow these steps:

1. Open a terminal or command prompt.
2. Navigate to the root directory of the project.
3. Run the following command:

```bash
mvn clean install
```

This command will compile the code, run tests, and package the application 
into a JAR file.

Run
Once the build process is complete, you can run the application using the 
following command:

```bash
java -jar target/vehicle-simulation-0.0.1-SNAPSHOT.jar
```

This command will execute the JAR file generated during the build process, 
launching the Vehicle Simulation Application.

## Interactive Mode

Once the Vehicle Simulation Application is running, it enters interactive mode, where it prompts the user for various 
inputs to simulate vehicle behavior. Here's how the interaction flows:

1. **Enter width and height of the field**: The user enters the width and height of the simulation field as two positive numbers separated by a space.

2. **Enter car Id**: The user provides a single word to identify the vehicle.

3. **Enter current position and facing direction of the car**: The user inputs the current position of the vehicle as two positive numbers separated by a space, followed by a single character indicating the facing direction (N, E, S, or W).
    - `N`: Indicates the NORTH
    - `E`: Indicates the EAST
    - `S`: Indicates the SOUTH
    - `W`: Indicates the WEST
4. **Enter car move commands**: The user enters a sequence of valid commands for the vehicle's movement. Valid commands consist of the following characters:
    - `F`: Move the vehicle forward.
    - `R`: Turn the vehicle right (clockwise).
    - `L`: Turn the vehicle left (counterclockwise).

5. **Enter car Id (or enter 'move' to drive your vehicle)**: At this point, the user can either input another car Id to simulate multiple vehicles in the same session, or type 'move' to execute the commands for the previously entered vehicle.

If the user types 'move', the application executes the commands for the vehicle and show final output and exit the program, allowing the user to input data for another vehicle or continue driving the same vehicle with new commands. If the user provides another car Id, the process repeats from step 3.

## Note
This interactive mode allows users to simulate the behavior of multiple vehicles within the defined field by providing inputs and observing the results.
Please note currently this program allow only 2 vehicles for parallel running.