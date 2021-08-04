var express = require("express");
//use the application off of express.
var app = express();
const PORT = 8080;

app.get("/", function (request, response){
    response.sendFile(__dirname+"/views/index.html");
});

app.get("/getmeme", function (request, response){
    var input1 = request.query.input1;
    var input2 = request.query.input2;
    var selectedMeme=request.query.memeSelector;
    if (input1 != "" && input2 != "") {
        response.send("<style>\n" +
            "        @import url('https://rsms.me/inter/inter-ui.css');\n" +
            "        body {\n" +
            "            align-items: center;\n" +
            "            background: linear-gradient(-45deg, #210242, #9198e5);\n" +
            "            display: flex;\n" +
            "            font-family: 'Inter UI', sans-serif;\n" +
            "            height: 100vh;\n" +
            "            justify-content: center;\n" +
            "            margin: 0;\n" +
            "            width: 100vw;\n" +
            "        }\n" +
            "        .card {\n" +
            "            background: white;\n" +
            "            border-radius: 12px;\n" +
            "            box-shadow: 0 14px 28px rgba(0,0,0,0.25), 0 10px 10px rgba(0,0,0,0.22);\n" +
            "            height: 200px;\n" +
            "            overflow: hidden;\n" +
            "            padding: 20px;\n" +
            "            position: relative;\n" +
            "            width: 300px;\n" +
            "        }\n" +
            "        .title {\n" +
            "            font-size: 1.5em;\n" +
            "            height: 29px;\n" +
            "            margin-bottom: 0;\n" +
            "        }\n" +
            "        .text {\n" +
            "            color: #667;\n" +
            "            font-size: 1em;\n" +
            "            height: 38px;\n" +
            "            margin: 5px 0 0;\n" +
            "        }\n" +
            "        .line {\n" +
            "            left: 0;\n" +
            "            position: absolute;\n" +
            "            width: 340px;\n" +
            "        }\n" +
            "        .line path {\n" +
            "            fill: none;\n" +
            "            stroke: black;\n" +
            "            stroke-width: 2;\n" +
            "            stroke-dasharray: 300 1903;\n" +
            "        }\n" +
            "        .card.saving .line path {\n" +
            "            stroke-dashoffset: -472;\n" +
            "            stroke-dasharray: 153 1903;\n" +
            "            transition: stroke-dasharray 500ms, stroke-dashoffset 500ms;\n" +
            "        }\n" +
            "        .card.done .line path {\n" +
            "            stroke-dashoffset: -762;\n" +
            "            stroke-dasharray: 1095 1903;\n" +
            "            transition: stroke-dasharray 1500ms, stroke-dashoffset 1500ms;\n" +
            "        }\n" +
            "        .line2 {\n" +
            "            height: 52px;\n" +
            "            left: 150px;\n" +
            "            opacity: 0;\n" +
            "            position: absolute;\n" +
            "            stroke-dasharray: 6 48;\n" +
            "            stroke-dashoffset: -42;\n" +
            "            top: 158px;\n" +
            "            transform-origin: 50%;\n" +
            "            width: 50px;\n" +
            "        }\n" +
            "        .card.saving .line2 {\n" +
            "            animation: 4s 500ms Rotate forwards;\n" +
            "        }\n" +
            "        .card.done .line2 {\n" +
            "            animation: 500ms ScaleDown forwards;\n" +
            "        }\n" +
            "        .form {\n" +
            "            transform-origin: 0 50%;\n" +
            "            transition: transform 500ms, opacity 500ms;\n" +
            "        }\n" +
            "        .card.saving .form {\n" +
            "            transform: translateY(-16px) scale(.8);\n" +
            "        }\n" +
            "        .card.done .form {\n" +
            "            transform: translateY(-16px) scale(.8);\n" +
            "            opacity: 0;\n" +
            "        }\n" +
            "        .input {\n" +
            "            background: transparent;\n" +
            "            border: 0;\n" +
            "            box-sizing: border-box;\n" +
            "            font-size: 20px;\n" +
            "            height: 40px;\n" +
            "            margin-top: 20px;\n" +
            "            outline: none !important;\n" +
            "            position: relative;\n" +
            "            width: 300px;\n" +
            "        }\n" +
            "        @keyframes Rotate {\n" +
            "            0% { transform: rotate(0); opacity: 1; }\n" +
            "            25% { transform: rotate(360deg); opacity: 1; }\n" +
            "            50% { transform: rotate(720deg); opacity: 1; }\n" +
            "            75% { transform: rotate(1080deg); opacity: 1; stroke-dasharray: 6 48; stroke-dashoffset: -42;}\n" +
            "            100% { transform: rotate(1080deg); opacity: 1; stroke-dashoffset: 0; stroke-dasharray: 36 48;}\n" +
            "        }\n" +
            "        @keyframes ScaleDown {\n" +
            "            0% { transform: scale(1); }\n" +
            "            100% { transform: scale(0);}\n" +
            "        }\n" +
            "\n" +
            "    </style>" +
            "<div><img src=\"http://apimeme.com/meme?meme="+selectedMeme+"&top="+input1+"&bottom="+input2+"\"></div>");
    } else {
        response.send("MISSING INPUT!!!");
    }
});

//start the server
app.listen(PORT, () => {
    console.log('Listening at ' + PORT );
});



