# JDockerController :ocean: :whale: :coffee:
A simple program that executes commands Shell (i.e. .sh or .bat) in java for run docker commands and outputs the console results.

## Check docker version
```
> docker version
```
## Docker containers available on this host
```
> docker ps
```
## Docker images available on this host
```
> docker images
```
## Remove all docker containers
```
> docker rm $(docker ps -a -q)
```
## Remove all docker images
```
> docker rmi $(docker images -q)
```
## Remove all docker images and docker containers
```
> docker rmi $(docker images -q) --force
```
## Build docker image
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

