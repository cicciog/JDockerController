const fs = require('fs');
const mocha = require('mocha');
const assert = require('assert');
const dockerFileManager = require('../dockerFileManager');
const repositories = "/home/francesco/Documenti/repositories";


describe("Unit Test 1", function () {
    
    it("should be able to verify the array length", function () {
        var images = dockerFileManager.csvToArray('../DokerBuildImagesCmd.csv');
        
        assert.equal(images.length > 0,true,'array contains a number o element greater than 0');
    });
});

describe("Unit Test 2", function () {
    
    it("should be able to verify the array length", function () {
        var images = dockerFileManager.csvToArray('../DokerBuildImagesCmd.csv');
        dockerfile = dockerFileManager.readDockerfile(repositories + images[1].buildcommand.split('\\').join('/'));
        assert.equal(dockerfile.length > 0,true,'dockerfile contains all lines of the file specified');
    });
});

