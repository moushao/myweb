var express = require('express')
var app = express()
var path = require('path')
var router = require('./router')
var xtpl = require('xtpl')

const PORT = 8088

app.set('views', path.join(__dirname, '../views'));
app.set('view engine', 'xtpl');

app.use(express.static(path.join(__dirname, '../static')))

app.use(router)
app.listen(PORT, function (err) {
  if (err) {
    console.log(err);
    return
  }
  console.log('listen on port: ' + PORT)
})
