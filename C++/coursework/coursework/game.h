#ifndef PROJECT_GAME_H
#define PROJECT_GAME_H

#include "robot.h"
#include <string>
#include <vector>
#include <map>

// A class holding many robots, each identified by a name

class game{
    std::map<std::string, robot> robots;

public:
    game(){};
    auto num_robots() const{return robots.size();} // returns the total number of robots in the game
    void move(const std::string &name, int dir);
    int num_within(int n) const;
    int max_travelled() const;
    std::vector<std::string> robots_by_distance() const;
};

#endif //PROJECT_GAME_H
