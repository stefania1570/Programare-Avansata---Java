# Homework 7
## Concurrency

✔️Implement the commands that start/pause the robots (all of them or only a specific one). A robot can be paused for a specific time or indefinitely, requiring a start command.
The commands must be given using the keyboard.  
✔️Design an algorithm such that each robots will try to explore the map in a systematic fashion, ensuring the termination of the exploration process.  
✔️Implement a timekeeper thread that runs concurrently with the player threads, as a daemon. This thread will display the running time of the exploration and it will stop it exceeds a certain time limit.  
✔️At the end of the exploration, determine how many tokens each robot has placed in the matrix.
