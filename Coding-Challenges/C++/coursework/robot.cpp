#include "robot.h"
#include <cstdlib>

using namespace std;

robot::robot(){ // Default constructor with robot placed at origin
    x = 0;
    y = 0;
    total_distance = 0; // Holds total distance travelled by this robot since it was created
}

// returns the distance of robot r from the origin according the Manhattan metric

int distance(const robot &r){
    return abs(r.x + r.y);
};