# Martian Robots

This is a CLI implementation of the Martian Robots problem.

## Running
### Using an IDE
Please run `CliApp.java`.

### Using Maven
#### 1. Compile the core
Navigate to `./martian-robots-core`

Run `mvn install`

#### 2. Compile the CLI
Navigate to `./martian-robots-cli`

Run `mvn install`

#### 3. Running the program
Navigate to `./martian-robots-cli/target`
Run `java -jar martian-robots-cli-1.0-SNAPSHOT-jar-with-dependencies.jar`

_(Note that this is the jar with dependencies.)_

This should start the application, and it can be interacted with as per the specification.

#### _Having issues?_
Maven can be a pain at times, particularly in a multi-modular environment. For simplicity, you can
download a complete jar [here](https://drive.google.com/file/d/1WzCdadj7tx43sMms-c4GW68d6o3ZqRTR/view?usp=sharing).

In a real environment, the core module might be pushed to Artifactory so that it's accessible
where needed.

## Interacting with the CLI
As per the specification, the output is bare, but I'll summarise the interaction steps as follows:

### Configuration
The first thing to do when using the CLI is to specify the size of the grid. To do this, input the x
and y coordinates of the top of the grid. The grid will span from `[0, 0]` up to (but not including)
`[x, y]`.

For example, the following would create a grid of three columns and four rows:
```
3 4
```

### Robot Interaction
Once this is complete, robots are created and interacted with in the following ways:
1. Adding the robots to the grid
2. Instructing a robot

#### Adding a robot to the grid
To do this, the coordinates and orientation of the robot must be provided, using the format
`{x} {y} {orientation}`.

`x` and `y` must be integer values.
`orientation` must be one of the following:
- `N` (facing north, i.e. in the direction of y+)
- `E` (facing east, i.e. in the direction of x+)
- `S` (facing south, i.e. in the direction of y-)
- `W` (facing west, i.e. in the direction of x-)

For example, the following will create a robot at `[1, 2]`, facing east:
```
1 2 E
```

#### Instructing a robot
To instruct a robot, a sequence of command characters can be provided. The allowed command
characters are as follows:

| Character | Effect |
|-----------|--------|
| `F`       | Move forward one unit, according to the robot's current orientation |
| `R`       | Rotate 90° to the right (clockwise) |
| `L`       | Rotate 90° to the left (anticlockwise) |

For example, the following command could be issued:
```
FRRFLLFFRRFLL
```

The result of issuing an instruction will be that the robot's new position will be printed, e.g.:
```
0 3 W
```

Note that this may be prefixed with `LOST` should the robot no longer be on the grid.

Once an instruction has been issued, you won't be able to interact with this robot again, and the
next input will allow you to add a new robot to the grid.