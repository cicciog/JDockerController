# JDockerController :ocean: :whale: :coffee:
A simple program that executes commands Shell (i.e. .sh or .bat) in java for run docker commands and outputs the console results.

## Check docker version
Show the Docker version information
```
> docker version
```
## Docker containers available on this host
List all containers
```
> docker ps
```
## Docker images available on this host
 List all images
```
> docker images
```
## Remove all docker containers
Remove one or more containers
```
> docker rm $(docker ps -a -q)
```
## Remove all docker images
 Remove one or more images
```
> docker rmi $(docker images -q)
```
## Remove all docker images and docker containers
 Remove all images and containers (forceability)
```
> docker rmi $(docker images -q) --force
```
## Build docker image
Build an image from a Dockerfile
```
> docker build -t name:v1.0 .Dockerfile
```

### Import maven dependency OpenCSV
```
<dependency>
    <groupId>com.opencsv</groupId>
     <artifactId>opencsv</artifactId>
    <version>4.6</version>
</dependency>
```

