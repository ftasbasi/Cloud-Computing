var express = require("express");

//use the application off of express.
var app = express();

//define the route for "/"
app.get("/", function (request, response){
    response.sendFile(__dirname+"/views/index.html");
});

app.get("/getmeme", function (request, response){
    var input1 = request.query.input1;
    var input2 = request.query.input2;

    if (input1 != "" && input2 != "") {
        response.send("<div><img src=\"http://apimeme.com/meme?meme=Black-Girl-Wat&top="+input1+"&bottom="+input2+"\"></div>");
    } else {
        response.send("MISSING INPUT!!!");
    }
});

//start the server
app.listen(3030);