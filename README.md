# Mobility4u 
## Overview
This is an application used with a CLI whose purpose is to perform CRUD operations on a given vehicle catalog file.
## Setup
Nothing much, really. The starting class is Start.java, you can either run it from there or assemble the jar with maven and execute it.

One mandatory thing is the catalog file that must be added/referenced to.
You can either use application.properties to reference the path to the catalog file in the project structure, 
or you can just tell the app where your file is located by giving it the absolute path, including file name and extension, on startup.

The protocol for the data in the catalog file is as follows:
1. As to Vehicle types, only cars are currently supported.
2. Supported car types are GAS_CAR, HYBRID_CAR or ELECTRIC_CAR.
3. An example catalog file looks like the following:
````
ELECTRIC_CAR Tesla, Model 3, 150KW, 50000Ah, 30000 euro
GAS_CAR Honda, Civic, 1.5L, 80KW, 18000 euro
HYBRID_CAR Toyota, Prius, 1.5L, 50KW, 12000Ah, 24000 euro
````
Here the positions of brand, model, price etc are fixed, since that's the protocol the app is following.
## Dependencies
- [Junit 4.12](https://mvnrepository.com/artifact/junit/junit/4.12)