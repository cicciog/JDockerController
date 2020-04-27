const fs = require("fs");
const parser = require('docker-file-parser');
const options = {includeComments: false};
const repositories = "/home/francesco/Documenti/repositories";

// Display the file data 
//console.log(dockerfile);

let dockerImage = [];
let dockerfile = {};


function csvToArray(pFilename) {
    let csvContents = fs.readFileSync(pFilename, {encoding: 'utf8'});
    let lines = csvContents.toString().split("\n");
    let dockersArray = [];

    for (let i = 0; i < lines.length; i++) {
        let element = lines[i].toString().split(',');
        dockersArray.push({
            name: element[0],
            buildcommand: element[1].replace('docker build -t ', '')
                    .replace(element[0], '')
                    .replace('.\r', 'Dockerfile')
                    .trim()
        });
    }
    return dockersArray;
}

function readDockerfile(pPath) {
    let file = fs.readFileSync(pPath, {encoding: 'utf8', flag: 'r'});
    return file.toString();
}

function createJson(pDockerName, pCommands) {
    let object = {}; // empty Object
    let key = 'docker';
    object[key] = []; // empty Array, which you can push() values into 

    for (let i = 0; i < pCommands.length; i++) {
        object[key].push(pCommands[i]);
    }

// stringify JSON Object
    let jsonContent = JSON.stringify(object);
    console.log(jsonContent);

    fs.writeFile('./output/' + pDockerName + '.json', jsonContent, 'utf8', function (err) {
        if (err) {
            console.log("An error occured while writing JSON Object to File.");
            return console.log(err);
        }

        console.log("JSON file has been saved.");
    });
}


dockerImage = csvToArray('DokerBuildImagesCmd.csv');

for (let i = 0; i < dockerImage.length; i++) {
    console.log(dockerImage[i].name.split('.').join('-'));
    dockerfile = readDockerfile(repositories + dockerImage[i].buildcommand.split('\\').join('/'));
    commands = parser.parse(dockerfile, options);
    console.log(commands);

    createJson(dockerImage[i].name, commands);
}






