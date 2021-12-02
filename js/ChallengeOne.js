const readline = require("readline");
const fs = require("fs");

var file = "../resources/measurements.txt";
var text = fs.readFileSync(file, "utf-8");
var test = text.split("\n");
console.log(text);
