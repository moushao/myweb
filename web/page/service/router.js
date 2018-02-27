var express = require('express')
var router = express.Router()
var path = require('path')
var proxy = require('http-proxy-middleware')
var axios = require('axios')
var marked = require('marked')

const SERVICE = 'http://localhost:8080/myweb_Web'

router.get('/', function (req, res) {
  axios.get(`${SERVICE}/article?op=showAllArticles`).then(response => {
    res.render('index', response.data.data)
  }, response => {
    console.log(response);
  })
})

router.get('/posts/:id', function (req, res) {
  axios.get(`${SERVICE}/article?op=findArticle&title_id=${req.params.id}`).then(response => {
    response.data.data.content = marked(response.data.data.content)
    res.render('post', response.data.data)
  }, response => {
    console.log(response);
  })
})

router.get('/admin(/*)', function (req, res) {
  res.sendFile(path.join(__dirname, '../static/admin.html'))
})

exports = module.exports = router
