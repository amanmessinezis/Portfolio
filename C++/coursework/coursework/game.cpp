#include "game.h"


using namespace std;


// move the named robot one step in the specified direction (0 = north, 1 = east, 2 = south, 3 = west).
// If there is no robot of that name, one should be created at the origin and then moved as above.

void game::move(const string &name, int dir){
    if(robots.count(name) == 0){
        robots.insert(pair<string, robot>(name, robot()));
    }
    if(dir == 0){
        robots.find(name)->second.move_north();
    } else if (dir == 1){
        robots.find(name)->second.move_east();
    } else if (dir == 2){
        robots.find(name)->second.move_south();
    } else if (dir == 3){
        robots.find(name)->second.move_west();
    }
}

// returns the number of robots that are no more than n steps from the origin.

int game::num_within(int n) const{
    int count = 0;
    auto it = robots.begin();
    while(it != robots.cend()){
        auto robot = it->second;
        if (distance(robot) <= n){
            ++count;
        }
        ++it;
    }
    return count;
}

// returns the furthest distance that any robot has travelled

int game::max_travelled() const{
    int furthest = 0;
    auto it = robots.begin();
    while(it != robots.cend()){
        auto robot = it->second;
        if (robot.travelled() > furthest){
            furthest = robot.travelled();
        }
        ++it;
    }
    return furthest;
}

// returns a collection of names of all the robots in the system
// arranged in increasing order of distance from the origin

vector<string> game::robots_by_distance() const{
    map<std::string, int> robot_distance_map;
    auto it = robots.begin();
    while(it != robots.cend()){
        auto name = it->first;
        auto robot = it->second;
        auto robot_distance = distance(robot);
        robot_distance_map.insert(pair<string, int>(name, robot_distance));
        ++it;
    }
    vector<string> names;
    for (auto it2 = robot_distance_map.rbegin(); it2 != robot_distance_map.rend(); it2++){
        names.push_back(it2->first);
    }

    return names;

}


