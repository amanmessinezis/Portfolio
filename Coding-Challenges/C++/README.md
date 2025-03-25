# Robot Simulation in C++

## Overview
This project simulates a set of uniquely named robots moving in a 2D grid. Each robot tracks its own position and distance travelled. The simulation supports adding and moving robots, querying stats, and ordering them by distance from origin.

## Features
- Add and move robots in four directions (north, south, east, west)
- Track total distance travelled by each robot
- Get number of robots within a given Manhattan distance from the origin
- Find the robot that travelled the furthest
- List robots ordered by distance from the origin

## Technologies
- C++
- STL (`std::map`, `std::vector`)

## Files
- `robot.h / robot.cpp`: Defines individual robot behaviour
- `game.h / game.cpp`: Manages robot collection and system operations

## Sample Usage
```cpp
game g;
g.move("Alpha", 0); // North
g.move("Alpha", 1); // East
g.move("Beta", 3);  // West
int close = g.num_within(2); // Count robots near origin
