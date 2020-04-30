const fs = require('fs');
const tape = require('tape');
const dockerFileManager = require('../dockerFileManager');


tape('every cmd', function (t) {
    t.plan(1);
    
    dockerImage = dockerFileManager.csvToArray('DokerBuildImagesCmd.csv'); 
   
    t.equal('ciao','ciao');
});


