#ifndef PROJECT_ROBOT_H
#define PROJECT_ROBOT_H

#include <string>

// Representing a robot moving around in a two-dimensional space

class robot{
    int total_distance;

public:
    int x;
    int y;
    robot();
    void move_north(){y+=1; total_distance++;}; // Move one step north
    void move_south(){y-=1; total_distance++;}; // Move one step south
    void move_east(){x+=1; total_distance++;}; // Move one step east
    void move_west(){x-=1; total_distance++;}; // Move one step west
    int north() const{return 0+y;}; // returns the current distance north of the robot. (This could be negative, if the robot has moved south more often than north.)
    int east() const{return 0+x;}; // returns the current distance east of the robot. (As with the previous function, this value might also be negative.)
    int travelled() const{return total_distance;};
};

int distance(const robot &r);


#endif //PROJECT_ROBOT_H
